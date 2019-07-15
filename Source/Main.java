package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    int SCREEN_WIDTH = 290;
    int SCREEN_HEIGHT = 530;
    final String uriString = new File("src\\sample\\02.mp3").toURI().toString();
    final MediaPlayer player = new MediaPlayer( new Media(uriString));
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();
        t.run();

    }
    Thread t = new Thread(() -> player.play());
    public static void main(String[] args) {
        launch(args);
    }
}
