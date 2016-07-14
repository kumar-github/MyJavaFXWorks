package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.StatusBar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainWindowController implements Initializable
{
	@FXML
	private ImageView homeImageView;
	@FXML
	private ImageView minimizeImageView;
	@FXML
	private ImageView maximizeOrRestoreImageView;
	@FXML
	private ImageView closeImageView;
	@FXML
	private BorderPane mainWindowBorderPane;
	@FXML
	private StatusBar mainWindowStatusBar;
	
	private String APPLICATION_TITLE = "Application Title New"; 

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	private boolean isInMaximizedState = true;
	@FXML
	private void handleMaximizeOrRestoreImageViewClick()
	{
		/* We may be here bcoz user clicked maximize of restore. so first find out. */
		if(isInMaximizedState)
		{
			removeCSSStyleFromNode(maximizeOrRestoreImageView, "mainWindowViewMaximizeImageViewStyle");
			addCSSStyleToNode(maximizeOrRestoreImageView, "mainWindowViewRestoreImageViewStyle");
		}
		//else if(isInRestoredState)
		else
		{
			removeCSSStyleFromNode(maximizeOrRestoreImageView, "mainWindowViewRestoreImageViewStyle");
			addCSSStyleToNode(maximizeOrRestoreImageView, "mainWindowViewMaximizeImageViewStyle");
		}
	}

	private void removeCSSStyleFromNode(Node aNode, String cssStyle)
	{
		aNode.getStyleClass().remove(cssStyle);
	}

	private void addCSSStyleToNode(Node aNode, String cssStyle)
	{
		aNode.getStyleClass().add(cssStyle);
	}
	
	private void makeSceneDraggable()
	{
		mainWindowBorderPane.setOnMousePressed(event ->
		{
			//xOffset = primaryStage.getX() - event.getScreenX();
			//yOffset = primaryStage.getY() - event.getScreenY();
			//primaryScene.setCursor(Cursor.MOVE);
		});
		
		mainWindowBorderPane.setOnMouseReleased(event ->
		{
			//primaryScene.setCursor(Cursor.HAND);
		});
		
		mainWindowBorderPane.setOnMouseDragged(event ->
		{
			//primaryStage.setX(event.getScreenX() + xOffset);
			//primaryStage.setY(event.getScreenY() + yOffset);
		});
	}
	
	public String getAPPLICATION_TITLE()
	{
		return APPLICATION_TITLE;
	}
}