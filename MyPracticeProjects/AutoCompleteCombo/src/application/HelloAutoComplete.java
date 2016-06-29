package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
//import org.controlsfx.ControlsFXSample;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
//import org.controlsfx.samples.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HelloAutoComplete extends ControlsFXSample
{

    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] _possibleSuggestions = {"Hey", "Hello", "Hello World", "Apple", "Cool", "Costa", "Cola", "Coca Cola"};
    private Set<String> possibleSuggestions = new HashSet<>(Arrays.asList(_possibleSuggestions));
    
    private TextField learningTextField;

    @Override public String getSampleName() {
        return "AutoComplete";
    }

    @Override public String getJavaDocURL() {
        return Utils.JAVADOC_BASE + "org/controlsfx/control/textfield/TextFields.html";
    }

    @Override public String getSampleDescription() {
        return "AutoComplete helps a user with suggestions to type faster, " 
                + "but does not limit the user from entering alternative text."
                + "\n\n"
                + "The textfields have been primed with the following words:\n"
                + "\"Hey\", \"Hello\", \"Hello World\", \"Apple\", \"Cool\", "
                + "\"Costa\", \"Cola\", \"Coca Cola\""
                + "\n\n"
                + "The 'Learning TextField' will add whatever words are typed "
                + "to the auto-complete popup, as long as you press Enter once "
                + "you've finished typing the word.";
    }

    @Override public Node getPanel(final Stage stage) {

        BorderPane root = new BorderPane();

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(30, 30, 0, 30));

        //
        // TextField with static auto-complete functionality
        //
        TextField textField = new TextField();

        TextFields.bindAutoCompletion(
                textField,
                "Hey", "Hello", "Hello World", "Apple", "Cool", "Costa", "Cola", "Coca Cola");

        grid.add(new Label("Auto-complete Text"), 0, 0);
        grid.add(textField, 1, 0);
        GridPane.setHgrow(textField, Priority.ALWAYS);


        //
        // TextField with learning auto-complete functionality
        // Learn the word when user presses ENTER
        //
        learningTextField = new TextField();
        autoCompletionBinding = TextFields.bindAutoCompletion(learningTextField, possibleSuggestions);
        learningTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                case ENTER:
                    autoCompletionLearnWord(learningTextField.getText().trim());
                    break;
                default:
                    break;
                }
            }
        });

        grid.add(new Label("Learning TextField"), 0, 1);
        grid.add(learningTextField, 1, 1);
        GridPane.setHgrow(learningTextField, Priority.ALWAYS);

        root.setTop(grid);
        return root;
    }

    private void autoCompletionLearnWord(String newWord){
        possibleSuggestions.add(newWord);
        
        // we dispose the old binding and recreate a new binding
        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(learningTextField, possibleSuggestions);
    }

    @Override public Node getControlPanel() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(30, 30, 0, 30));

        // TODO Add customization example controls


        return grid;
    }


    public static void main(String[] args) {
        launch(args);
    }

}