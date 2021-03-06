package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdvancedStageSample extends Application
{
	//variables for storing initial position of the stage at the beginning of drag
	private double initX;
	private double initY;

    private void init(Stage primaryStage)
    {

        Group root = new Group();

        primaryStage.setScene(new Scene(root));

         //create a button for initializing our new stage

        Button button = new Button("Create a Stage");

        button.setStyle("-fx-font-size: 24;");

        button.setDefaultButton(true);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent t) {

                // INITIALISATION OF THE STAGE/SCENE

                 

                //create stage which has set stage style transparent

                final Stage stage = new Stage(StageStyle.TRANSPARENT);

                //create root node of scene, i.e. group

                Group rootGroup = new Group();

                //create scene with set width, height and color

                Scene scene = new Scene(rootGroup, 200, 200, Color.TRANSPARENT);

                //set scene to stage

                stage.setScene(scene);

                //center stage on screen

                stage.centerOnScreen();

                //show the stage

                stage.show();

 

                // CREATION OF THE DRAGGER (CIRCLE)

           

                //create dragger with desired size

                //Circle dragger = new Circle(100, 100, 100);
                Rectangle dragger = new Rectangle(500, 500);
                

                //fill the dragger with some nice radial background

                dragger.setFill(new RadialGradient(-0.3, 135, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {

                    new Stop(0, Color.DARKGRAY),

                    new Stop(1, Color.BLACK)

                 }));

 

 

                //when mouse button is pressed, save the initial position of screen

                rootGroup.setOnMousePressed(new EventHandler<MouseEvent>() {

                    public void handle(MouseEvent me) {

                        initX = me.getScreenX() - stage.getX();

                        initY = me.getScreenY() - stage.getY();

                    }

                });

 

                //when screen is dragged, translate it accordingly

                rootGroup.setOnMouseDragged(new EventHandler<MouseEvent>() {

                    public void handle(MouseEvent me) {

                        stage.setX(me.getScreenX() - initX);

                        stage.setY(me.getScreenY() - initY);

                    }

                });

                

                // CREATE MIN AND CLOSE BUTTONS

                //create button for closing application

                Button close = new Button("Close me");

                close.setOnAction(new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {

                        //in case we would like to close whole demo

                        //javafx.application.Platform.exit();

 

                        //however we want to close only this instance of stage

                        stage.close();

                    }

                });

 

                //create button for minimalising application

                Button min = new Button("Minimize me");

                min.setOnAction(new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {

                        stage.setIconified(true);

                    }

                });

 

 

                // CREATE SIMPLE TEXT NODE

                Text text = new Text("JavaFX"); //20, 110,

                text.setFill(Color.WHITESMOKE);

                text.setEffect(new Lighting());

                text.setBoundsType(TextBoundsType.VISUAL);

                text.setFont(Font.font(Font.getDefault().getFamily(), 50));

 

                // USE A LAYOUT VBOX FOR EASIER POSITIONING OF THE VISUAL NODES ON SCENE

                VBox vBox = new VBox();

                vBox.setSpacing(10);

                vBox.setPadding(new Insets(60, 0, 0, 20));

                vBox.setAlignment(Pos.TOP_CENTER);

                vBox.getChildren().addAll(text, min, close);

 

                //add all nodes to main root group

                rootGroup.getChildren().addAll(dragger, vBox);

            }

        });

 

        root.getChildren().add(button);

    }

 

    @Override public void start(Stage primaryStage) throws Exception {

        init(primaryStage);

        primaryStage.show();

    }   
    
	public static void main(String[] args) {
		launch(args);
	}
}