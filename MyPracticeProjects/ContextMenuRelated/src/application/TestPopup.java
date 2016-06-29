package application; 
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle; 
 
/** 
 * 
 * @author Lawrence PremKumar 
 */
 
public class TestPopup extends Application {
 
    Stage stage1 = null;
    PopupMenuItem menu1 = null;
    PopupMenuItem menu2 = null;
    PopupMenuItem menu3 = null;
    PopupMenuItem menu4 = null;
 
    public static void main(String[] args) {
        Application.launch(TestPopup.class, args);
    }
 
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        ImageView imgView = new ImageView();
        imgView.setFitHeight(40.0);
        imgView.setFitWidth(40.0);
        Image img = new Image(this.getClass().getResourceAsStream("duke.jpg"));
        imgView.setImage(img);
        Image img1 = new Image(this.getClass().getResourceAsStream("duke.jpg"));
        ImageView imgView1 = new ImageView();
        imgView1.setFitHeight(40.0);
        imgView1.setFitWidth(40.0);
        imgView1.setImage(img1);
        Image img2 = new Image(TestPopupControl.class.getResourceAsStream("duke.jpg"));
        ImageView imgView2 = new ImageView();
        imgView2.setFitHeight(40.0);
        imgView2.setFitWidth(40.0);
        imgView2.setImage(img2);
        menu1 = new PopupMenuItem("One", ContentDisplay.TOP, imgView);
        menu2 = new PopupMenuItem("Two", ContentDisplay.TOP, imgView1);
        menu3 = new PopupMenuItem("Three", ContentDisplay.TOP, imgView2);
        menu1.setOnMouseClicked(new MouseEventHandler());
        menu2.setOnMouseClicked(new MouseEventHandler());
        menu3.setOnMouseClicked(new MouseEventHandler());
        final java.util.ArrayList popupArray = new java.util.ArrayList();
        popupArray.add(menu1);
        popupArray.add(menu2);
        popupArray.add(menu3);
        Rectangle bgRect = new Rectangle();
        bgRect.setWidth(200);
        bgRect.setHeight(130);
        bgRect.setArcHeight(5.0);
        bgRect.setArcWidth(5.0);
        bgRect.setFill(Color.RED);
        final PopupMenu popup = new PopupMenu(5, 5);
        popup.setHgap(5.0);
        popup.setLayoutX(5.0);
        popup.setLayoutY(7.0);
        popup.setPrefColumns(3);
        popup.setPrefRows(1);
        popup.add(popupArray);
        bgRect.setOnMouseClicked(new EventHandler() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    stage1 = new Stage(StageStyle.TRANSPARENT);
                    Group rootGroup = new Group();
                    DropShadow ds = new DropShadow();
                    rootGroup.setEffect(ds);
                    ds.setOffsetY(3.0f);
                    ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
                    Scene scene = new Scene(rootGroup);
                    scene.setFill(null);
                    Rectangle r = new Rectangle();
                    r.setWidth(140);
                    r.setHeight(70);
                    Stop[] stops = new Stop[]{new Stop(0, Color.web("#1F6592")), new Stop(1, Color.web("#80CAFA"))};
                    LinearGradient lg = new LinearGradient(125.0, 0.0, 225.0, 0.0, true, CycleMethod.NO_CYCLE, stops);
                    r.setFill(lg);
                    r.setArcHeight(10.0);
                    r.setArcWidth(10.0);
                    rootGroup.getChildren().addAll(r, popup);
                    stage1.setScene(scene);
                    stage1.setX(event.getScreenX());
                    stage1.setY(event.getScreenY());
                    stage1.setVisible(true);
                }
            }
        });
        root.getChildren().addAll(bgRect);
        primaryStage.setScene(scene);
        primaryStage.setVisible(true);
    }
 
    public class MouseEventHandler implements EventHandler {
        public void handle(MouseEvent me) {
            if (me.getSource().equals(menu1)) { 
                // Action specific to menu1 
                stage1.close(); 
            } 
            if(me.getSource().equals(menu2)){ 
                // Action specific to menu2 
                stage1.close(); 
            } 
            if(me.getSource().equals(menu3)){ 
                // Action specific to menu3 
                stage1.close(); 
            } 
        } 
    } 
}