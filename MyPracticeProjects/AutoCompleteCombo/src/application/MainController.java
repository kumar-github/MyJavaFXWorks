package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.Subject;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class MainController implements Initializable
{

	@FXML
	private ComboBox subjectDropdown;
	@FXML
	private TextField subjectName;
	
		@Override
   public void initialize(URL url, ResourceBundle rb)
		{
       subjectName.setOnKeyReleased(new SubjectFilter());
       subjectName.setOnKeyPressed(new SubjectPressed());
   }
 
    private class SubjectPressed implements EventHandler {
        /*@Override
        public void handle(KeyEvent t) {
 
            if(t.getCode() == KeyCode.TAB) {
                subjectDropdown.hide();
            } 
        }*/

		@Override
		public void handle(Event t)
		{
			if(t.getCode() == KeyCode.TAB) {
                subjectDropdown.hide();
            }
		}
    }    
   private class SubjectFilter implements EventHandler {
        @Override
        public void handle(KeyEvent t) {
 
            if(t.getCode() == KeyCode.DOWN) {
                subjectDropdown.requestFocus();
                subjectDropdown.show();
            } else {
                String partial = subjectName.getText();
                filterSubject(oldPartialSubject, partial);
                oldPartialSubject = partial;
                mySubjectClaim.setId("");
                mySubjectClaim.setRefNo("");
                mySubjectClaim.setName(oldPartialSubject);
                mySubjectClaim.setClaimType("");
            }
        }
    }
    public void filterSubject(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        ObservableList tempList;
        if ( oldVal != null &amp;&amp; (newVal.length() &lt; oldVal.length()) ) {             // Restore the lists original set of entries             // and start from the beginning             tempList =  ListManager.getInstance().getSubjectList();         } else {             tempList = subjectDropdown.getItems();         }         // Break out all of the parts of the search text         // by splitting on white space         String[] parts = newVal.toLowerCase(Locale.US).split(" ");         // Filter out the entries that don't contain the entered text         subjectSubentries.clear();         for ( Subject entry:  tempList ) {             boolean match = true;             String entryText = entry.getName();             for ( String part: parts ) {                 // The entry needs to contain all portions of the                 // search string *but* in any order                 if ( ! entryText.toLowerCase(Locale.US).contains(part) ) {                     match = false;                     break;                 }             }             if ( match ) {                 subjectSubentries.add(entry);             }         }         subjectDropdown.setItems(subjectSubentries);         if ( newVal != null &amp;&amp; !newVal.isEmpty() &amp;&amp; subjectSubentries.size() &gt; 0 ) {
            if(!subjectDropdown.isShowing()) {
                subjectDropdown.show();
            }
        } else {
            if(subjectDropdown.isShowing()) {
                subjectDropdown.hide();
            }    
        }
    }
 
    private static class SubjectToStringConverter extends StringConverter {
        @Override
        public String toString(Subject t) {
            return ((t==null)?null:t.toString());
        }
 
        @Override
        public Subject fromString(String string) {
            Subject subject = new Subject();
            subject.setName(string);
            return subject;
        }
    }