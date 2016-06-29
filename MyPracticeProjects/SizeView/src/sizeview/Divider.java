package sizeview;

import java.util.List;
import java.util.Vector;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Divider extends Rectangle {
    // A static counter for IDs
    static int DIVIDER_ID_INDEX = 1;
    
    // The unique ID of this divider
    public int id = DIVIDER_ID_INDEX++;

    public double SEP_WIDTH = 2;
    
    // The node this divider controls
    public final Node node;
    
    // All nodes left of our control
    Vector<Node> leftNodes = new Vector<Node>();
    
    // All nodes right of our control
    Vector<Node> rightNodes = new Vector<Node>();

    // The parent HBox container
    HBox parent;
    
    // When true, will never resize any controls except for the one directly
    // to the left of the divider (the controlling node)
    boolean preserveResize = false; 
    boolean alwaysProportional = true;
    
    // When true, will maintain size of left and right controls, except when
    // dragging right, where all right most controls will be reset to share space
    boolean preserveAdajacent = true; 
    
    // Set to true when user drags to the right
    boolean draggingRight = false; 
    
    // This is a flag that is reset with each mouse click to indicate
    // first time processing each time the user clicks on a divider
    boolean oneTimeProc = false;
    int w = 0;
    public Divider(HBox parent, Node c) {
        this.node = c;
        this.parent = parent;

        setWidth(SEP_WIDTH);
        setStroke(Color.TRANSPARENT);
        setFill(Color.TRANSPARENT);
        
        setOnMouseEntered( me -> {
            setCursor(Cursor.W_RESIZE);
        });
        
        setOnMouseExited(me -> {
            setCursor(Cursor.DEFAULT);
        });
        
        setOnMousePressed(me -> {
            processChildren();

            // Lock the lock components
            for ( Node node: leftNodes ) {
                if ( node instanceof Control ) {
                    Control control = (Control)node;
                    double w = control.getWidth();
                    control.setMinWidth(w);
                    control.setMaxWidth(w);
                }
                else if ( node instanceof VBox ) {
                    VBox vb = (VBox)node;
                    double w = vb.getWidth();
                    vb.setMinWidth(w);
                    vb.setMaxWidth(w);
                    //vb.setPrefWidth(w);
                }
            }

            oneTimeProc = false;
        });
        
        
        setOnMouseDragged(mouseEvt -> {
            if ( alwaysProportional ) {
                // Figure out how much the mouse has been dragged, 
                // divide by the total number of compoenents to
                // the right of the divider, and add/subtract an equal
                // number of pixels from each of them to keep their
                // sizes proportional
                double[] widths = new double[rightNodes.size()];

                int lw = 0;
                for ( Node node: rightNodes ) {
                    if ( node instanceof Control) {
                        Control control = (Control)node;
                        widths[lw++] = control.getWidth();
                    }
                    else if ( node instanceof VBox ) {
                        VBox vb = (VBox)node;
                        widths[lw++] = vb.getWidth();
                    }
                }

                double pixels = mouseEvt.getX();
                if ( pixels > 0 ) {
                    for ( int i = 0; i < mouseEvt.getX(); i++ ) {
                        if ( widths == null || w < 0 || w >= widths.length || widths.length == 0) 
                            continue;
                        
                        // Never shrink a control below a min width
                        if ( widths[w] > 10 ) {
                            widths[w]--;

                        }
                        else {
                            i--;

                            // check that all right components aren't
                            // at their min widths
                            int resizable = 0;
                            for ( lw = 0 ; lw < widths.length; lw++ ) {
                                if ( widths[lw] > 10 )
                                    resizable++;
                            }

                            if ( resizable == 0 ) {
                                return;
                            }
                        }
                        w++;
                        if ( w >= rightNodes.size() )
                            w = 0;
                    }
                }
                else {
                    for ( int i = (int)mouseEvt.getX(); i < 0; i++) {
                        widths[w++]++;
                        if ( w >= rightNodes.size() )
                            w = 0;
                    }
                }

                lw = 0;
                for ( Node node: rightNodes ) {
                    double newWidth = widths[lw++];
                    if ( node instanceof Control) {
                        Control control = (Control)node;
                        control.setMinWidth(10);
                        control.setPrefWidth(newWidth);
                    }
                    else if ( node instanceof VBox ) {
                        VBox vb = (VBox)node;
                        vb.setMinWidth(10);
                        vb.setPrefWidth(newWidth);
                    }
                }
            }
            else {
                draggingRight = false;

                // A positive relative X position indicates the user 
                // is dragging to the right. Do this once per click
                if ( oneTimeProc == false && mouseEvt.getX() >= 1 ) {
                    draggingRight = true;
                    oneTimeProc = true;
                }

                if ( draggingRight ) {
                    if ( ! preserveResize ) {
                        for ( Node node: rightNodes ) {
                            if ( node instanceof Control) {
                                Control control = (Control)node;
                                control.setMinWidth(Control.USE_COMPUTED_SIZE);
                                control.setMaxWidth(Control.USE_COMPUTED_SIZE);
                            }
                            else if ( node instanceof VBox ) {
                                VBox vb = (VBox)node;
                                vb.setMinWidth(Control.USE_COMPUTED_SIZE);
                                vb.setMaxWidth(Control.USE_COMPUTED_SIZE);
                            }
                        }
                    }
                }
            }

            if ( node instanceof Control ) {
                Control control = (Control)node;
                double newWidth = control.getWidth() + mouseEvt.getX();
                if ( newWidth > control.getWidth() ) {
                    control.setMaxWidth(newWidth);
                    control.setMinWidth(newWidth);
                }
                else {
                    control.setMinWidth(newWidth);
                    control.setMaxWidth(newWidth);
                }
            }
            else if ( node instanceof VBox ) {
                VBox vb = (VBox)node;
                double newWidth = vb.getWidth() + mouseEvt.getX();
                if ( newWidth > vb.getWidth() ) {
                    vb.setMaxWidth(newWidth);
                    vb.setMinWidth(newWidth);
                }
                else {
                    vb.setMinWidth(newWidth);
                    vb.setMaxWidth(newWidth);
                }
            }
        });
        
        setOnMouseReleased(me -> {
            draggingRight = false;
        });
        
        // Set this divider's height to always equal its parent's height
        parent.heightProperty().addListener( (observable, oldValue, newValue) -> {
            Double height = (Double)newValue - 1;
            setHeight(height);
        });
    }
    
    private void processChildren() {
        leftNodes.clear();
        rightNodes.clear();
        
        boolean addLock = true;
        List<Node> children = parent.getChildren();
        for ( Node node: children ) {
            System.out.println(node+"");
            if ( node == this.node ) {
                addLock = false;
            }
            else if ( node instanceof VBox || node instanceof Control ) {
                if ( addLock )
                    leftNodes.add(node);
                else
                    rightNodes.add(node);
            }
        }
        System.out.println("");
      
    }
}
