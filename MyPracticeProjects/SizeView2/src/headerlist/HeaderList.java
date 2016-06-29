package headerlist;
 
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
 
public class HeaderList extends VBox {
    Label headerTxt = null;
    CheckBox headerCheck = null;
    ChangeListener headerCheckListener = null;
    Boolean sizeRestricted = false;
    ListView list = null;
    TextField editBox = null;
    int id = -1;
 
    public HeaderList() {
        this("", false);
    }
 
    public HeaderList(String text) {
        this(text, false);
    }
   
    public HeaderList(String text, boolean showEditBox) {
        list = new ListView();
        Scene s = getScene();
        if ( s != null )
            s.getStylesheets().add("HeaderList.css");
 
        VBox.setVgrow(list, Priority.ALWAYS);
 
        // Create the header background
        Group header = new Group();
        final Rectangle headerBG = new Rectangle(0,0,list.getWidth(),25);  //resize
        headerBG.setArcWidth(6);
        headerBG.setArcHeight(6);
        Stop[] stops = new Stop[]{
            new Stop(0.0, Color.web("#4d6c8b")),
            new Stop(1.0, Color.web("#112138"))
        };
        headerBG.setFill(
                new LinearGradient(0.0, 0.0, 0.0, 1.0, true, null, stops));
        headerBG.setClip(new Rectangle(0,0, list.getWidth(), 20));  // resize
 
        // create the header text
        headerTxt = new Label(text);
        headerTxt.setTranslateX(5);
        headerTxt.setTranslateY(3);
        headerTxt.setPrefWidth(list.getWidth()-5);
        headerTxt.setFont(new Font("Arial Bold", 12));
        headerTxt.setTextFill(Color.WHITE);
        headerTxt.setTextAlignment(TextAlignment.LEFT);
        list.widthProperty().addListener( (observable, oldValue, newValue) -> {
            Double w = (Double)newValue;
            headerBG.setWidth(w);
            headerBG.setClip(new Rectangle(0,0, w, 20));
            headerTxt.setPrefWidth(w-5);
            headerCheck.setTranslateX(w-50);
            if ( w < 75 && headerCheck.isVisible() ) {
                // hide the checkbox due to small list width
                sizeRestricted = true;
                headerCheck.setVisible(false);
            }
            else if ( w > 75 && sizeRestricted ) {
                // restore the checkbox that was hidden due to width
                sizeRestricted = false;
                headerCheck.setVisible(true);
            }
        });
 
        // create the header checkbox
        headerCheck = new CheckBox("All");
        headerCheck.setVisible(false);
        headerCheck.setTranslateY(3);
        headerCheck.setFont(new Font("Arial Bold", 12));
        headerCheck.setTextFill(Color.WHITE);
        headerCheck.selectedProperty().addListener( (observable, oldVal, newVal) -> {
            if ( headerCheckListener != null ) {
                headerCheckListener.changed(observable, newVal, newVal);
            }
        });
       
        // create the header bar with all components
        header.getChildren().addAll(headerBG, headerTxt, headerCheck);
        setMinWidth(10);
       
        getChildren().add(header);
        
        if ( showEditBox == true ) {
            this.setSpacing(2);
           
            StackPane stack = new StackPane();
            stack.setAlignment(Pos.CENTER_RIGHT);
            editBox = new TextField();
            editBox.setMinHeight(20);
            editBox.setPromptText("Filter");
            editBox.textProperty().addListener( (observable, oldVal, newVal) -> {
                handleSearchByKey((String)oldVal, (String)newVal);
            });
           
            
            double tbHeight = 20;//      = bind original.boundsInLocal.height;
 
            double iconRadius    = tbHeight * 0.15;
 
            Group searchIcon = new Group();
            searchIcon.setTranslateX(-5);
            searchIcon.setRotate(45);
            Circle cir = new Circle();
            cir.setRadius(iconRadius);
            cir.setFill(Color.WHITE);
            cir.setStroke(Color.GREY);
            cir.setStrokeWidth(iconRadius * 0.5);
            Rectangle rect = new Rectangle();
            rect.setTranslateX(iconRadius);
            rect.setTranslateY(0 - iconRadius * 0.25);
            rect.setWidth( iconRadius * 1.5);
            rect.setHeight(iconRadius * 0.5);
            rect.setFill(Color.GRAY);
            searchIcon.getChildren().setAll(cir,rect);
            stack.getChildren().addAll(editBox,searchIcon);
           
            getChildren().add(stack);
        }
        getChildren().add(list);
    }
 
    public HeaderList(double width, double height) {
    }
   
    Rectangle space = null;
    public void addBottomSpace() {
        if ( space == null ) {
            space = new Rectangle(0,0,this.getWidth(),30);
            getChildren().add(space);
        }
    }
   
    public void removeBottomSpace() {
        if ( space != null ) {
            getChildren().remove(space);
            space = null;
        }
    }
   
    public void setPromptText(String txt) {
        if ( editBox != null )
            editBox.setPromptText(txt);
    }
   
    public void setHeaderCheckText(String txt) {
        if ( headerCheck != null )
            headerCheck.setText(txt);
    }
   
    public void setHeaderCheckVisible(Boolean visible) {
        if ( headerCheck != null )
            headerCheck.setVisible(visible);
    }
   
    public boolean isHeaderCheckSelected() {
        return headerCheck.isSelected();
    }
   
    public void setHeaderCheckListener( ChangeListener listener ) {
        this.headerCheckListener = listener;
    }
 
    public void setHeader(String text) {
        if ( headerTxt != null )
            headerTxt.setText(text);
    }
 
    public String getHeader() {
        return headerTxt.getText();
    }

    Object[] items = null;
   
    public void setItems(String[] items) {
        this.items = items;
        this.setItems(items);
    }
 
    ObservableList<Object> ol = null;
    public void setItems(Object[] items) {
        if ( list != null ) {
            ol = FXCollections.observableArrayList(items);
            this.items = items;
            list.setItems(ol);
            list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
    }
   
    public ListView getList() {
        return list;
    }
 
    public String getText() {
        return headerTxt.getText();
    }
   
    public int getListId() {
        return id;
    }
   
    public void setListId(int id) {
        this.id = id;
    }
   
    public void clearFilterText() {
        this.editBox.clear();
    }
   
    public void setFilterFocus() {
        this.editBox.requestFocus();
    }
   
    public void handleSearchByKey(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries
            // and start from the beginning
            list.setItems( ol );
        }
        
        // Break out all of the parts of the search text
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
        // Filter out the entries that don't contain the entered text
        ObservableList<Object> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            boolean match = true;
            String entryText = entry.toString();
            for ( String part: parts ) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
            if ( match ) {
                subentries.add(entry);
            }
        }
        list.setItems(subentries);
    }
   
    public void disable() {
        setDisable(true);
        setOpacity(.1);
    }
    public void enable() {
        setDisable(false);
        setOpacity(1);
    }
}
