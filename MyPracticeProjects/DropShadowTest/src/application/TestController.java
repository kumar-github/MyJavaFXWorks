package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class TestController implements Initializable
{
	@FXML private TextField txt;
	@FXML private ImageView img;
	@FXML private Label lbl;
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		Tooltip tip = new Tooltip("Testing");
		lbl.setTooltip(tip);
		txt.setTooltip(tip);
		
		Tooltip.install(img, new Tooltip("Another Testing"));
	}
}