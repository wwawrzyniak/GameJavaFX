package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import java.io.IOException;


public class PlayController  {
    public static String name;
    @FXML
    TextField nameTyper;

    public void saveName() {
        name = nameTyper.getText();
    }
    public void ChangeToPlayWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("PlayWindow.fxml"));
        BeforePlayController.methodChangeSceneUniversal(event, tableViewParent);
        saveName();
    }

}
