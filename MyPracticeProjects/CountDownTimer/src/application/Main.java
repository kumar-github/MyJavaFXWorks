package application;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private Stage stage;

    public static void main(String[] args)
    {
        Application.launch(Main.class,(java.lang.String[]) null);
    }

    @Override public void start(Stage primaryStage) throws Exception
    {
        stage = primaryStage;
/*
        Parent root = FXMLLoader.load(getClass().getResource("CountdownTimer.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Countdown Timer");
        stage.setScene(new Scene(root,300,275));
*/

        gotoCustomControl();
    }

    private void gotoCustomControl()
    {
        try
        {
            Controller c = (Controller) replaceSceneContent("CountdownTimer.fxml");
            c.setApp(this,stage);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            c.setFocus();
        }
        catch(Exception ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("getCause: " + ex.getCause());
            System.out.println("getSuppressed: " + ex.getSuppressed());
            System.exit(ex.hashCode());
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try
        {
            InputStream in = Main.class.getResourceAsStream(fxml);
            page = loader.load(in);
            Scene scene = new Scene(page,180,300);
            stage.setScene(scene);
            stage.sizeToScene();
        }
        catch(Exception e)
        {

        }
        return (Initializable) loader.getController();
    }

}
