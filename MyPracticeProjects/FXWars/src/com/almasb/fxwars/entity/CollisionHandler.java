package com.almasb.fxwars.entity;

import java.util.HashMap;

import com.almasb.fxwars.Config;
import com.almasb.fxwars.entity.Entity.Type;

public abstract class CollisionHandler {

    private static HashMap<CollisionPair, CollisionHandler> collisionHandlers = new HashMap<CollisionPair, CollisionHandler>();
    private static HashMap<Type, CollisionHandler> screenCollisionHandlers = new HashMap<Type, CollisionHandler>();
    private static HashMap<BBoxPair, CollisionChecker> collisionCheckers = new HashMap<BBoxPair, CollisionChecker>();

    protected boolean onCollision(Entity a, Entity b) { return false; }
    protected void onScreenCollision(Entity e) {}

    /**
     * Registers a collision for 2 types of entities so that
     * when they collide the handler will be called
     *
     * Note: only registered collisions will trigger the collision event
     *
     * @param a
     * @param b
     * @param handler
     */
    public static void registerCollision(Type a, Type b, CollisionHandler handler) {
        collisionHandlers.put(CollisionPair.get(a, b), handler);
    }

    public static void registerScreenCollision(Type t, CollisionHandler handler) {
        screenCollisionHandlers.put(t, handler);
    }

    public static boolean handle(Entity a, Entity b) {
        // first check if collision between these types is registered
        CollisionPair pair = CollisionPair.get(a.getType(), b.getType());

        CollisionHandler handler = null;
        for (CollisionPair p : collisionHandlers.keySet()) {
            if (pair.typeA == p.typeA && pair.typeB == p.typeB) {
                handler = collisionHandlers.get(p);
                break;
            }
        }

        if (handler != null) {
            BBoxPair bbox = BBoxPair.get(a.getBBoxType(), b.getBBoxType());
            CollisionChecker checker = null;

            for (BBoxPair p : collisionCheckers.keySet()) {
                if (bbox.typeA == p.typeA && bbox.typeB == p.typeB) {
                    checker = collisionCheckers.get(p);
                    break;
                }
            }

            if (checker != null && checker.isColliding(a, b)) {
                handler.onCollision(a, b);
                return true;
            }
        }

        return false;
    }

    public static void handleScreenCollision(Entity e) {
        CollisionHandler handler = screenCollisionHandlers.get(e.getType());
        if (handler != null) {
            if (CollisionChecker.isCollidingWithScreen(e)) {
                handler.onScreenCollision(e);
            }
        }
    }

    static {
        // register our collision checkers between different types of geometries
        CollisionChecker bboxLineChecker = new CollisionChecker() {
            @Override
            public boolean isColliding(Entity a, Entity b) {
                Entity geoBox;
                Entity geoLine;

                if (a.getBBoxType() == BBoxType.LINE) {
                    geoBox = b;
                    geoLine = a;
                }
                else {
                    geoBox = a;
                    geoLine = b;
                }

                // line start and end points
                double startX = geoLine.getTranslateX();
                double startY = geoLine.getTranslateY();
                double endX = startX + geoLine.getVelocity().getX();
                double endY = startY + geoLine.getVelocity().getY();

                // bounding box
                double minX = geoBox.getTranslateX();
                double minY = geoBox.getTranslateY();
                double maxX = minX + geoBox.getGeometry().width;
                double maxY = minY + geoBox.getGeometry().height;

                return bboxIntersectsLine(minX, minY, maxX, maxY, startX, startY, endX, endY);
            }
        };

        CollisionChecker bboxCircleChecker = new CollisionChecker() {
            @Override
            public boolean isColliding(Entity a, Entity b) {
                Entity geoBox;
                Entity geoCircle;

                if (a.getBBoxType() == BBoxType.CIRCLE) {
                    geoBox = b;
                    geoCircle = a;
                }
                else {
                    geoBox = a;
                    geoCircle = b;
                }

                // bounding box
                double minX = geoCircle.getTranslateX();
                double minY = geoCircle.getTranslateY();
                double maxX = minX + geoCircle.getGeometry().width;
                double maxY = minY + geoCircle.getGeometry().height;

                // bounding box
                double minX2 = geoBox.getTranslateX();
                double minY2 = geoBox.getTranslateY();
                double maxX2 = minX2 + geoBox.getGeometry().width;
                double maxY2 = minY2 + geoBox.getGeometry().height;

                // we approximate collision with a bbox
                return bboxIntersectsBBox(minX, minY, maxX, maxY, minX2, minY2, maxX2, maxY2);
            }
        };

        collisionCheckers.put(BBoxPair.get(BBoxType.BOX, BBoxType.LINE), bboxLineChecker);
        collisionCheckers.put(BBoxPair.get(BBoxType.CIRCLE, BBoxType.LINE), bboxLineChecker);
        collisionCheckers.put(BBoxPair.get(BBoxType.BOX, BBoxType.CIRCLE), bboxCircleChecker);
    }

    private abstract static class CollisionChecker {

        protected abstract boolean isColliding(Entity a, Entity b);

        /**
         * This is a special case of bbox as screen typically
         * contains all objects but is being collided with
         * only when screen bounds are touching the object
         *
         * @param e
         * @return
         */
        private static boolean isCollidingWithScreen(Entity e) {
            int appW = Config.getInstance().getAppWidth();
            int appH = Config.getInstance().getAppHeight();

            switch (e.getBBoxType()) {
                case BOX:
                    return e.getTranslateX() <= 0 || e.getTranslateX() + e.getGeometry().width >= appW
                    || e.getTranslateY() <= 0 || e.getTranslateY() + e.getGeometry().height >= appH;
                case CIRCLE:
                    return e.getTranslateX() <= 0 || e.getTranslateX() + e.getGeometry().width >= appW
                    || e.getTranslateY() <= 0 || e.getTranslateY() + e.getGeometry().height >= appH;
                case LINE:
                    // line start and end points
                    double startX = e.getTranslateX();
                    double startY = e.getTranslateY();
                    double endX = startX + e.getVelocity().getX();
                    double endY = startY + e.getVelocity().getY();

                    return bboxContainsPoint(0, 0, appW, appH, startX, startY)
                            ^ bboxContainsPoint(0, 0, appW, appH, endX, endY);
                default:
                    System.out.println("Unknown bbox type - " + e.getBBoxType());
                    return false;
            }
        }
    }

    private static class CollisionPair {
        Type typeA;
        Type typeB;

        public static CollisionPair get(Type t, Type t2) {
            CollisionPair pair = new CollisionPair();
            if (t.ordinal() <= t2.ordinal()) {
                pair.typeA = t;
                pair.typeB = t2;
            }
            else {
                pair.typeA = t2;
                pair.typeB = t;
            }

            return pair;
        }
    }

    private static class BBoxPair {
        BBoxType typeA;
        BBoxType typeB;

        public static BBoxPair get(BBoxType t, BBoxType t2) {
            BBoxPair pair = new BBoxPair();
            if (t.ordinal() <= t2.ordinal()) {
                pair.typeA = t;
                pair.typeB = t2;
            }
            else {
                pair.typeA = t2;
                pair.typeB = t;
            }

            return pair;
        }
    }

    /**
     * Returns true iff bounding box defined by minX, minY and maxX, maxY
     * contains a point x, y
     *
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     * @param x
     * @param y
     * @return
     */
    private static boolean bboxContainsPoint(double minX, double minY, double maxX, double maxY,
            double x, double y) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    /**
     * Returns true iff bounding box defined by minX, minY and maxX, maxY
     * intersects a line defined by startX, startY and endX, endY
     *
     * Note: if bbox contains the line this method returns true
     *
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    private static boolean bboxIntersectsLine(double minX, double minY, double maxX, double maxY,
            double startX, double startY, double endX, double endY) {
        return bboxContainsPoint(minX, minY, maxX, maxY, startX, startY)
                || bboxContainsPoint(minX, minY, maxX, maxY, endX, endY);
    }

    /**
     * Returns true iff bounding box defined by minX, minY and maxX, maxY
     * intersects another bbox defined by minX2, minY2 and maxX2, maxY2
     *
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     * @param minX2
     * @param minY2
     * @param maxX2
     * @param maxY2
     * @return
     */
    private static boolean bboxIntersectsBBox(double minX, double minY, double maxX, double maxY,
            double minX2, double minY2, double maxX2, double maxY2) {
        return maxX >= minX2 && maxY >= minY2 && minX <= maxX2 && minY <= maxY2;
    }
}
