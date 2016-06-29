package sizeviewtest;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;

import java.awt.Desktop;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import headerlist.HeaderList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import sizeview.SizeView;

public class SizeViewTest extends Application {
    final static int CONTROLS = 5;
    
    Vector<HeaderList> nodes = new Vector<>();
    SizeView container = null;
    Group root;
        
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Manager");
        root = new Group();
        final Scene scene = new Scene(root, 800, 480);
        double hboxWidth = scene.getWidth() - 10 - 10;
        
        for ( int n = 0 ; n < CONTROLS; n++ ) {
            HeaderList list = new HeaderList("" , true);
            nodes.add( list );
            
            list.getList().setOnMouseClicked(me -> { 
                onListClicked(me,list);
            });

//            list.getList().setOnDragDetected(me -> {
//                onListDragDetected(me, list);
//            });
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
    
    private String stripDecorators(String filename) {
        if ( filename.startsWith("<") && filename.endsWith(">") ) {
            filename = filename.substring(1, filename.length()-1);
        }
        return filename;
    }
    
    private void onTextClicked(MouseEvent me, HeaderList list) {
        Text source = (Text)me.getSource();
        HeaderList sourceList = getParentHeaderList(source);
        String sourcePath = 
            constructPath( sourceList ) + stripDecorators( source.getText() );
        String fileContents = readFile( sourcePath );
        if ( fileContents == null )
            fileContents = "";

        // Open in a new Stage/Scene graph with a TextFlow control
        Group grp = new Group();
        Node node;
        if ( sourcePath.endsWith("html") ) {
            HTMLEditor html = new HTMLEditor();
            html.setHtmlText(fileContents);
            node = html;
        }
        else {
            TextFlow txtFlow = new TextFlow(new Text(fileContents));
            node = txtFlow;
        }

        grp.getChildren().add(node);
        Scene scene = new Scene(grp, 640, 480);
        Stage editStg = new Stage();
        editStg.setScene(scene);
        editStg.show();
        editStg.centerOnScreen();
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
            String filename = "";
            Object obj = parent.getSelectionModel().getSelectedItem();
            filename = ((Text)parent.getSelectionModel().getSelectedItem()).getText();

            // Remove the directory decorators "<" and ">"
            filename = stripDecorators(filename);

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

    public void populateList(String path, String dirname, int listIndex) {
        HeaderList list = nodes.elementAt(listIndex);
        populateList(path, dirname, list);
    }

    public void populateList(String path, String dirname, HeaderList list) {
        list.getList().getSelectionModel().clearSelection();
        
        if ( dirname != null ) {
            list.setHeader(dirname);
            if ( ! path.endsWith(File.separator) ) {
                path = path + File.separator;
            }
            path = path + dirname;
        }
        else {
            list.setHeader(path);
        }
        
        // get file list
        //List<String> results = new ArrayList<String>();
        List results = new ArrayList();
        File[] files = new File(path).listFiles();
        if ( files == null )
            return;

        for (File file : files) {
            if ( ! file.isHidden() ) {
                if ( file.isFile() ) {
                    Text source = new Text(file.getName());
                    source.setOnDragDetected(de -> { onDragDetected(de, source); });
                    source.setOnDragDone(de -> { onDragDone(de,source); });
                    source.setOnMouseClicked( me -> { onTextClicked(me,list); } );
                    results.add(source);
                }
                else if ( file.isDirectory() ) {
                    Text target = new Text("<" + file.getName()+">");
                    target.setOnDragOver(de -> { onDragOver(de,target); });
                    target.setOnDragEntered(de -> { onDragEntered(de,target); });
                    target.setOnDragExited(de -> { onDragExited(de,target); });
                    target.setOnDragDropped(de -> { onDragDropped(de,target); });
                    results.add(target);
                }
            }
        }            

        ObservableList items = list.getList().getItems();
        if ( items != null )
            list.getList().getItems().clear();
        list.setItems(results.toArray());
        
    }
    
    private String readFile(String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, Charset.defaultCharset());
        }
        catch ( Exception e ) { }
        return null;
    }
    
    private void onDragDetected(MouseEvent me, Text source) {
        // start a drag-and-drop gesture
        HeaderList sourceList = getParentHeaderList(source);
        String sourcePath = 
            constructPath( sourceList ) + stripDecorators( source.getText() );
        
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        // Put the file contents or filename into the clipboard
        ClipboardContent content = new ClipboardContent();
        String fileContents = readFile( sourcePath );
        if ( fileContents == null ) 
            fileContents = source.getText();
        
        content.putString(fileContents);
        db.setContent(content);

        me.consume();
    }
    
    private void onDragDone(DragEvent event, Text source) {
        // the drag-and-drop gesture ended, just clear the clipboard
        System.out.println("onDragDone");
        Dragboard db = event.getDragboard();
        db.clear();
        event.consume();
    }

    private void onDragOver(DragEvent event, Text target) {
        System.out.println("onDragOver");
        // Check the dragboard. If it has contents, then a drag/drop was started
        if ( event.getDragboard().hasString() )
            event.acceptTransferModes(TransferMode.ANY);
        event.consume();
    }
    
    public void onDragEntered(DragEvent event, Text target) {
        // entered the target region
        System.out.println("onDragEntered");
        if (event.getGestureSource() != target &&
                event.getDragboard().hasString()) {
            // Highlight the potential drop target
            target.setUnderline(true);
            target.setStroke(Color.BLACK);
        }

        event.consume();
    }

    public void onDragExited(DragEvent event, Text target) {
        target.setStroke(Color.TRANSPARENT);
        target.setUnderline(false);

        event.consume();
    }

    public void onDragDropped(DragEvent event, Text target) {
        System.out.println("onDragDropped");
        
        // Get data from the dragboard
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            // Do the file move here
            Text source = (Text)event.getGestureSource();
            HeaderList sourceList = getParentHeaderList(source);
            HeaderList targetList = getParentHeaderList(target);

            String targetPath = 
                constructPath( targetList ) + 
                stripDecorators( target.getText() ) +
                File.separator +
                source.getText();
                    
            String sourcePath = 
                constructPath( sourceList ) + 
                stripDecorators( source.getText() );

            try {
                Files.move( Paths.get(sourcePath), 
                            Paths.get(targetPath), ATOMIC_MOVE);

                success = true;
                
                // Update the source list
                String path = constructPath( sourceList );
                if ( path.endsWith("/") )
                    path = path.substring(0,path.length()-1);
                int index = path.lastIndexOf("/");
                String dirname = path.substring(path.lastIndexOf("/")+1);
                path = path.substring(0,index);
                populateList(path, dirname, sourceList);
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }

        // Inform the source that the drag/drop is complete
        event.setDropCompleted(success);
        event.consume();
    }
    
    private HeaderList getParentHeaderList(Text txt) {
        ListCell node = (ListCell)txt.getParent();
        ListView lv = (ListView)node.getListView();
        HeaderList hl = (HeaderList)lv.getParent();
        return hl;
    }
}
