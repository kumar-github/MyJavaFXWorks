package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

public class Controller extends AnchorPane implements Initializable
{
    @FXML private TextField txtMinutes;
    @FXML private TextField txtSeconds;
    @FXML private Label     lblTimeDisplay;
    @FXML private Label     lblInfo;
    @FXML private Button    btnStart;
    @FXML private Stage     stage;
    private       String    min, sec;
    private int iMin, iSec;
    private Main application;
    private boolean stopAudio = false;
    private boolean stopTimer = false;

    @Override public void initialize(URL location,ResourceBundle resources)
    {
        assert txtSeconds != null : "fx:id=\"txtSeconds\" was not injected: check your FXML file 'CountdownTimer.fxml'.";
        assert txtMinutes != null : "fx:id=\"txtMinutes\" was not injected: check your FXML file 'CountdownTimer.fxml'.";
        assert lblTimeDisplay != null : "fx:id=\"lblTimeDisplay\" was not injected: check your FXML file 'CountdownTimer.fxml'.";
        assert lblInfo != null : "fx:id=\"lblInfo\" was not injected: check your FXML file 'CountdownTimer.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'CountdownTimer.fxml'.";
        txtSeconds.setMinWidth(45);
        txtSeconds.setPrefWidth(45);
        txtMinutes.setMinWidth(45);
        txtMinutes.setPrefWidth(45);
        btnStart.setText("Start Countdown");
        setInput("00:01");
    }

    @FXML public void checkKeyPressed(KeyEvent ev)
    {
        String charCheck = "";
        String c = ev.getCharacter();
        if(ev.getSource().equals(txtMinutes))
        {
            charCheck = "1234567890";
        }
        if(ev.getSource().equals(txtSeconds))
        {
            charCheck = "1234567890";
        }
        if(! charCheck.contains(c))
        {
            ev.consume();
        }
    }

    @FXML public void actionClick()
    {
        switch(btnStart.getText())
        {
            case "Stop Audio":
                lblInfo.setText("Audio Stopped");
                stopAudio = true;
                stopTimer = true;
                btnStart.setText("Start Countdown");
                break;
            case "Stop Countdown":
                lblInfo.setText("Timer Stopped");
                btnStart.setText("Start Countdown");
                stopAudio = true;
                stopTimer = true;
                break;
            case "Start Countdown":
                lblInfo.setText(" ");
                iMin = Integer.valueOf(txtMinutes.getText());
                iSec = Integer.valueOf(txtSeconds.getText());
                if((iMin<1) && (iSec<1))
                {
                    showInfoDialog("Can't do it","Cannot have a timer started without a time");
                    txtMinutes.requestFocus();
                }
                else
                {

                    stopAudio = false;
                    stopTimer = false;
                    lblTimeDisplay.textProperty().bind(countdownTimer.messageProperty());
                    new Thread(countdownTimer).start();
                    btnStart.setText("Stop Countdown");
                    lblInfo.setText("Countdown Running");
                }
                setInput("00:00");
                break;
        }
    }

    @FXML public void btnExit(ActionEvent actionEvent)
    {
        System.out.println(String.valueOf(actionEvent));
        System.exit(0);
    }

    @FXML public void setFocus()
    {
        txtMinutes.requestFocus();
    }

    public void setApp(Main app,Stage s)
    {
        setApplication(app);
        setStage(s);
    }

    public void showInfoDialog(String title,String message)
    {
        Dialogs.create().owner(this.stage).title(title).message(message).showInformation();
    }

    private void setInput(String s)
    {
        String[] op = s.split(":");
        txtMinutes.setText(op[0]);
        txtSeconds.setText(op[1]);
    }

    private void playSound()
    {
        try
        {
            String SOUND_FILENAME;
            setOutputVolume(7f);
            Random rn = new Random();
            int soundNumber = rn.nextInt(3) + 1;
            switch(soundNumber)
            {
                case 1:
                    SOUND_FILENAME = "Alarm_Clock_Buzzer.mp3";
                    break;

                case 2:
                    SOUND_FILENAME = "Railroad_Crossing_Bell.mp3";
                    break;

                case 3:
                    SOUND_FILENAME = "Tornado_Siren_II.mp3";
                    break;

                default:
                    SOUND_FILENAME = "Tornado_Siren_II.mp3";
            }
            SOUND_FILENAME = String.valueOf(Controller.class.getResource("Audio/" + SOUND_FILENAME));
            double volume = 1; //Valid ranges are 0 to 1 and any fraction in between
            double balance = 0; //Ranges can be fractional between -1 and 1, 0 being balanced
            double rate = 1; //Ranges are 0.125 (1/8 speed) to 8.0 (8x speed)
            double pan = 0; //Values from -1 to 1 sets BOTH channels to the left or right speaker accordingly
            int cycleCount = AudioClip.INDEFINITE; //Ranges 1 to AudioClip.INDEFINITE which will play the file continuously until
            // stop issued

            AudioClip audioFile = new AudioClip(SOUND_FILENAME);
            audioFile.setCycleCount(cycleCount);
            audioFile.play(volume,balance,rate,pan,1);
            while(audioFile.isPlaying())
            {
                Thread.sleep(100);
                if(stopAudio)
                {
                    audioFile.stop();
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setApplication(Main application)
    {
        this.application = application;
    }

    private void setOutputVolume(float value)
    {
        String command = "set volume " + value;
        try
        {
            ProcessBuilder pb = new ProcessBuilder("osascript","-e",command);
            pb.directory(new File("/usr/bin"));
            System.out.println(command);
            StringBuffer output = new StringBuffer();
            Process p = pb.start();
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while((line = reader.readLine()) != null)
            {
                output.append(line + "\n");
            }
            System.out.println(output);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    Task<Void> countdownTimer = new Task<Void>()
    {
        @Override protected Void call() throws Exception
        {
            try
            {
                min = txtMinutes.getText();
                sec = txtSeconds.getText();
            }
            catch(Exception e)
            {
                showInfoDialog("Incorrect Input","Enter Minutes AND seconds.");
                countdownTimer.cancel();
            }
            long start = System.currentTimeMillis();
            long totalSeconds = (iMin * 60) + iSec;
            boolean cont = true;
            updateMessage("00:00");
            while(cont)
            {
                long tNow = System.currentTimeMillis();
                long elapsed = (tNow - start) / 1000;
                String sMin, sSec;
                if(elapsed<totalSeconds)
                {
                    int baseElapsed = Integer.valueOf(String.valueOf(elapsed));
                    int baseTotal = Integer.valueOf(String.valueOf(totalSeconds));
                    int secTotal = baseTotal - baseElapsed;
                    int m1 = secTotal / 60;
                    int s1 = secTotal % 60;
                    sMin = String.valueOf(m1);
                    sSec = String.valueOf(s1);
                    if(m1<10)
                    {
                        sMin = "0" + sMin;
                    }
                    if(s1<10)
                    {
                        sSec = "0" + sSec;
                    }
                    updateMessage(sMin + ":" + sSec);
                    if(stopTimer)
                    {
                        this.cancel();
                    }
                    if(isCancelled())
                    {
                        break;
                    }

                }
                else
                {
                    updateMessage("00:00");
                    cont = false;
                }
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException interrupted)
                {
                    if(isCancelled())
                    {
                        updateMessage("Thread Cancelled");
                        break;
                    }

                }
            }
            playSound();
            return null;
        }

        @Override protected void succeeded()
        {
            super.succeeded();
            System.out.println("Success");
            String newLine = "\n";
            String userMessage = "";
            updateMessage(userMessage + newLine + "Success");
        }

        @Override protected void cancelled()
        {
            super.cancelled();
            String newLine = "\n";
            String userMessage = "";
            userMessage = userMessage + newLine + "Thread Canceled";
            updateMessage(userMessage);
        }

        @Override protected void failed()
        {
            super.failed();
            String newLine = "\n";
            String userMessage = "";
            updateMessage(userMessage + newLine + "Thread failed");
        }
    };

}
