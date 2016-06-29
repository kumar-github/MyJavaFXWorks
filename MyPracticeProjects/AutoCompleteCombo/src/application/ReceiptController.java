package application;
 
 
 
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
 
 
 
public class ReceiptController implements Initializable {
 
 
 
    @FXML
 
    private TextField txtAuto;
 
 
 
    @Override
 
    public void initialize(URL location, ResourceBundle resources) {
 
        BatchService service = new BatchService();
 
 
        TextFields.bindAutoCompletion(txtAuto, t-> {
 
            return service.getSuggestions("code", t.getUserText());
 
        });
 
    }
 
}