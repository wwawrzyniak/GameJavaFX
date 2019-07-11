package sample;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TheGame {
    private String timeAchieved;
    private String scoreAchieved;
    final private int star_r = 12;
    final private int mete_r = 27;
    final private int ufo_r = 20;
    int points = 0;
    @FXML
    GridPane backgroundPane;
    @FXML
    ImageView theUfo;
    @FXML
    Label min;
    @FXML
    Label sec;
    @FXML
    GridPane backgroundPane2;
    @FXML
    AnimationTimer gameTimer;
    @FXML
    ImageView meteorOne;
    @FXML
    ImageView meteorTwo;
    @FXML
    ImageView starG;
    @FXML
    Label score;
    @FXML
    ImageView heart1;
    @FXML
    ImageView heart2;
    @FXML
    ImageView heart3;
    @FXML
    Button endbutton1;
    @FXML
    Button endbutton2;
    ImageView heartArray[];
    Random rnd;
    boolean isLeftPressed, isRightPressed;
    int angle;
    private int minutesPassed = 0;
    private int secondPassed = 0;
    private int lifes = 3;

    Timer myTimer = new Timer();
    TimerTask task = new TimerTask() {

        public void run() {
            secondPassed++;
            if (secondPassed<60) {
                Platform.runLater(() -> {
                    sec.setText(String.valueOf(secondPassed));
                });
            }
            else {Platform.runLater(() -> {
                secondPassed=0;
                minutesPassed++;
                sec.setText(String.valueOf(secondPassed));
                min.setText(String.valueOf(minutesPassed));
            });}
        }
    };

    public void ChangeToMainWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        BeforePlayController.methodChangeSceneUniversal(event, tableViewParent);
    }
    public void ChangeToPlayWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("PlayWindow.fxml"));
        BeforePlayController.methodChangeSceneUniversal(event, tableViewParent);
    }
    public void initialize() {
        myTimer.scheduleAtFixedRate(task, 0, 1000);
        createGameLoop();
        rnd = new Random();
        heartArray = new ImageView[3];
        heartArray[0] = heart1;
        heartArray[1] = heart2;
        heartArray[2] = heart3;
        endbutton1.setVisible(false);
        endbutton2.setVisible(false);
    }

    public void clickHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            isLeftPressed = true;
        } else if (event.getCode() == KeyCode.RIGHT) {
            isRightPressed = true;
        }
    }

    public void clickRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT)
            isLeftPressed = false;
        else if (event.getCode() == KeyCode.RIGHT)
            isRightPressed = false;
    }

    public void moveUfo() {
        if (isLeftPressed) {
            angle -= 3.5;
            theUfo.setRotate(angle);
            if (theUfo.getLayoutX() > 2) {
                theUfo.setLayoutX(theUfo.getLayoutX() - 2);
            }
        }

        if (isRightPressed) {
            angle += 3.5;
            theUfo.setRotate(angle);
            if (theUfo.getLayoutX() < Main.SCREEN_WIDTH - 65) {
                theUfo.setLayoutX(theUfo.getLayoutX() + 2);
            }
        }
    }

    public void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground(backgroundPane,backgroundPane2);
                moveUfo();
                moveMeteors();
                checkIfCrash();
                checkMeteors();
            }
        };
        gameTimer.start();
    }

    public void moveBackground(GridPane bground1, GridPane bground2) {
        BeforePlayController.moveBackgroundUniversal(bground1, bground2);
    }

    private void setMeteorPosition(ImageView img) {
        img.setLayoutX(rnd.nextInt(330));
        img.setLayoutY(-(rnd.nextInt(400)+100));
    }

    private void moveMeteors() {
        starG.setLayoutY(starG.getLayoutY() + 4);
        meteorTwo.setLayoutY(meteorTwo.getLayoutY() + 4);
        meteorOne.setLayoutY(meteorOne.getLayoutY() + 4);
        starG.setRotate(starG.getRotate() + 2);
        meteorTwo.setRotate(meteorTwo.getRotate() + 2);
        meteorOne.setRotate(meteorOne.getRotate() + 2);
    }

    private void checkMeteors() {
        if (starG.getLayoutY() > 900) setMeteorPosition(starG);
        if (meteorOne.getLayoutY() > Main.SCREEN_HEIGHT) setMeteorPosition(meteorOne);
        if (meteorTwo.getLayoutY() > Main.SCREEN_HEIGHT) setMeteorPosition(meteorTwo);
    }

    private void checkIfCrash() {
        if (ufo_r + star_r > calculateDistance(theUfo.getLayoutX()+30, starG.getLayoutX()+15, theUfo.getLayoutY()+30, starG.getLayoutY()+15)) {
            setMeteorPosition(starG);
            points++;
            score.setText(String.valueOf(points));
        }

        if (ufo_r + mete_r > calculateDistance(theUfo.getLayoutX() + 30, meteorOne.getLayoutX() + 25, theUfo.getLayoutY() + 30, meteorOne.getLayoutY() + 30)) {
            setMeteorPosition(meteorOne);
            removeLife();
        }
        if (ufo_r + mete_r > calculateDistance(theUfo.getLayoutX() + 30, meteorTwo.getLayoutX() + 18, theUfo.getLayoutY() + 30, meteorTwo.getLayoutY() + 22)) {
            setMeteorPosition(meteorTwo);
            removeLife();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private void removeLife() {
        heartArray[lifes-1].setImage(null);
        lifes--;
        if (lifes == 0) {
            gameTimer.stop();
            myTimer.cancel();
            endbutton1.setVisible(true);
            endbutton2.setVisible(true);
            if (score.getText()=="") score.setText("0");
            scoreAchieved=score.getText();
            if (secondPassed<10) sec.setText("0"+secondPassed) ;
            timeAchieved=min.getText()+":"+sec.getText();
            try {
                saveStats();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void saveStats() throws IOException {
        FileWriter fw = null;
        fw = new FileWriter("scores.txt", true);
        Writer output = new BufferedWriter(fw);
        Person p = new Person(PlayController.name,scoreAchieved,timeAchieved);
        output.write(p.toString());
        ((BufferedWriter) output).newLine();
        output.close();
    }

}


