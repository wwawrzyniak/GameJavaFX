package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BeforePlayController implements Initializable {
    @FXML
    GridPane bground1;
    @FXML
    ImageView image;
    @FXML
    GridPane bground2;
    @FXML
    AnimationTimer animTim;
    @FXML
    ImageView startUfo;

    private double angle=0.1;

    private void letsMoveIt(){
        animTim=new AnimationTimer(){
            @Override
            public void handle(long now) {
                moveBackground(bground1,bground2);
                floatUfo();
            }
        }; animTim.start();
    }

    private void floatUfo(){

        if (startUfo.getRotate()>5)  {angle =-0.1; startUfo.setRotate(startUfo.getRotate()+angle);}
        else if (startUfo.getRotate()<-5) {angle =0.1; startUfo.setRotate(startUfo.getRotate()+angle);}
        else startUfo.setRotate(startUfo.getRotate()+angle);

    }

    private  void moveBackground(GridPane bground1, GridPane bground2) {
        moveBackgroundUniversal(bground1, bground2);
    }

    static void moveBackgroundUniversal(GridPane bground1, GridPane bground2) {
        bground1.setLayoutY(bground1.getLayoutY() + 0.2);
        bground2.setLayoutY(bground2.getLayoutY() + 0.2);
        if (bground1.getLayoutY() >= (Main.SCREEN_HEIGHT))
            bground1.setLayoutY(-(Main.SCREEN_HEIGHT));
        if (bground2.getLayoutY() >= (Main.SCREEN_HEIGHT))
            bground2.setLayoutY(-(Main.SCREEN_HEIGHT));
    }

    public void ChangeToBeforePlayWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("BeforePlayWindow.fxml"));
        methodChangeSceneUniversal(event, tableViewParent);
    }

    static void methodChangeSceneUniversal(ActionEvent event, Parent tableViewParent) {
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void ChangeToStatsWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("StatsWindow.fxml"));
        methodChangeSceneUniversal(event, tableViewParent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        letsMoveIt();
    }
}

