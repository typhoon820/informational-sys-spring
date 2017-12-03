package com.music.utils.Alerts;

import com.music.entity.ArtistEntity;
import com.music.entity.SongEntity;
import com.music.service.ArtistService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewArtistDialog implements DialogShowable {

    @Autowired
    ArtistService artistService;

    @Override
    public Dialog show() {
        Dialog<ArtistEntity> dialog = new Dialog<>();
        dialog.setTitle("Add new artist");
        dialog.setHeaderText("Enter information about the artist you want to add:");
        dialog.setResizable(false);
        dialog.getDialogPane().setPrefSize(300, 220);
        Label firstName = new Label("Firstname: ");
        Label lastName = new Label("Lastname: ");
        Label spec = new Label("Specialization: ");

        TextField firstNameText = new TextField();
        TextField lastNameText = new TextField();
        TextField specText = new TextField();
        GridPane gridPane = new GridPane();
        gridPane.add(firstName, 1, 1);
        gridPane.add(firstNameText, 2, 1);
        gridPane.add(lastName, 1, 2);
        gridPane.add(lastNameText, 2, 2);
        gridPane.add(spec, 1, 3);
        gridPane.add(specText, 2, 3);
        dialog.getDialogPane().setContent(gridPane);
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);
        dialog.setResultConverter(buttonType -> {
            if (buttonType == buttonTypeOk) {
                ArtistEntity a = new ArtistEntity();
                a.setFirstname(firstNameText.getText());
                a.setLastname(lastNameText.getText());
                a.setSpecialization(specText.getText());
                return a;
            }
            return null;
        });

        return dialog;
    }
}

