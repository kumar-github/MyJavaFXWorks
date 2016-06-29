package application;
	
import java.awt.Color;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ButtonGlow extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			final Image image = new Image(getClass().getResourceAsStream("bell.png"));
			final Button dummyButton = new Button("Dummy Button");
		    
			final Button button1 = new Button();
		    button1.setText("Title");
		    button1.setGraphic(new ImageView(image));
		    button1.setContentDisplay(ContentDisplay.TOP);
		    button1.setTextAlignment(TextAlignment.CENTER);
		 
		    final Glow glow = new Glow();
		    glow.setLevel(0.1);
		    button1.setEffect(glow);
		    
		    final Button button2 = new Button();
		    button2.setText("Title");
		    button2.setGraphic(new ImageView(image));
		    button2.setContentDisplay(ContentDisplay.TOP);
		    button2.setTextAlignment(TextAlignment.CENTER);
		    
		    final Timeline timeline = new Timeline();
		    timeline.setCycleCount(Timeline.INDEFINITE);
		    timeline.setAutoReverse(true);
		    final KeyValue kv = new KeyValue(glow.levelProperty(), 0.3);
		    final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
		    timeline.getKeyFrames().add(kf);
		    timeline.play();
		 
		    final FlowPane root = new FlowPane();
		    root.setPadding(new Insets(10, 10, 10, 10));
		    root.getChildren().addAll(dummyButton, button1, button2);
		 
		    final Scene scene = new Scene(root, 300, 250);

		 
		    primaryStage.setTitle("Hello Glowing Button!");
		    primaryStage.setScene(scene);
		    primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
