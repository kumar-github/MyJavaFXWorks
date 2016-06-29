package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChatWindow extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT); // here it is

        Group rg = new Group();
        Scene scene = new Scene(rg, 500, 500, Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Rectangle r = new Rectangle(5, 5, stage.getWidth() - 10, stage.getHeight() - 10);
        //BorderPane r = new BorderPane();
        //r.setCenter(new Button("Test"));
        r.setFill(Color.STEELBLUE);
        r.setEffect(new DropShadow());
        rg.getChildren().add(r);
    }

    public static void main(String[] args) {
        launch();
    }
}