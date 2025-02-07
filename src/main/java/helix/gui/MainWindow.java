package helix.gui;

import helix.Helix;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Helix helix;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Bind "Enter" key to send message
        userInput.setOnAction(event -> handleUserInput());
    }

    public void setHelix(Helix helix) {
        this.helix = helix;
    }

    /**
     * Handles the user input and displays the appropriate response from Helix.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = helix.executeCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, new Image(this.getClass().getResourceAsStream("/images/user.png"))),
                DialogBox.getHelixDialog(response, new Image(this.getClass().getResourceAsStream("/images/helix.png")))
        );
        userInput.clear();
    }
}