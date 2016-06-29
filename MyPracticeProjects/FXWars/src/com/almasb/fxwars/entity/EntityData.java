package com.almasb.fxwars.entity;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class EntityData {
    public GeometryData geometry;
    public VisualData visual;
    public double speed;

    public Node graphics;

    /**
     * Returns a new object with geometry and visual
     * references pointing at this object's fields, i.e.
     * references to original geometry and visual are maintained
     * as to save memory since the data doesn't change
     *
     * The {@link #graphics} field is not copied at all
     * per javafx scenegraph specs - the node can only have
     * 1 parent, thus manual copying is needed for {@link #graphics} field
     *
     * @return
     */
    private EntityData copy() {
        EntityData copy = new EntityData();
        copy.geometry = this.geometry;
        copy.visual = this.visual;
        copy.speed = this.speed;
        return copy;
    }

    public void setSpeed(double s) {
        speed = s;
    }

    public double getSpeed() {
        return speed;
    }

    public void setGeometry(GeometryData d) {
        geometry = d;
    }

    public GeometryData getGeometry() {
        return geometry;
    }

    public VisualData getVisual() {
        return visual;
    }

    public void setVisual(VisualData visual) {
        this.visual = visual;
    }

    public static class DataHolder {
        @FXML
        private EntityData dataPlayer;
        @FXML
        private EntityData dataBullet;
        @FXML
        private EntityData dataEnemyWanderer;
        @FXML
        private EntityData dataEnemySeeker;
        @FXML
        private EntityData dataPowerup;

        private DataHolder() {}
    }

    private static DataHolder holder = new DataHolder();

    static {
        FXMLLoader loader = new FXMLLoader(holder.getClass().getResource("entity_data.fxml"));
        loader.setController(holder);
        try {
            loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EntityData getPlayer() {
        EntityData data = holder.dataPlayer.copy();
        data.graphics = getGraphics(data.visual, data.geometry);
        //        double radius = 10;
        //
        //        GeometryData geo = new GeometryData();
        //        geo.width = radius * 2;
        //        geo.height = radius * 2;
        //        geo.type = BBoxType.CIRCLE;
        //
        //        Circle circle = new Circle(radius, radius, radius, Color.BLUE);
        //
        //        EntityData data = new EntityData();
        //        data.geometry = geo;
        //        data.graphics = circle;
        //        data.speed = 5;
        return data;
    }

    public static EntityData getBullet() {
        EntityData data = holder.dataBullet.copy();
        data.graphics = getGraphics(data.visual, data.geometry);
        //        GeometryData geo = new GeometryData();
        //        geo.width = 10;
        //        geo.height = 1;
        //        geo.type = BBoxType.LINE;
        //
        //        Line line = new Line(0, 0, geo.width, 0);
        //        line.setStroke(Color.AQUA);
        //
        //        EntityData data = new EntityData();
        //        data.geometry = geo;
        //        data.graphics = line;
        //        data.speed = 10;
        return data;
    }

    public static EntityData getEnemyWanderer() {
        EntityData data = holder.dataEnemyWanderer.copy();
        data.graphics = getGraphics(data.visual, data.geometry);
        //        GeometryData geo = new GeometryData();
        //        geo.width = 40;
        //        geo.height = 40;
        //        geo.type = BBoxType.BOX;
        //
        //        Rectangle rect = new Rectangle(geo.width, geo.height);
        //        rect.setFill(null);
        //        rect.setStroke(Color.YELLOWGREEN);
        //
        //        EntityData data = new EntityData();
        //        data.geometry = geo;
        //        data.graphics = rect;
        //        data.speed = 5;
        return data;
    }

    public static EntityData getEnemySeeker() {
        EntityData data = holder.dataEnemySeeker.copy();
        data.graphics = getGraphics(data.visual, data.geometry);
        //        GeometryData geo = new GeometryData();
        //        geo.width = 30;
        //        geo.height = 30;
        //        geo.type = BBoxType.BOX;
        //
        //        Rectangle rect = new Rectangle(geo.width, geo.height);
        //        rect.setFill(null);
        //        rect.setStroke(Color.RED);
        //
        //        EntityData data = new EntityData();
        //        data.geometry = geo;
        //        data.graphics = rect;
        //        data.speed = 2;
        return data;
    }

    public static EntityData getPowerup() {
        EntityData data = holder.dataPowerup.copy();
        data.graphics = getGraphics(data.visual, data.geometry);
        //        GeometryData geo = new GeometryData();
        //        geo.width = 20;
        //        geo.height = 20;
        //        geo.type = BBoxType.BOX;
        //
        //        Rectangle rect = new Rectangle(geo.width, geo.height);
        //        rect.setFill(null);
        //        rect.setStroke(Color.WHITE);
        //
        //        EntityData data = new EntityData();
        //        data.geometry = geo;
        //        data.graphics = rect;
        //        data.speed = 2;
        return data;
    }

    private static Node getGraphics(VisualData data, GeometryData geo) {
        double width = geo.width;
        double height = geo.height;

        switch (data.type) {
            case ELLIPSE:
                Ellipse e = new Ellipse(width /2, height / 2, width / 2, height / 2);
                e.setFill(data.color);
                return e;
            case LINE:
                Line line = new Line(0, 0, width, 0);
                line.setStroke(data.color);
                return line;
            case RECTANGLE:
                Rectangle rect = new Rectangle(width, height);
                rect.setFill(null);
                rect.setStroke(data.color);
                return rect;
            case SPRITE:
                // TODO: impl
                break;
        }

        return null;
    }
}
