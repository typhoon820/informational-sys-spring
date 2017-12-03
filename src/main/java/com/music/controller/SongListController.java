package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.Security.CurrentUser;
import com.music.entity.BandEntity;
import com.music.entity.SongEntity;
import com.music.service.SongService;
import com.music.utils.Alerts.Dialogs;
import com.music.utils.Alerts.NewSongDialog;
import com.music.utils.Alerts.WarningAlert;
import com.music.utils.CurrentController;
import com.music.utils.Observers.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.DialogTypeSelection;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class SongListController extends AbstractController implements Initializable {

    @FXML
    private TableView<SongEntity> songTable;

    @FXML
    private TableColumn<SongEntity, String> songNameCol;

    @FXML
    private TableColumn<SongEntity, String> genreCol;

    @FXML
    private JFXButton addSongButton;

    private ObservableList<SongEntity> songList = FXCollections.observableArrayList();

    @Autowired
    private SongService songService;

    private BandEntity band;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        band = (BandEntity) currentModel;
        initCols();
        loadSongs();
        if(!CurrentUser.getLoggedInUser().getUser().getUserStatus().getStatus().equals("Admin")) {
            addSongButton.setDisable(true);
        }
    }

    private void loadSongs() {
        songList.clear();
        songList.setAll(band.getSongs());
        songTable.getItems().setAll(songList);
    }

    private void initCols() {
        songNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    @Autowired
    NewSongDialog dialog;

    @FXML
    void addNewSong(ActionEvent event) {
        SongEntity s;
        Optional<SongEntity> res = Dialogs.show(dialog).showAndWait();
        if (res.isPresent()) {
            s = res.get();
            if (s.getName().isEmpty() || s.getGenre() == null) {
                Optional<ButtonType> sel = showAlert(new WarningAlert(), "all fields have to be set").showAndWait();
                if (sel.get() == ButtonType.OK) {
                    addNewSong(event);
                }
            }
            s.setBand(band);
            band.getSongs().add(s);
            songList.add(s);
            songTable.getItems().setAll(songList);
        }

    }

    @FXML
    void deleteSong(MouseEvent event) {

    }

}
