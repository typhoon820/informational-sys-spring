package com.music.controller;


import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.music.Security.CurrentUser;
import com.music.entity.*;
import com.music.service.ArtistService;
import com.music.service.BandService;
import com.music.service.SongService;
import com.music.utils.Alerts.WarningAlert;
import com.music.utils.Observers.BandObserver;
import com.music.utils.Observers.GenreObserver;
import com.music.utils.SelectionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import com.music.utils.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class MenuController extends AbstractController implements Initializable, BandObserver, GenreObserver {

    private ObservableList<SongEntity> songList = FXCollections.observableArrayList();
    private ObservableList<BandEntity> bandList = FXCollections.observableArrayList();
    private ObservableList<ArtistEntity> artistList = FXCollections.observableArrayList();

    //    static SongEntity currentSong;
//    static BandEntity currentBand;
//    static ArtistEntity currentArtist;
    static SongHasAlbumEntity currentSongAlbum;

    private BandListController bandListController;
    private GenreListController genreListController;
    private boolean gridaPaneActive;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TabPane mainTable;

    @FXML
    private Tab songTab;

    @FXML
    private Tab bandTab;


    @FXML
    private AnchorPane secondPane;

    @FXML
    private JFXButton editButton = new JFXButton();

    @FXML
    private JFXButton tempButton = new JFXButton();

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<SongEntity> songTable;
    @FXML
    private TableColumn<SongEntity, String> songNameCol;

    @FXML
    private TableColumn<SongEntity, String> authorCol;

    @FXML
    private TableColumn<SongEntity, Integer> genreCol;
//
//    @FXML
//    private TableColumn<SongEntity, Integer> albumCol;


    @FXML
    private Tab artistsTab;

    @FXML
    private TableView<ArtistEntity> artistsTable;

    @FXML
    private TableColumn<ArtistEntity, String> firstNameCol;

    @FXML
    private TableColumn<ArtistEntity, String> lastNameCol;

    @FXML
    private TableColumn<ArtistEntity, String> specCol;


    @FXML
    private TableView<BandEntity> bandTable;

    @FXML
    private TableColumn<BandEntity, String> bandName;


    @FXML
    private JFXHamburger insertionHamberger;

    @FXML
    private JFXDrawer insertionDrawer;


    @FXML
    private JFXComboBox<AbstractModel> comboBox;

    @FXML
    private GridPane insertionPane;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXTextField serachTextField;

    @FXML
    private JFXComboBox<String> entityComboBox;


    @Autowired
    private SongService songService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private BandService bandService;

    private AbstractModel model;

    private GridPaneWrapper paneWrapper = new GridPaneWrapper();
    private boolean initialized = false;
//    DatabaseHandler handler;
//    Printer printer = new Printer();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bandListController = (BandListController) stageManager.getLoader().getController("../views/bandList.fxml");
        bandListController.registerObserver(this);
        genreListController = (GenreListController) stageManager.getLoader().getController("../views/genreList.fxml");
        genreListController.registerObserver(this);
        entityComboBox.getItems().setAll("Artist", "Band", "Song");
        paneWrapper.setPane(gridPane);
        paneWrapper.setActive(true);
        initialized = true;
        CurrentController.getInstance().setController(this.getClass());
        initCol();
        loadData();
        loadArtists();
        loadBands();
        startShowGridPane();
        VBox box = (VBox) stageManager.getLoader().load("../views/insertionBar.fxml");
        insertionDrawer.setSidePane(box);
        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(insertionHamberger);
        burgerTask.setRate(-1);
        if(CurrentUser.getLoggedInUser().getUser().getUserStatus().getStatus().equals("Admin")) {
            insertionHamberger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();

                if (insertionDrawer.isShown()) {
                    insertionDrawer.close();
                    paneWrapper.setActive(true);
                    CurrentController.getInstance().setController(this.getClass());
                } else {
                    insertionDrawer.open();
                    paneWrapper.setActive(false);
                    CurrentController.getInstance().setController(InsertionController.class);
                }
            });
        }
        else {
            editButton.setDisable(true);
            tempButton.setDisable(true);
        }

    }

    private void initCol() {
        songNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("band"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        specCol.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        bandName.setCellValueFactory(new PropertyValueFactory<>("name"));
        System.out.println(artistService.findById(5).getBands().get(0).getAuthorId().getName());
        //albumCol.setCellValueFactory(new PropertyValueFactory<>("albums"));
    }

    private void loadData() {
        if (initialized) {
            songList.clear();
        }
        List<SongEntity> songs = songService.findAll();
        songList.addAll(songs);
        songTable.getItems().setAll(songList);
    }

    private void loadArtists() {
        artistList.clear();
        artistList.addAll(artistService.findAll());
        artistsTable.getItems().setAll(artistList);
    }

    private void loadBands() {
        bandList.clear();
        bandList.setAll(bandService.findAll());
        bandTable.getItems().setAll(bandList);
    }

    private void startShowGridPane() {
        paneWrapper.clear();
        paneWrapper.printLogo();
    }

    @FXML
    void printRowInfo(MouseEvent event) {
        model = songTable.getSelectionModel().getSelectedItem();
        CurrentSong.getInstance().setSongEntity((SongEntity) model);
        PreviousSongVersion.getInstance().setSongEntity((SongEntity) model);
        //currentSong = (SongEntity)model;
        currentModel = model;
        paneWrapper.clear();
        paneWrapper.printModelInfo(model);
        //System.out.println();
    }

    public Stage getStage() {
        return ((Stage) (editButton.getScene().getWindow()));
    }

    @FXML
    void showBands(ActionEvent event) {

    }

    @FXML
    void showCD(ActionEvent event) {

    }

    @FXML
    void showSongs(ActionEvent event) {
        loadData();
    }

    @FXML
    void editFields(ActionEvent event) {

        System.out.println("test");
        paneWrapper.invalidateStringFields(currentModel);
        if (currentModel instanceof SongEntity) {
            songService.update((SongEntity) currentModel);
            loadData();
        }

        if (currentModel instanceof ArtistEntity) {
            artistService.update((ArtistEntity) currentModel);
            loadArtists();
        }
        if (currentModel instanceof BandEntity) {
            bandService.update((BandEntity) currentModel);
            loadBands();
        }

    }

    @FXML
    void printArtistInfo(MouseEvent event) {
        model = artistsTable.getSelectionModel().getSelectedItem();
        //CurrentArtist.getInstance().setArtistEntity((ArtistEntity)model);
        //currentArtist = (ArtistEntity)model;
        currentModel = model;
        paneWrapper.clear();
        paneWrapper.printModelInfo(model);
    }


    @FXML
    void printBandInfo(MouseEvent event) {
        model = bandTable.getSelectionModel().getSelectedItem();
        //currentBand = (BandEntity) model;
        currentModel = model;
        paneWrapper.clear();
        paneWrapper.printModelInfo(model);
    }


    @FXML
    void discard(ActionEvent event) {
        Optional<ButtonType> answer = showAlert(new WarningAlert(), "You are going to delete selected instance." +
                " Are you sure?").showAndWait();
        if (answer.get() == ButtonType.OK) {
            if (model instanceof SongEntity) {
                songService.delete(((SongEntity) model));
                loadData();
            }

            if (model instanceof ArtistEntity) {
                artistService.delete(((ArtistEntity) model));
                loadArtists();
            }
            if (model instanceof BandEntity) {
                bandService.delete(((BandEntity) model));
                loadBands();
            }
            paneWrapper.clear();
            paneWrapper.printLogo();
        }

    }

    @Override
    public void update(AbstractModel model) {

    }

    @Override
    public void updateBand(BandEntity bandEntity) {
        if (paneWrapper.isActive()) {
            paneWrapper.invalidateStringFields(this.model);
            ((SongEntity) this.model).setBand(bandEntity);
            paneWrapper.clear();
            paneWrapper.printModelInfo(this.model);
        }
    }

    @Override
    public void updateGenre(GenreEntity genreEntity) {
        if (paneWrapper.isActive()) {
            ((SongEntity) this.model).setGenre(genreEntity);
            paneWrapper.invalidateStringFields(this.model);
            paneWrapper.clear();
            paneWrapper.printModelInfo(this.model);
        }
    }


    @FXML
    void switchArtistsTab(Event event) {
        loadArtists();
        SelectionModel.getInstance().setModel(ArtistEntity.class);
        System.out.println(ArtistEntity.class);
    }

    @FXML
    void switchBandsTab(Event event) {
        loadBands();
        SelectionModel.getInstance().setModel(BandEntity.class);
        System.out.println(BandEntity.class);
    }

    @FXML
    void switchSongsTab(Event event) {
        loadData();
        SelectionModel.getInstance().setModel(SongEntity.class);
        System.out.println(SongEntity.class);
    }


    @FXML
    void findEntities(ActionEvent event) {
        String search = serachTextField.getText();
        AbstractModel found = null;
        switch (entityComboBox.getSelectionModel().getSelectedItem()) {
            case "Artist":
                mainTable.getSelectionModel().select(artistsTab);
                found = artistService.findByName(search);
                break;
            case "Band":
                mainTable.getSelectionModel().select(bandTab);
                found = bandService.findByName(search);
                break;
            case "Song":
                mainTable.getSelectionModel().select(songTab);
                found = songService.findByName(search);
                break;
        }
        paneWrapper.clear();
        if (found != null) {
            paneWrapper.printModelInfo(found);
            currentModel = found;
        } else {
            paneWrapper.printLogo();
        }
    }
}
