package com.almasb.fxwars.entity;

import javafx.scene.paint.Color;

public class VisualData {
    public VisualType type;
    public Color color;

    public void setType(VisualType type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public VisualType getType() {
        return type;
    }
}
