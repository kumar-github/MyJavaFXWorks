/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daftarhargabaju;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author herudi
 */
public class DaftarHargaBaju extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/userLogin.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(DaftarHargaBaju.class.getResource("/css/baju.css").toExternalForm());
        stage.setScene(scene);
        scene.setFill(new Color(0, 0, 0, 0));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
