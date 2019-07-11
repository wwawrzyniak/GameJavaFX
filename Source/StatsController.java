package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class StatsController implements Initializable {
    @FXML
    TableView table;
    @FXML
    GridPane bground1;
    @FXML
    GridPane bground2;

    ArrayList temp = new ArrayList<>();

    ObservableList<Person> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readFromFile();
        file();
        table.getItems().addAll(data);
        BeforePlayController.moveBackgroundUniversal(bground1,bground2);
    }

    public void readFromFile() {
        String line;
        try {
            BufferedReader input = new BufferedReader((new FileReader("scores.txt")));
            if (!input.ready()) {
                throw new IOException();
            }
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                temp.add(line);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Read from file finished");
    }

    public void file() {
        for (Object s : temp) {
            String line = String.valueOf(s);
            String splited[] = line.split("\\s");
            if (splited.length == 3) {
                data.add(new Person(splited[0], splited[1], splited[2]));
            }
        }
    }

    public void ChangeToMainWindow(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        BeforePlayController.methodChangeSceneUniversal(event, tableViewParent);
    }
}


