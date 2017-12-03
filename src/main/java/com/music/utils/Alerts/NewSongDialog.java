package com.music.utils.Alerts;

import com.music.entity.GenreEntity;
import com.music.entity.SongEntity;
import com.music.service.GenreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class NewSongDialog implements DialogShowable {
    @Autowired
    GenreService genreService;
    @Override
    public Dialog show() {
        Dialog<SongEntity> dialog = new Dialog<>();
        dialog.setTitle("Add new song");
        dialog.setHeaderText("Enter information about the song you want to add:");
        dialog.setResizable(false);
        dialog.getDialogPane().setPrefSize(300,220);
        Label songName = new Label("Song name: ");
        Label genre = new Label("Genre: ");
        //Label album = new Label("Album: ");
        TextField songNameText = new TextField();
        ComboBox<String> comboBox = new ComboBox<>();
        ObservableList list =  FXCollections.observableArrayList();
        genreService.findAll().stream().forEach(e->list.add(e.getGenre()));
        comboBox.setItems(list);
        comboBox.setPromptText("genre...");
        GridPane gridPane = new GridPane();
        gridPane.add(songName, 1,1);
        gridPane.add(songNameText, 2,1);
        gridPane.add(genre,1,2);
        gridPane.add(comboBox,2,2);
        dialog.getDialogPane().setContent(gridPane);
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);

        dialog.setResultConverter(buttonType -> {
            if(buttonType==buttonTypeOk){
                SongEntity s = new SongEntity();
                s.setName(songNameText.getText());
                s.setGenre(genreService.findByName(comboBox.getSelectionModel().getSelectedItem()));
                return s;
            }
            return null;
        });

        return dialog;
    }
}
