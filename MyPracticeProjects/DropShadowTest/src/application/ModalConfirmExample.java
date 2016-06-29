package application;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.concurrent.Worker;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.*;

/**
 * Application modal dialog with the following properties:
 *   translucent background
 *   drop-shadowed border
 *   non-rectangular shape
 *   blur effect applied to parent when dialog is showing
 *   configurable message text
 *   configurable yes and no event handlers
 */
class ModalDialog extends Stage {
    private static final Effect parentEffect = new BoxBlur();

    private final String messageText;
    private final EventHandler<ActionEvent> yesEventHandler;
    private final EventHandler<ActionEvent> noEventHandler;

    public ModalDialog(
            Stage parent,
            String messageText,
            EventHandler<ActionEvent> yesEventHandler,
            EventHandler<ActionEvent> noEventHandler) {
        super(StageStyle.TRANSPARENT);

        this.messageText = messageText;
        this.yesEventHandler = yesEventHandler;
        this.noEventHandler = noEventHandler;

        // initialize the dialog
        initOwner(parent);
        initParentEffects(parent);
        initModality(Modality.APPLICATION_MODAL);
        setScene(createScene(createLayout()));
    }

    private StackPane createLayout() {
        StackPane layout = new StackPane();
        layout.getChildren().setAll(
                createGlassPane(),
                createContentPane()
        );

        return layout;
    }

    private Pane createGlassPane() {
        final Pane glassPane = new Pane();
        glassPane.getStyleClass().add(
                "modal-dialog-glass"
        );

        return glassPane;
    }

    private Pane createContentPane() {
        final HBox contentPane = new HBox();
        contentPane.getStyleClass().add(
                "modal-dialog-content"
        );
        contentPane.getChildren().setAll(
                new Label(messageText),
                createYesButton(),
                createNoButton()
        );

        return contentPane;
    }

    private Button createYesButton() {
        final Button yesButton = new Button("Yes");
        yesButton.setDefaultButton(true);
        yesButton.setOnAction(yesEventHandler);

        return yesButton;
    }

    private Button createNoButton() {
        final Button noButton = new Button("No");
        noButton.setOnAction(noEventHandler);

        return noButton;
    }

    private Scene createScene(StackPane layout) {
        Scene scene = new Scene(layout, Color.TRANSPARENT);
        scene.getStylesheets().add(
                getClass().getResource(
                        "modal-dialog.css"
                ).toExternalForm()
        );

        return scene;
    }

    private void initParentEffects(final Stage parent) {
        this.showingProperty().addListener(new ChangeListener<Boolean>() {
            @Override public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasShowing, Boolean isShowing) {
                parent.getScene().getRoot().setEffect(
                        isShowing ? parentEffect : null
                );
            }
        });
    }
}

/**
 * Demonstrates a modal confirm box in JavaFX.
 * Dialog is rendered upon a blurred background.
 * Dialog is translucent.
 */
public class ModalConfirmExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final WebView webView = new WebView();

        final ModalDialog dialog = createWebViewPreferenceDialog(primaryStage, webView);

        // show the preference dialog each time a new page is loaded.
        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State newState) {
                if (newState.equals(Worker.State.SUCCEEDED)) {
                    dialog.show();
                    dialog.toFront();
                }
            }
        });
        webView.getEngine().load("http://docs.oracle.com/javafx/");

        // initialize the stage
        primaryStage.setTitle("Modal Confirm Example");
        primaryStage.setScene(new Scene(webView));
        primaryStage.show();
    }

    private ModalDialog createWebViewPreferenceDialog(final Stage primaryStage, final WebView webView) {
        final EventHandler<ActionEvent> yesEventHandler =
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        System.out.println("Liked: " + webView.getEngine().getTitle());
                        primaryStage.getScene().getRoot().setEffect(null);
                        Stage dialogStage = getTargetStage(actionEvent);
                        dialogStage.close();
                    }
                };

        final EventHandler<ActionEvent> noEventHandler =
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        System.out.println("Disliked: " + webView.getEngine().getTitle());
                        primaryStage.getScene().getRoot().setEffect(null);
                        Stage dialogStage = getTargetStage(actionEvent);
                        dialogStage.close();
                    }
                };

        return new ModalDialog(primaryStage, "Will you like this Page?", yesEventHandler, noEventHandler);
    }

    private Stage getTargetStage(ActionEvent actionEvent) {
        Node target = (Node) actionEvent.getTarget();
        return ((Stage) target.getScene().getWindow());
    }
}