package sizeview;

import java.util.Vector;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * @author ericjbruno
 */
public class SizeView extends HBox {
    boolean ignoreChanges = false;
        
    public SizeView() {
        setManaged(true);
        
        // Listen for changes to the pane's child nodes
        ObservableList<Node> children = this.getChildren();
        children.addListener((ListChangeListener<Node>) (changed) -> {
                if ( ! ignoreChanges ) {
                    Vector<Node> nodes = new Vector<>();
                    while (changed.next()) {
                        if (changed.wasPermutated()) {
                            System.out.println("Children permutated");
                        } 
                        else if (changed.wasUpdated()) {
                            System.out.println("Children updated");
                        } 
                        else {
                            System.out.println("Children removed/added");
                            for (Node remitem : changed.getRemoved()) {
                                //remitem.remove(Outer.this);
                            }
                            for (Node additem : changed.getAddedSubList()) {
                                nodes.add(additem);
                            }
                        }
                    }

                    if ( nodes != null && nodes.size() > 0 ) {
                        Node[] nodeArray = new Node[ nodes.size() ];
                        nodeArray = (Node[])nodes.toArray(nodeArray);
                        divide(nodeArray);
                    }
                }
        });
    }
    
    protected void divide(Node[] nodes) {
        // There needs to be at least one control in the array
        if ( nodes.length == 0 )
            return;
        
        ignoreChanges = true;

        // Place each control (with a divider) into the hbox
        if ( nodes.length == 1 ) {
            Node node = nodes[0];
            // Add a divider first, and give it the control
            // to the left to resize when moved
            int lastChildIdx = getChildren().size() - 1;
            Divider div = new Divider( this, getChildren().get(lastChildIdx) );
            div.setHeight( this.getHeight() );
            getChildren().add( lastChildIdx, div );
            HBox.setHgrow(node, Priority.ALWAYS);
        }
        else {
            for ( int i = nodes.length-1; i > 0;  i-- ) {
                Node node = nodes[i];
                if ( i > 0 ) {
                    // Add a divider first, and give it the control
                    // to the left to resize when moved
                    Divider div = new Divider( this, nodes[i-1] );
                    div.setHeight( this.getHeight() );
                    getChildren().add( i, div );
                }

                HBox.setHgrow(node, Priority.ALWAYS);
            }
        }
        
        ignoreChanges = false;
    }

}
