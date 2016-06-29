package application;

import application.MiniIconAnimationButton.AnimationType;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Rotate extends Application {
	@Override
	public void start(Stage primaryStage) {
		try
		{
			//Button button = new Button("Click");
			DropShadow dropShadow = new DropShadow();
			dropShadow.setOffsetX(5);
			dropShadow.setOffsetY(5);
			dropShadow.setColor(Color.CYAN);
			
			Rectangle rectangle = new Rectangle(200, 100, Color.ORANGE);
			rectangle.setStrokeWidth(5);
			rectangle.setStroke(Color.DARKGOLDENROD);
			rectangle.setArcWidth(30);
			rectangle.setArcHeight(30);
			rectangle.setEffect(dropShadow);
			
			Text text = new Text("My Rectangle");
			text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			text.setEffect(new Reflection());
			
			StackPane stackPane = new StackPane(rectangle, text);
			stackPane.setPrefHeight(200);
			stackPane.setPrefWidth(400);
			
			RotateTransition r = new RotateTransition(Duration.seconds(3), stackPane);
			r.setFromAngle(0);
			r.setToAngle(360);
			r.setCycleCount(2);
			r.setAutoReverse(true);
			
			Button rotateButton = new Button("Rotate");
			rotateButton.setPrefWidth(80);
			rotateButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					if(r.getStatus() == Animation.Status.RUNNING)
						r.pause();
					else
						r.play();
				}
			});

			VBox vbox = new VBox(50, stackPane, rotateButton);
			vbox.setAlignment(Pos.CENTER);
			vbox.setStyle("-fx-background-color: lightblue");
			
			Scene scene = new Scene(vbox, 400, 400);
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