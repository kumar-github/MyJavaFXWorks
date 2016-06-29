package com.almasb.fxwars.entity;

import com.almasb.fxwars.math.Vec2;

import javafx.scene.Parent;

public class Entity extends Parent {
    // TODO: move these to a separate file
    public static final String PROP_PLAYER = "Entity_player";
    public static final String PROP_PLAYER_X = "SimpleDoubleProperty_x";
    public static final String PROP_PLAYER_Y = "SimpleDoubleProperty_y";
    public static final String PROP_NEW_TARGET = "boolean_requestNewTarget";
    public static final String PROP_TARGET_X = "double_x";
    public static final String PROP_TARGET_Y = "double_y";

    public enum Type {
        PLAYER(null),
        BULLET(Control.getBulletControl()),
        ENEMY_WANDERER(Control.getEnemyWandererControl()),
        ENEMY_SEEKER(Control.getEnemySeekerControl()),
        POWERUP(Control.getEnemyWandererControl());

        final Control control;

        private Type(Control control) {
            this.control = control;
        }
    }

    private Vec2 velocity = new Vec2(0, 0);
    private Control control;
    private Type type;
    private EntityData data;
    private boolean alive = true;

    public Entity(Type type) {
        this.type = type;
        this.control = type.control;

        switch (type) {
            case BULLET:
                data = EntityData.getBullet();
                break;
            case ENEMY_SEEKER:
                data = EntityData.getEnemySeeker();
                break;
            case ENEMY_WANDERER:
                data = EntityData.getEnemyWanderer();
                break;
            case PLAYER:
                data = EntityData.getPlayer();
                break;
            case POWERUP:
                data = EntityData.getPowerup();
                break;
            default:
                System.out.println("unknown type - " + type);
                break;
        }

        getChildren().add(data.graphics);
    }

    protected void move() {
        translate(velocity.getX(), velocity.getY());
    }

    public void translate(double x, double y) {
        setTranslateX(getTranslateX() + x);
        setTranslateY(getTranslateY() + y);
    }

    public void update() {
        if (control != null)
            control.update(this);
    }

    public void setAlive(boolean b) {
        alive = b;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setVelocity(Vec2 velocity) {
        this.velocity.set(velocity);
    }

    public Vec2 getVelocity() {
        return velocity;
    }

    public Type getType() {
        return type;
    }

    public BBoxType getBBoxType() {
        return data.geometry.type;
    }

    public GeometryData getGeometry() {
        return data.geometry;
    }

    public double getSpeed() {
        return data.speed;
    }

    public AnimationData getDeathAnimation() {
        switch (type) {
            case BULLET:
                return AnimationData.createBulletDeath(getTranslateX(), getTranslateY());
            case ENEMY_SEEKER:  // fallthru
            case ENEMY_WANDERER:
                return AnimationData.createEnemyDeath(getTranslateX(), getTranslateY(), data.geometry.width, data.geometry.height);
            case PLAYER:
                return AnimationData.createPlayerDeath(getTranslateX() + data.geometry.width / 2, getTranslateY() + data.geometry.height / 2);
            case POWERUP:
                return AnimationData.createPowerupDeath(getTranslateX(), getTranslateY(), data.geometry.width, data.geometry.height);
            default:
                System.out.println("unknown type - " + type);
                return null;
        }
    }
}
