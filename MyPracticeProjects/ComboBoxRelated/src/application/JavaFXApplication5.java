package application;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class JavaFXApplication5 extends Application
{
    public static void main( String[] args )
    {
        launch( args );
    }


    public class ProgressData
    {
        private final DoubleProperty  progressProp = new SimpleDoubleProperty();
        private final StringProperty  progressName = new SimpleStringProperty();

        public ProgressData( String name, double progress )
        {
            progressProp.set( progress );
            progressName.set( name );
        }

        public DoubleProperty progressProperty()
        {
            return  progressProp;
        }

        public StringProperty nameProperty()
        {
            return  progressName;
        }

        @Override
        //  Lazy hack for the combo button.
        public String toString()
        {
            return progressName.get();
        }
    }


    @Override
    public void start( Stage primaryStage )
    {
        ProgressData vb1 = new ProgressData( "Progressbar 1", -1 );
        ProgressData vb2 = new ProgressData( "Progressbar 2", 0.2 );
        ProgressData vb3 = new ProgressData( "Progressbar 3", 0.3 );

        TextChooser textChooser = new TextChooser( vb1, vb2, vb3 );

        textChooser.setStyle( "-fx-font: 10px \"Verdana\";" );

        StackPane root = new StackPane();
        root.getChildren().add( textChooser );

        Scene scene = new Scene( root, 300, 250 );

        primaryStage.setTitle( "Hello World!" );
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    public static class TextChooser extends StackPane
    {
        private final Label label = new Label();

        private final ComboBox<ProgressData> combo = new ComboBox<>();

        public TextChooser(ProgressData... options)
        {
            StackPane.setAlignment( label, Pos.CENTER_LEFT );
            StackPane.setAlignment( combo, Pos.CENTER_LEFT );


            final ProgressBar  labelBar = new ProgressBar();
            label.visibleProperty().bind( combo.visibleProperty().not() );
            label.setContentDisplay( ContentDisplay.RIGHT );
            label.setGraphic( labelBar );


            combo.getItems().setAll( options );

            //  This will change the label's text and the progress bar value.
            combo.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<ProgressData>()
            {
                @Override
                public void changed( ObservableValue<? extends ProgressData> observable, ProgressData oldValue, ProgressData newValue )
                {
                    if ( labelBar.progressProperty().isBound() )
                    {
                        labelBar.progressProperty().unbind();
                    }

                    labelBar.progressProperty().bind( newValue.progressProperty() );
                    label.setText( newValue.nameProperty().get() );
                }
            } );

            combo.setCellFactory( new Callback<ListView<ProgressData>, ListCell<ProgressData>>()
            {
                @Override
                public ListCell<ProgressData> call( ListView<ProgressData> p )
                {
                    return new ListCell<ProgressData>()
                    {
                        private final ProgressBar  cellBar = new ProgressBar();
                        {
                            cellBar.setMouseTransparent( true );
                            setContentDisplay( ContentDisplay.RIGHT );
                            setGraphic( cellBar );
                        }

                        @Override
                        protected void updateItem( ProgressData item, boolean empty )
                        {
                            super.updateItem( item, empty );

                            if ( item != null && ! empty )
                            {
                                if ( cellBar.progressProperty().isBound() )
                                {
                                    cellBar.progressProperty().unbind();
                                }
                                cellBar.progressProperty().bind( item.progressProperty() );
                                setText( item.nameProperty().get() );
                            }
                        }
                    };
                }
            } );

            combo.getSelectionModel().select( 0 );
            combo.setVisible( true );

            label.setOnMouseEntered( new EventHandler<MouseEvent>()
            {
                @Override
                public void handle( MouseEvent event )
                {
                    combo.setVisible( true );
                }
            } );

            combo.showingProperty().addListener( new InvalidationListener()
            {
                @Override
                public void invalidated( Observable observable )
                {
                    if ( !combo.isShowing() )
                    {
                        combo.setVisible( false );
                    }
                }
            } );

            combo.setOnMouseExited( new EventHandler<MouseEvent>()
            {
                @Override
                public void handle( MouseEvent event )
                {
                    if ( !combo.isShowing() )
                    {
                        combo.setVisible( false );
                    }
                }
            } );

            getChildren().setAll( label, combo );
        }

    }

}