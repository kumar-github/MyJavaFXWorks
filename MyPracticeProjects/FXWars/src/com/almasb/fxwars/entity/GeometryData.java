package com.almasb.fxwars.entity;

public class GeometryData {
    public double width, height;
    public BBoxType type;
    public void setWidth(double w) {
        width = w;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double h) {
        height = h;
    }

    public double getHeight() {
        return height;
    }

    public void setType(BBoxType t) {
        type = t;
    }

    public BBoxType getType() {
        return type;
    }
}
