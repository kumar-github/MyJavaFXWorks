package com.almasb.fxwars.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.transform.Rotate;

public abstract class Control {

    private Control() {}

    public abstract void update(Entity entity);

    private static Control bulletControl = new Control() {
        @Override
        public void update(Entity entity) {
            if ((Boolean) entity.getProperties().get(Entity.PROP_NEW_TARGET)) {
                entity.getProperties().put(Entity.PROP_NEW_TARGET, false);

                double targetX = (double) entity.getProperties().get(Entity.PROP_TARGET_X);
                double targetY = (double) entity.getProperties().get(Entity.PROP_TARGET_Y);

                entity.getVelocity().setXY(targetX, targetY)
                .subtract(entity.getTranslateX(), entity.getTranslateY())
                .normalize().multiply(entity.getSpeed());

                double angle = Math.toDegrees(Math.atan(entity.getVelocity().getY() / entity.getVelocity().getX()));
                angle = entity.getVelocity().getX() > 0 ? angle : 180 + angle;

                entity.getTransforms().add(new Rotate(angle, 0, 0));
            }

            entity.move();
        }
    };

    private static Control enemyWandererControl = new Control() {
        @Override
        public void update(Entity entity) {
            if ((Boolean) entity.getProperties().get(Entity.PROP_NEW_TARGET)) {
                entity.getProperties().put(Entity.PROP_NEW_TARGET, false);

                double targetX = (double) entity.getProperties().get(Entity.PROP_TARGET_X);
                double targetY = (double) entity.getProperties().get(Entity.PROP_TARGET_Y);

                entity.getVelocity().setXY(targetX, targetY)
                .subtract(entity.getTranslateX(), entity.getTranslateY())
                .normalize().multiply(entity.getSpeed());
            }

            entity.move();
        }
    };

    private static Control enemySeekerControl = new Control() {
        @Override
        public void update(Entity entity) {
            double playerX = ((SimpleDoubleProperty) entity.getProperties().get(Entity.PROP_PLAYER_X)).get();
            double playerY = ((SimpleDoubleProperty) entity.getProperties().get(Entity.PROP_PLAYER_Y)).get();

            entity.getVelocity().setXY(playerX, playerY)
            .subtract(entity.getTranslateX(), entity.getTranslateY())
            .normalize().multiply(entity.getSpeed());

            entity.move();
        }
    };

    public static Control getBulletControl() {
        return bulletControl;
    }

    public static Control getEnemyWandererControl() {
        return enemyWandererControl;
    }

    public static Control getEnemySeekerControl() {
        return enemySeekerControl;
    }
}
