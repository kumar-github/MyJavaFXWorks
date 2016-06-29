/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.classBaju;
import model.navigasi;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author herudi
 */
public class UserLoginController implements Initializable {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private ComboBox<String> cmbAdmin;
    navigasi nav = new navigasi();
    classBaju thisBaju = new classBaju();
    Stage stage;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbAdmin.getItems().addAll("Rudy Sahimar","Herudi");
    }    

    @FXML
    private void aksiLogin(ActionEvent event) throws IOException {
        if(cmbAdmin.getSelectionModel().isEmpty()){
            Dialogs.create()
                    .owner(stage)
                    .title("Information")
                    .masthead("Informasi Data Baju")
                    .message("Admin Harus Dipilih")
                    .showInformation();
        }else if(txtUser.getText().equals("@herudi45") && txtPass.getText().equals("qwerty")){
            daftarBajuController.comboBaju = cmbAdmin.getValue();
            Node node = (Node) event.getSource();
            Stage st = (Stage) node.getScene().getWindow();
            Stage showDECORATED = new Stage(StageStyle.DECORATED);  
            showDECORATED.setScene(createScene(loadMainPane())); 
            showDECORATED.setTitle("Rudy Sahimar");
            showDECORATED.centerOnScreen();
            showDECORATED.show();
            st.close();
        }else{
            Dialogs.create()
                    .owner(stage)
                    .title("Information")
                    .masthead("Informasi Data Baju")
                    .message("Username Dan Password Tidak Cocok")
                    .showInformation();
            txtUser.clear();
            txtPass.clear();
            txtUser.requestFocus();
        }

    }
    private Pane loadMainPane() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(nav.getDaftarBaju()));
        daftarBajuController daftarBaju = loader.getController();
        navigasi.setStageController(daftarBaju);
        navigasi.loadStageDaftarListBaju(nav.getTextEditor());
        navigasi.loadStageTambahBaju(nav.getTambahBaju());
        return mainPane;
        
    }
    
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(getClass().getResource("/css/baju.css").toExternalForm());        
        return scene;
    }
    
    @FXML
    private void aksiClose(ActionEvent event){
        Platform.exit();
    }
}
