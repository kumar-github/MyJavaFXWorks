/* 
 * Copyright 2014 Jens Deters.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package application;

import java.util.regex.Pattern;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Jens Deters
 */
public class ComboBoxBindingsDemoController {

    @FXML
    private Label valueLabel;

    @FXML
    private Button goButton;

    @FXML
    private TextField noBlanksField;

    @FXML
    private TextField noLeadingBlanksField;

    @FXML
    private TextField numbersOnlyField;

    @FXML
    private TextField lettersOnlyField;

    @FXML
    private ComboBox<String> valueComboBox;

    @FXML
    private ComboBox<?> noBlanksComboBox;

    @FXML
    private ComboBox<?> noLeadingBlanksComboBox;

    @FXML
    private ComboBox<String> numbersOnlyComboBox;

    @FXML
    private ComboBox<String> lettersOnlyComboBox;

    private ObservableList<String> demoItemsList;
    //private static final Pattern LEADING_BLANKS_PATTERN = Pattern.compile("^\\s*+");

    @FXML
    public void initialize() {
        valueComboBox.setItems(getDemoItemsList());

        BooleanBinding goButtonState = valueComboBox.editorProperty().get().textProperty().isEmpty()
                .or(valueComboBox.valueProperty().asString().isEmpty());

        goButton.disableProperty().bind(goButtonState);

        /*InputConstraints.noBlanks(noBlanksField);
        InputConstraints.noLeadingBlanks(noLeadingBlanksField);
        InputConstraints.numbersOnly(numbersOnlyField);
        InputConstraints.lettersOnly(lettersOnlyField);

        InputConstraints.noLeadingBlanks(valueComboBox.getEditor());
        InputConstraints.noLeadingBlanks(noLeadingBlanksComboBox.getEditor());
        InputConstraints.noBlanks(noBlanksComboBox.getEditor());
        InputConstraints.numbersOnly(numbersOnlyComboBox.getEditor());
        InputConstraints.lettersOnly(lettersOnlyComboBox.getEditor());*/


    }

    public ObservableList<String> getDemoItemsList() {
        if (demoItemsList == null) {
            demoItemsList = FXCollections.observableArrayList();
            demoItemsList.add("Erlangen");
            demoItemsList.add("Nürnberg");
            demoItemsList.add("Büchenbach");
        }
        return demoItemsList;
    }

    @FXML
    public void goAction() {
        System.out.println("Value '" + valueComboBox.getValue() + "'");
    }

}
