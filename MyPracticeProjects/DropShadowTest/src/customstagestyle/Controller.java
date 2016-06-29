/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customstagestyle;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Ersel
 */
public class Controller implements Initializable {
    
    @FXML
    private Label label1;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private MenuItem item1;
    
    
    /**
     * @param event
     * Close Button
     */
    public void close(ActionEvent event){
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), pane1);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play(); 
    
    }
    /*
    Minimize Button
    */
    @FXML 
    private void minimize() throws IOException{
        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.setIconified(true);
    }
    /*
    @FXML 
    private void maximize() throws IOException{
        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.setMaximized(true);
    }
    */
    
    double x;
    double y;
    double posX;
    double posY;
    @FXML
    private void mousePressed() {
        label1.setCursor(Cursor.HAND);
        Point p = MouseInfo.getPointerInfo().getLocation();
        x=p.x;
        y=p.y;
        posX=pane1.getScene().getWindow().getX();
        posY=pane1.getScene().getWindow().getY();
        //System.out.println("pressed"+x);
    }
    @FXML
    private void mouseReleased(){
        label1.setCursor(Cursor.DEFAULT);
        
    }
    @FXML
    private void mouseDragged(){
        Point p2 = MouseInfo.getPointerInfo().getLocation();
        pane1.getScene().getWindow().setX(posX + p2.x-x);
        //System.out.println(p2.x);
        pane1.getScene().getWindow().setY(posY + p2.y-y);
    }
    @FXML
    private void newWindow() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AboutWindow.fxml"));
        Stage aboutStage = new Stage();
        Scene aboutScene = new Scene(root);
        aboutStage.setScene(aboutScene);
        aboutStage.initStyle(StageStyle.TRANSPARENT);
        aboutScene.setFill(Color.TRANSPARENT);
        aboutStage.getIcons().add(new Image("/customstagestyle/tick.png"));
        aboutStage.getScene().getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        aboutStage.setX(50);
        aboutStage.setY(50);
        aboutStage.show();
        
    }
    @FXML
    private void hide(){
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), pane1);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane1.getScene().getWindow().hide();
            }
        });
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play(); 
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
