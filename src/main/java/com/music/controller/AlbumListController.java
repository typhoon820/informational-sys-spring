package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.Security.CurrentUser;
import com.music.entity.AlbumEntity;
import com.music.entity.GenreEntity;
import com.music.entity.SongEntity;
import com.music.entity.SongHasAlbumEntity;
import com.music.service.AlbumService;
import com.music.service.SongAlbumService;
import com.music.service.SongService;
import com.music.utils.Alerts.NewEntityDialog;
import com.music.utils.Alerts.WarningAlert;
import com.music.utils.CurrentController;
import com.music.utils.CurrentSong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class AlbumListController extends AbstractController implements Initializable {

    private ObservableList<AlbumEntity> allAlbumsList = FXCollections.observableArrayList();
    private ObservableList<SongHasAlbumEntity> currentAlbumsList = FXCollections.observableArrayList();

    @Autowired
    AlbumService albumService;

    @Autowired
    SongService songService;

    @Autowired
    SongAlbumService songAlbumService;

    @FXML
    private TableView<SongHasAlbumEntity> currentAlbumsTable;

    @FXML
    private TableColumn<SongHasAlbumEntity, String> currentAlbumColumn;

    @FXML
    private TableColumn<SongHasAlbumEntity, String> versionColumn;

    @FXML
    private TableView<AlbumEntity> allAlbumsTable;

    @FXML
    private TableColumn<AlbumEntity, String> allAlbumColumn;

    @FXML
    private JFXButton addAlbumButton;

    private static final DataFormat ALBUM_DATA_FORMAT = new DataFormat("com.music.entity.AlbumEntity");

    boolean initialized = false;

    private List<AlbumEntity> allAlbums = new ArrayList<>();
    private List<AlbumEntity> currentAlbums = new ArrayList<>();
    private AlbumEntity recieved = new AlbumEntity();
    private SongHasAlbumEntity rec = new SongHasAlbumEntity();
    private SongEntity song;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialized = true;
        song = (SongEntity) currentModel;
        initAllAlbumCells();
        initCurrentAlbumCells();
        loadAllAlbumsData();
        loadCurrentAlbumsData();
        System.out.println(currentAlbumsList.size());
        System.out.println("entered album list controller");
        if(!CurrentUser.getLoggedInUser().getUser().getUserStatus().getStatus().equals("Admin")) {
            addAlbumButton.setDisable(true);
            allAlbumsTable.setOnDragDetected(null);
        }

    }


    private void initAllAlbumCells() {
        allAlbumColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allAlbumsTable.setOnDragDetected(mouseEvent -> {
            AlbumEntity selected = allAlbumsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                System.out.println("Started Dragging" + selected.getName());
                Dragboard db = allAlbumsTable.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(String.valueOf(selected.getId()));
                db.setContent(content);
                mouseEvent.consume();
            }
        });


    }

    private void initCurrentAlbumCells() {
        currentAlbumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));
        currentAlbumsTable.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != currentAlbumsTable &&
                    dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            dragEvent.consume();
        });
        currentAlbumsTable.setOnDragDropped(dragEvent -> {
            if (dragEvent.getGestureSource() != currentAlbumsTable &&
                    dragEvent.getDragboard().hasString()) {
                recieved = albumService.findById(Integer.valueOf(dragEvent.getDragboard().getString()));
                //recieved = albumService.findByName(dragEvent.getDragboard().getString());
                String version;
                if (!currentAlbumsList.contains(rec)) {
                    Optional<String> result = showDialog(dragEvent.getDragboard().getString()).showAndWait();
                    if (result.isPresent()) {
                        System.out.println("entered drag finish============");
                        version = result.get().equals("") ? "n/a" : result.get();
                        //SongEntity s = CurrentSong.getInstance().getSongEntity();
                        SongHasAlbumEntity sha = new SongHasAlbumEntity();
                        sha.setAlbum(recieved);
                        sha.setSong(song);
                        sha.setVersion(version);
                        song.getAlbums().add(sha);
                        currentAlbumsList.add(sha);
                        System.out.println("Added -> size = " + currentAlbumsList.size());
                    }
                    currentAlbumsTable.setItems(currentAlbumsList);
                }
                dragEvent.setDropCompleted(true);
                dragEvent.consume();
            }

        });
    }

    private void loadAllAlbumsData() {
        allAlbumsList.clear();
        allAlbumsList.addAll(albumService.findAll());
        allAlbumsTable.getItems().setAll(allAlbumsList);
    }


    private void loadCurrentAlbumsData() {
        currentAlbumsList.clear();
        currentAlbumsTable.getItems().clear();
        for (SongHasAlbumEntity a : song.getAlbums()) {
            if (!currentAlbumsList.contains(a)) {
                currentAlbumsList.add(a);
            }
        }
        currentAlbumsTable.getItems().setAll(currentAlbumsList);
    }


    @FXML
    void deleteClick(MouseEvent event) {
        if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
            SongHasAlbumEntity selectedAlbum = currentAlbumsTable.getSelectionModel().getSelectedItem();
            Optional<ButtonType> selected = showAlert(new WarningAlert(), "You are about to delete selected album from list").showAndWait();
            if (selected.get() == ButtonType.OK) {
                List<SongHasAlbumEntity> newAlbums = song.getAlbums();
                newAlbums.remove(selectedAlbum);
                song.setAlbums(newAlbums);
                //songAlbumService.delete(CurrentSong.getCurrentSong().getSongEntity().getId(), selectedAlbum.getId());
                currentAlbumsList.remove(selectedAlbum);
                currentAlbumsTable.getItems().setAll(currentAlbumsList);
                //CurrentSong.getInstance().setSongEntity(songService.findById(CurrentSong.getInstance().getSongEntity().getId()));
            }
        }
    }

    @FXML
    void addAlbum(ActionEvent event) {
        Optional<String> res = new NewEntityDialog().showDialog("album").showAndWait();
        if (albumService.findByName(res.get()) == null) {
            AlbumEntity album = new AlbumEntity();
            album.setName(res.get());
            albumService.save(album);
            loadAllAlbumsData();
        }
    }

}
