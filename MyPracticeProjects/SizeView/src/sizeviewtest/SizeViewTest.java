package sizeviewtest;

import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import headerlist.HeaderList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sizeview.SizeView;

public class SizeViewTest extends Application {
    final static int CONTROLS = 5;
    
    Vector<HeaderList> nodes = new Vector<>();
    SizeView container = null;
        
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Manager");
        Group root = new Group();
        final Scene scene = new Scene(root, 800, 480);
        double hboxWidth = scene.getWidth() - 10 - 10;
        
        for ( int n = 0 ; n < CONTROLS; n++ ) {
            HeaderList list = new HeaderList("" , true);
            nodes.add( list );
            
            list.getList().setOnMouseClicked(me -> { 
                onListClicked(me,list);
            });
            
        }
        
        container = new SizeView();
        container.getChildren().setAll(nodes);
        container.setLayoutX(10);
        container.setLayoutY(10);
        container.setPrefWidth(hboxWidth);
        root.getChildren().add(container);
        
        scene.widthProperty().addListener( 
                (observable, oldValue, newValue) -> { 
                    Double w = (Double)newValue;
                    container.setPrefWidth( w - 20 );
                    container.layout();
                } );

        primaryStage.setScene(scene);
        primaryStage.show();

        // Start by showing contents of user's home directory
        populateList(System.getProperty("user.home"), null, 0);
    }
    
    private String constructPath(HeaderList list) {
        // Re-create full filepath
        String path = "";
        for ( HeaderList node: nodes ) {
            path = path + node.getHeader() + File.separator;
            if ( node == list ) 
                break;
        }
        
        return path;
    }
    
    private void onListClicked(MouseEvent me, HeaderList list) {
        // Since each list's header contains only its part of the full
        // file path, reconstruct the full path up to the selected list
        String path = constructPath( list );

        try {
            // Get the selected text wihin the list. Trim off the directory
            // decorators "<" and ">" if they exist
            //
            ListView parent = (ListView)me.getSource();
            String filename = (String)parent.getSelectionModel().getSelectedItem();
            if ( filename.startsWith("<") && filename.endsWith(">") ) {
                filename = filename.substring(1, filename.length()-1);
            }

            File file = new File(path + filename);
            if ( file.isDirectory() ) {
                // Add a new HeaderList and populate, if needed
                int currentList = 0;
                int clearListNode = -1;
                boolean addList = false;
                for ( HeaderList node: nodes ) {
                    if ( node == list) {
                        if ( currentList+1 < nodes.size() ) {
                            // Reuse an existing HeadlerList and
                            // clear all lists after this node
                            clearListNode = currentList+1;
                            populateList(path, filename, currentList+1);
                        }
                        else {
                            // set a flag to add a new list after loop
                            addList = true;
                        }
                    }
                    else if ( clearListNode > -1 ) {
                        if ( currentList > clearListNode ) {
                            node.setHeader(" ");
                            node.getList().getSelectionModel().clearSelection();
                            node.getList().setItems(null);
                        }
                    }
                    currentList++;
                }

                if ( addList ) {
                    HeaderList hlist = new HeaderList(path , true);
                    nodes.add(hlist);
                    
                    hlist.getList().setOnMousePressed(me1 -> { 
                        onListClicked(me1,hlist);
                    });
                    
                    container.getChildren().add( hlist );
                    currentList = nodes.size() - 1;
                    populateList(path, filename, currentList);
                }
            }
            else {
                // Check for doubleclick on file
                if ( me.getClickCount() > 1 ) {
                    // Display file details
                    Desktop.getDesktop().open(file);
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    public void populateList(String path, String filename, int listIndex) {
        HeaderList list = nodes.elementAt(listIndex);
        list.getList().getSelectionModel().clearSelection();
        
        if ( filename != null ) {
            list.setHeader(filename);
            if ( ! path.endsWith(File.separator) ) {
                path = path + File.separator;
            }
            path = path + filename;
        }
        else {
            list.setHeader(path);
        }
        
        // get file list
        List<String> results = new ArrayList<String>();
        File[] files = new File(path).listFiles();
        if ( files == null )
            return;

        for (File file : files) {
            if ( ! file.isHidden() ) {
                if ( file.isFile() ) {
                    results.add(file.getName());
                }
                else if ( file.isDirectory() ) {
                    results.add("<" + file.getName()+">" );
                }
            }
        }            

        list.setItems(results.toArray());
    }
    
}
