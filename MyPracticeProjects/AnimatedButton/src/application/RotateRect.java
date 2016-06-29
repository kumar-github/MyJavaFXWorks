package application;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RotateRect extends Application {
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);
        stage.setScene(scene);

        Rectangle rect = new Rectangle (100, 40, 100, 100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);
    
        RotateTransition rt = new RotateTransition(Duration.seconds(3), rect);
        rt.setByAngle(180);
        rt.setAutoReverse(true);
    
        rt.play();
        
        root.getChildren().add(rect);

        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
