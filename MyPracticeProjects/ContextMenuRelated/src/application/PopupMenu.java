package application; 
import javafx.scene.layout.TilePane; 
  
 /*
  * @author Lawrence PremKumar 
  *
 */
  final public class PopupMenu extends TilePane {
    PopupMenu() {
    }
    PopupMenu(double hgap, double vgap) {
        super(hgap, vgap);
    }
 
    public boolean add(java.util.ArrayList popupArray) {
        boolean flag = true;
        for (int i = 0; i <= popupArray.size();i++) { 
    flag = this.getChildren().add(popupArray.get(i).createPopupMenuItem());
            if (!flag) {
                break;
            } else {
                continue;
            }
        }
        return flag;
    }
}