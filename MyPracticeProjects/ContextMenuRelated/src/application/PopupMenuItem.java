package application;
 
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
 
/**
 *
 * @author Lawrence PremKumar
 */
public class PopupMenuItem extends Group{
 
    public Label popUpMenuItem  ;
    final Rectangle bgRect = new Rectangle();
 
    PopupMenuItem(){
        popUpMenuItem = new Label();
    }
    PopupMenuItem(String menuName ,  javafx.scene.control.ContentDisplay labelPos ,ImageView imgView){
        popUpMenuItem = new Label(menuName,imgView);
        popUpMenuItem.setContentDisplay(labelPos);
        popUpMenuItem.setTextFill(Color.WHITE);
    }
 
    public Group createPopupMenuItem(){
 
        /*this.setOnMouseEntered(new EventHandler<Event>()
		{
            public void handle(MouseEvent event) {
                bgRect.setWidth(popUpMenuItem.getWidth()+2);
                bgRect.setHeight(popUpMenuItem.getHeight()+2);
                bgRect.setArcHeight(5.0);
                bgRect.setArcWidth(5.0);
                bgRect.setFill(Color.BLACK);
                bgRect.setOpacity(0.5);
           }
		}); */
        		
        		 
        /*this.setOnMouseExited(new EventHandler() {
            public void handle(MouseEvent event) {
                  bgRect.setOpacity(0.0);
                  System.out.println("Mouse exited \n");
            }
        });*/
 
        this.getChildren().addAll(popUpMenuItem,bgRect);
        return this;
    }
}