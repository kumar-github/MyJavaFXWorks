package com.almasb.fxwars.entity;

import java.util.Random;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import com.almasb.fxwars.Config;

public class AnimationData {
    public Group node;
    public ParallelTransition animation;

    private static final Random random = new Random();

    private AnimationData() {}

    public static AnimationData createPlayerDeath(double x, double y) {
        AnimationData data = createBase(x, y);

        Color c = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        DropShadow shadow = new DropShadow(10, Color.YELLOW);
        shadow.setInput(new Glow());

        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(1, c);
            circle.setEffect(shadow);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), circle);
            double angle = Math.toRadians(i*12);
            tt.setByX(Math.cos(angle) * 50);
            tt.setByY(Math.sin(angle) * 50);

            SequentialTransition st = new SequentialTransition(tt);

            data.node.getChildren().add(circle);
            data.animation.getChildren().add(st);
        }

        return data;
    }

    public static AnimationData createBulletDeath(double x, double y) {
        AnimationData data = createBase(x, y);

        Color c = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        DropShadow shadow = new DropShadow(10, Color.YELLOW);
        shadow.setInput(new Glow());

        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(1, c);
            circle.setEffect(shadow);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), circle);
            tt.setByX(random.nextDouble() * 100 - 50);
            tt.setByY(random.nextDouble() * 200);

            SequentialTransition st = new SequentialTransition(tt);

            data.node.getChildren().add(circle);
            data.animation.getChildren().add(st);
        }

        return data;
    }

    public static AnimationData createEnemyDeath(double x, double y, double w, double h) {
        AnimationData data = createBase(x, y);

        Color c = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));

        Line[] lines = new Line[] {
                // top
                new Line(0, 0, w, 0),
                // right
                new Line(w, 0, w, h),
                // bottom
                new Line(0, h, w, h),
                // left
                new Line(0, 0, 0, h)
        };

        for (Line line : lines) {
            line.setStroke(c);

            RotateTransition rt = new RotateTransition(Duration.seconds(1), line);
            rt.setToAngle(random.nextDouble() * 90);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), line);
            tt.setByY(random.nextDouble() * 100 + 50);

            ParallelTransition p = new ParallelTransition(rt, tt);

            data.node.getChildren().add(line);
            data.animation.getChildren().add(p);
        }

        return data;
    }

    public static AnimationData createPowerupDeath(double x, double y, double w, double h) {
        AnimationData data = createBase(x, y);

        Rectangle rectangle = new Rectangle(w, h);
        rectangle.setFill(null);
        rectangle.setStroke(Color.WHITE);

        ScaleTransition st = new ScaleTransition(Duration.seconds(1), rectangle);
        st.setFromX(1);
        st.setToX(0);
        st.setFromY(1);
        st.setToY(0);

        data.node.getChildren().add(rectangle);
        data.animation.getChildren().add(st);

        return data;
    }

    public static AnimationData createGameOver() {
        AnimationData data = createBase(Config.getInstance().getAppWidth() / 2 - 100,
                Config.getInstance().getAppHeight() / 2);

        Text text = new Text("GAME OVER");
        text.setFill(Color.WHITE);
        text.setFont(Font.font(30));

        ScaleTransition st = new ScaleTransition(Duration.seconds(1), text);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(2);
        st.setToY(2);
        st.setAutoReverse(true);
        st.setCycleCount(Transition.INDEFINITE);

        data.node.getChildren().add(text);
        data.animation.getChildren().add(st);

        return data;
    }

    private static AnimationData createBase(double x, double y) {
        AnimationData data = new AnimationData();

        Group animRoot = new Group();
        animRoot.setTranslateX(x);
        animRoot.setTranslateY(y);
        ParallelTransition animation = new ParallelTransition();

        data.node = animRoot;
        data.animation = animation;
        data.animation.setOnFinished(event -> {
            Group group = (Group)data.node.getParent();
            if (group != null)
                group.getChildren().remove(data.node);
        });
        return data;
    }
}
