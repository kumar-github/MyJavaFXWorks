package com.almasb.fxwars.math;

public class Vec2 {

    private double x, y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void set(Vec2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vec2 setXY(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vec2 add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2 subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2 multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vec2 divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public Vec2 normalize() {
        double m = getMagnitude();
        if (m > 0)
            divide(m);
        return this;
    }
}
