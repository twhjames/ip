package helix.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a custom dialog box consisting of text and an avatar.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param text The text message.
     * @param img The user's avatar image.
     * @return A DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        Label label = new Label(text);
        ImageView iv = new ImageView(img);
        return new DialogBox(label, iv);
    }

    /**
     * Creates a dialog box for Helix.
     *
     * @param text The text message.
     * @param img The Helix's avatar image.
     * @return A DialogBox for Helix.
     */
    public static DialogBox getHelixDialog(String text, Image img) {
        Label label = new Label(text);
        ImageView iv = new ImageView(img);
        DialogBox db = new DialogBox(label, iv);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList children = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(children);
        this.getChildren().setAll(children);
    }
}