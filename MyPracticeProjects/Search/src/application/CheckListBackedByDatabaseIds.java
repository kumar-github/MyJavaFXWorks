package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Demonstrates linking displays and selections in a ListView and an associated list of checkboxes 
 * with values derived from a database.
 */
public class CheckListBackedByDatabaseIds extends Application {
  public static void main(String[] args) throws Exception { launch(args); }
  public void start(final Stage stage) throws Exception {
    // create a backing database
    final Database db = new Database();

    // provide some instructions.
    final Label instructions = new Label("Choose an opponent and enabled attacks.");

    // provide some choices based on the animals in the database;
    ObservableList<Choice> choices = FXCollections.observableArrayList();
    choices.add(new Choice(null, "No selection"));
    for (Database.Animal animal : db.findAllAnimals()) {
      choices.add(new Choice(animal.id, animal.name));
    }  
    final ListView<Choice> opponentList = new ListView(choices);
    opponentList.getSelectionModel().select(new Choice(null, "No selection"));
    opponentList.setMinSize(240, 170);
    opponentList.setMaxSize(240, 170);

    // provide feedback which acts on choices.
    final Label battleText = new Label();

    // a display (as a list of checkboxes) of potential weapons usable by a given opponent.
    final VBox weaponChecks = new VBox(10);
    weaponChecks.getChildren().add(new Label("No Attacks"));
    
    // remembers the checkbox selections for a given selected item choice.
    final Map<Choice, List<CheckBox>> checkboxMementos = new HashMap();
    
    // act on a choice.
    opponentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Choice>() {
      @Override public void changed(ObservableValue<? extends Choice> observableValue, Choice oldChoice, Choice newChoice) {
        weaponChecks.getChildren().clear();
        battleText.setText("");
        
        if (newChoice.id == null) {
          weaponChecks.getChildren().add(new Label("No Attacks"));
        } else {
          List<CheckBox> rememberedChecks = checkboxMementos.get(newChoice);
          if (rememberedChecks != null) {
            weaponChecks.getChildren().addAll(rememberedChecks);
          } else {
            // lookup weapons for the animal in the database.
            Database.Animal opponent = db.findAnimal(newChoice.id);

            // create weapons choices.
            List<CheckBox> weaponChoices = new ArrayList();
            for (int weaponId : opponent.weaponIds) {
              final Database.Weapon weapon = db.findWeapon(weaponId);
              final CheckBox weaponCheck = new CheckBox(weapon.name);
              weaponCheck.setSelected(true);
              weaponCheck.setUserData(weapon.id);
              weaponChecks.getChildren().add(weaponCheck);
              weaponCheck.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                  battleText.setText("");
                }
              });
              weaponChoices.add(weaponCheck);
            }
            
            // store a memento of the weapons selected for the animal.
            checkboxMementos.put(newChoice, weaponChoices);
          }
        }  
      }
    });
    
    // create a button to take action on a given selection.
    Button engage = new Button("Join Battle!");
    engage.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent actionEvent) {
        Choice newChoice = opponentList.getSelectionModel().getSelectedItem();
        if (newChoice.id == null) {
          battleText.setText("You have chosen cowardice.");
        } else {
          // determine the selected weapons.
          List<Integer> selectedWeaponIds = new ArrayList();
          for (Object child : weaponChecks.getChildren()) {
            if (child instanceof CheckBox) {
              final CheckBox attackCheck = (CheckBox) child;
              if (attackCheck.isSelected()) {
                selectedWeaponIds.add((Integer) attackCheck.getUserData());
              }
            }
          }
          
          // lookup info by id in the database.
          Database.Animal opponent = db.findAnimal(newChoice.id);
          StringBuilder weapons = new StringBuilder();
          for (int weaponId : selectedWeaponIds) {
            weapons.append("\n  ").append(db.findWeapon(weaponId).name);
          }

          // format and display the lookup info.
          battleText.setText(
            "You chosen to face the " + opponent.name + ".\n\n" +
            ((weapons.toString().length() == 0)
               ? "Your opponent is defenseless."
               : (
                  "Beware of your opponents deadly attacks:" + weapons + "\n\n" +
                  "Good Luck!"
                 ))
          );
        }
      }
    });

    // show the scene.
    final VBox layout = new VBox(20);
    layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10; -fx-font-size: 20;");
    final HBox battleSelector = HBoxBuilder.create().spacing(20).children(
      opponentList,
      weaponChecks
    ).build();
    layout.getChildren().addAll(
      instructions,
      battleSelector,
      engage,
      battleText
    );
    VBox.setVgrow(battleSelector, Priority.NEVER);
    engage.translateXProperty().bind(layout.widthProperty().divide(2).subtract(engage.widthProperty().divide(2)).subtract(10));
    Scene scene = new Scene(layout, 550, 450);
    stage.setScene(scene);
    stage.show();
  }

  /** Helper class for mapping a choice displayable in a ChoiceBox to a backing id. */
  class Choice {
    Integer id; String displayString;
    Choice(Integer id)                       { this(id, null); }
    Choice(String  displayString)            { this(null, displayString); }
    Choice(Integer id, String displayString) { this.id = id; this.displayString = displayString; }
    @Override public String toString() { return displayString; }
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Choice choice = (Choice) o;
      return displayString != null && displayString.equals(choice.displayString) || id != null && id.equals(choice.id);
    }
    @Override public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (displayString != null ? displayString.hashCode() : 0);
      return result;
    }
  }

  /** Quick and dirty in memory database */
  class Database {
    private Weapon[] weapons = {
      new Weapon(1, "Flaming Breath"), new Weapon(2, "Feathers of Doom"), new Weapon(3, "Sleeping Sickness"), new Weapon(4, "Drop from above"), new Weapon(5, "Laughter")
    };
    private Animal[] animals = {
      new Animal(1, "Burning Emu", 1, 2 ), new Animal(2, "Killer Koala", 3, 4), new Animal(3, "Outrageous Orangutan", 5)
    };
    
    Animal findAnimal(int id) { for (Animal animal : animals) if (animal.id == id) return animal; return null; }
    Animal[] findAllAnimals() { return animals; }
    Weapon findWeapon(int id) { for (Weapon weapon : weapons) if (weapon.id == id) return weapon; return null; }

    class Animal {
      int id; String name; int[] weaponIds;
      Animal(int id, String name, int... weaponIds) { this.id = id; this.name = name; this.weaponIds = weaponIds; }
      @Override public String toString() { return name; }
    }

    class Weapon {
      int id; String name;
      Weapon(int id, String name) { this.id = id; this.name = name; }
      @Override public String toString() { return name; }
    }
  }
}