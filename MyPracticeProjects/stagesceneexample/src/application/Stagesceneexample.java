/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Saravanababu
 */
public class Stagesceneexample extends Application {

    @Override
    public void start(final Stage primaryStage) {
        Label l1 = new Label("Stage Style & Scene Graph Example");
        l1.setFont(Font.font("Times New Roman", 30));
        Button b1 = new Button();
        b1.setText("StageStyle.DECORATED");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = new Stage(StageStyle.DECORATED);
                s.initModality(Modality.APPLICATION_MODAL);
                s.show();
                FlowPane r = new FlowPane();
                Label label = new Label("DECORATED Stage Style");
                r.setAlignment(Pos.CENTER);
                r.getChildren().add(label);
                s.setScene(new Scene(r, 400, 250));
            }
        });
        Button b2 = new Button();
        b2.setText("StageStyle.TRANSPARENT");
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage s = new Stage(StageStyle.TRANSPARENT);
                s.initModality(Modality.APPLICATION_MODAL);
                s.show();
                FlowPane r = new FlowPane();
                Button bt = new Button("Close Me");
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        s.close();
                    }
                });
                Label label = new Label("TRANSPARENT Stage Style");
                r.setAlignment(Pos.CENTER);
                r.getChildren().add(label);
                r.getChildren().add(bt);
                s.setScene(new Scene(r, 300, 250, Color.BURLYWOOD));
            }
        });
        Button b3 = new Button();
        b3.setText("StageStyle.UNDECORATED");
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage s = new Stage(StageStyle.UNDECORATED);
                s.initModality(Modality.APPLICATION_MODAL);
                s.show();
                FlowPane r = new FlowPane();
                Button bt = new Button("Close Me");
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        s.close();
                    }
                });
                Label label = new Label("UNDECORATED Stage Style");
                r.setAlignment(Pos.CENTER);
                r.getChildren().add(label);
                r.getChildren().add(bt);
                s.setScene(new Scene(r, 300, 250, Color.BURLYWOOD));
            }
        });

        Button b4 = new Button();
        b4.setText("StageStyle.UTILITY");
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = new Stage(StageStyle.UTILITY);
                s.initModality(Modality.APPLICATION_MODAL);
                s.show();
                FlowPane r = new FlowPane();
                Label label = new Label("UTILITY Stage Style");
                r.setAlignment(Pos.CENTER);
                r.getChildren().add(label);
                s.setScene(new Scene(r, 400, 250));
            }
        });
        VBox vbox = new VBox(50);
        vbox.setAlignment(Pos.CENTER);
        VBox root = new VBox(30);
        root.getChildren().addAll(b1, b2, b3, b4);
        root.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(vbox, 550, 400, Color.CORNSILK);
        vbox.getChildren().addAll(l1, root);
        primaryStage.setTitle("www.javafxapps.in");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
