package application;

import java.util.logging.Logger;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

/**
 *
 * @author jdeters
 */
public class ServiceDemoController {

    private final static Logger LOGGER = Logger.getLogger(ServiceDemoController.class.getName());

    @FXML
    private Button connectButton;
    
    @FXML
    private Button disconnectButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label messagesLabel;

    @FXML
    private ProgressIndicator serviceRunningIndicator;


    private ConnectService connectService;
    private DisconnectService disconnectService;

    private StringProperty statusMessagesProperty;
    private BooleanProperty connectedProperty;

    @FXML
    public void initialize() {
        connectService = new ConnectService();
        disconnectService = new DisconnectService();

        BooleanBinding anyServiceRunning = connectService.runningProperty().or(disconnectService.runningProperty());
        serviceRunningIndicator.visibleProperty().bind(anyServiceRunning);
        cancelButton.visibleProperty().bind(anyServiceRunning);
        
        //connectButton.disableProperty().bind(connectedProperty().or(anyServiceRunning));
        //disconnectButton.disableProperty().bind(connectedProperty().not().or(anyServiceRunning));
        
        connectButton.disableProperty().bind(connectService.runningProperty());
        disconnectButton.disableProperty().bind(disconnectService.runningProperty());
        
        messagesLabel.textProperty().bind(statusMessagesProperty());
        
        connectService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
        	statusMessagesProperty().set(newValue);
        });
        
        disconnectService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            statusMessagesProperty().set(newValue);
        });

        statusMessagesProperty().set("Disconnected.");
    }

    @FXML
    public void cancel() {
        LOGGER.info("cancel");
        connectService.cancel();
        disconnectService.cancel();
    }

    @FXML
    public void connect() {
        LOGGER.info("connect");
        //disconnectService.cancel();
        
        connectService.setDelay(Duration.seconds(1));
        connectService.setPeriod(Duration.seconds(25));
        connectService.restart();
    }

    @FXML
    public void disconnect() {
        LOGGER.info("disconnect");
        connectService.cancel();
        disconnectService.restart();
    }

    private StringProperty statusMessagesProperty() {
        if (statusMessagesProperty == null) {
            statusMessagesProperty = new SimpleStringProperty();
        }
        return statusMessagesProperty;
    }

    private BooleanProperty connectedProperty() {
        if (connectedProperty == null) {
            connectedProperty = new SimpleBooleanProperty(Boolean.FALSE);
        }
        return connectedProperty;
    }

    private class ConnectService extends ScheduledService<Void> {
    //private class ConnectService extends Service<Void> {
        @Override
        protected void succeeded() {
            statusMessagesProperty().set("Connected.");
            connectedProperty().set(true);
        }

        @Override
        protected void failed() {
            statusMessagesProperty().set("Connecting failed.");
            LOGGER.severe(getException().getMessage());
            connectedProperty().set(false);
        }

        @Override
        protected void cancelled() {
            statusMessagesProperty().set("Connecting cancelled.");
            connectedProperty().set(false);
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    updateMessage("Connecting....");
                    Thread.sleep(1000);
                    // DEMO: uncomment to provoke "Not on FX application thread"-Exception:
                    //connectButton.setVisible(false);
                    updateMessage("Waiting for server feedback.");
                    Thread.sleep(1000);
                    return null;
                }
            };
        }

    }

    //private class DisconnectService extends Service<Void> {
    private class DisconnectService extends ScheduledService<Void> {
        @Override
        protected void succeeded() {
            statusMessagesProperty().set("");
            connectedProperty().set(false);
        }

        @Override
        protected void cancelled() {
            statusMessagesProperty().set("Disconnecting cancelled.");
            connectedProperty().set(false);
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    updateMessage("Disconnecting....");
                    Thread.sleep(5000);
                    updateMessage("Waiting for server feedback.");
                    Thread.sleep(5000);
                    return null;
                }
            };
        }

    }
}