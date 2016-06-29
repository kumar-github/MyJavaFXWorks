package application;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class MTab extends Tab
{
    public MTab(String pText)
    {
        super();
        //Button fakeLabel = new Button(pText);
        Label fakeLabel = new Label(pText);
        fakeLabel.setMnemonicParsing(true);
        fakeLabel.getStyleClass().clear();
        setGraphic(fakeLabel);
        /*fakeLabel.setOnAction(ev -> {
            if (getTabPane() != null) {
                getTabPane().getSelectionModel().select(this);
            }
        });*/
    }
}