package helix.gui;

import helix.Helix;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main application class for the Helix GUI.
 */
public class MainApp extends Application {

    private Helix helix = new Helix("data/helix_tasklist.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Helix - Personal Assistant");
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().setHelix(helix);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}