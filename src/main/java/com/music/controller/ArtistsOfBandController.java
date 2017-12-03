package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.Security.CurrentUser;
import com.music.entity.*;
import com.music.service.ArtistBandService;
import com.music.service.ArtistService;
import com.music.utils.Alerts.Dialogs;
import com.music.utils.Alerts.NewArtistDialog;
import com.music.utils.Alerts.WarningAlert;
import com.music.utils.CurrentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ArtistsOfBandController extends AbstractController implements Initializable {

    private BandEntity bandEntity;

    @FXML
    private TableView<ArtistBandEntity> currentArtistsTable;

    @FXML
    private TableColumn<ArtistBandEntity, String> currentArtistFirstNameCol;

    @FXML
    private TableColumn<ArtistBandEntity, String> currentArtistLastNameCol;

    @FXML
    private TableColumn<ArtistBandEntity, String> currentArtistSpecCol;

    @FXML
    private TableView<ArtistBandEntity> exMembersTable;

    @FXML
    private TableColumn<ArtistBandEntity, String> exFirstNameCol;

    @FXML
    private TableColumn<ArtistBandEntity, String> exLastNameCol;

    @FXML
    private TableColumn<ArtistBandEntity, String> exSpecCol;

    @FXML
    private TableColumn<ArtistBandEntity, Date> exFromColumn;

    @FXML
    private TableColumn<ArtistBandEntity, Date> exToColumn;

    @FXML
    private TableView<ArtistEntity> availableArtistsTable;

    @FXML
    private TableColumn<ArtistEntity, String> availableFirstNameCol;

    @FXML
    private TableColumn<ArtistEntity, String> availableLastNameCol;

    @FXML
    private TableColumn<ArtistEntity, String> availableSpecCol;

    @FXML
    private JFXButton addToCrewButton;
    @FXML
    private JFXButton newArtistButton;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistBandService artistBandService;

    private ArtistBandEntity recieved = new ArtistBandEntity();

    private ObservableList<ArtistBandEntity> exArtists = FXCollections.observableArrayList();
    private ObservableList<ArtistBandEntity> currentArtists = FXCollections.observableArrayList();
    private ObservableList<ArtistEntity> availableArtists = FXCollections.observableArrayList();
    private Date date = new Date();

    private void loadExArtists() {
        exArtists.clear();
        exArtists.setAll(artistBandService.findPreviousArtistBand(bandEntity));
        exMembersTable.getItems().setAll(exArtists);
    }

    private void loadCurrentArtists() {
        currentArtists.clear();
        currentArtists.setAll(artistBandService.findCurrentArtistBand(bandEntity));
        currentArtistsTable.getItems().setAll(currentArtists);

    }

    private void loadAvailableArtists() {
        availableArtists.clear();
        availableArtists.setAll(artistService.findAll().stream().
                filter(item -> artistBandService.findCurrentBand(item) == null).
                collect(Collectors.toList()));
        availableArtistsTable.getItems().setAll(availableArtists);

    }

    private void initCols() {
        currentArtistFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        currentArtistLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        currentArtistSpecCol.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        exFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        exLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        exSpecCol.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        exFromColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        exToColumn.setCellValueFactory(new PropertyValueFactory<>("leaveDate"));


        availableFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        availableLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        availableSpecCol.setCellValueFactory(new PropertyValueFactory<>("specialization"));


    }

    private void initDragNDrop(){
        currentArtistsTable.setOnDragDetected(mouseEvent -> {
            ArtistBandEntity selected = currentArtistsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                System.out.println("Started dragging");
                Dragboard db = currentArtistsTable.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(String.valueOf(selected.getId()));
                db.setContent(content);
                mouseEvent.consume();
            }
        });
        exMembersTable.setOnDragOver(dragEvent -> {
            System.out.println(dragEvent.getGestureSource());
            if (dragEvent.getGestureSource() != exMembersTable &&
                    dragEvent.getDragboard().hasString()) {

                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                System.out.println("Accepted transfering" +dragEvent.getDragboard().getString());
            }
            dragEvent.consume();
        });
        exMembersTable.setOnDragDropped(dragEvent -> {
            if (dragEvent.getGestureSource() != exMembersTable &&
                    dragEvent.getDragboard().hasString()) {
                System.out.println("logic");
                recieved = artistBandService.findById(Integer.valueOf(dragEvent.getDragboard().getString()));
                //Optional<String> result = showDialog(dragEvent.getDragboard().getString()).showAndWait();
                //if (result.isPresent()) {
                //bandEntity.getCurrentArtists().remove(recieved);
                System.out.println(recieved.getJoinDate());
                bandEntity.getCurrentArtists().forEach(ar->{
                    if (ar.getId() == recieved.getId()){
                        ar.setLeaveDate(new Date());
                    }
                });
                recieved.setLeaveDate(new Date());
                exArtists.add(recieved);
                exMembersTable.setItems(exArtists);
                dragEvent.setDropCompleted(true);
                dragEvent.consume();
            }

        });
    }

    @Autowired
    NewArtistDialog dialog;

    @FXML
    void addNewArtist(ActionEvent event) {
        ArtistEntity a;
        Optional<ArtistEntity> res = Dialogs.show(dialog).showAndWait();
        if (res.isPresent()) {
            a = res.get();
            if (a.getFirstname().isEmpty() || a.getLastname().isEmpty() || a.getSpecialization().isEmpty()) {
                Optional<ButtonType> sel = showAlert(new WarningAlert(), "all fields have to be set").showAndWait();
                if (sel.get() == ButtonType.OK) {
                    addNewArtist(event);
                }
            }
            artistService.save(a);
            loadAvailableArtists();
        }
    }

    @FXML
    void addToBand(ActionEvent event) {
        ArtistEntity artist = availableArtistsTable.getSelectionModel().getSelectedItem();
        ArtistBandEntity insert = new ArtistBandEntity();
        insert.setArtistId(artist);
        insert.setAuthorId(bandEntity);
        insert.setJoinDate(new Date());
        System.out.println(insert.getJoinDate());
        bandEntity.getCurrentArtists().add(insert);
        currentArtists.add(insert);
        currentArtistsTable.getItems().setAll(currentArtists);
        availableArtistsTable.getItems().remove(artist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bandEntity = (BandEntity) currentModel;
        System.out.println(new Date());
        initCols();
        loadAvailableArtists();
        loadCurrentArtists();
        loadExArtists();
        initDragNDrop();
        if(!CurrentUser.getLoggedInUser().getUser().getUserStatus().getStatus().equals("Admin")) {
            addToCrewButton.setDisable(true);
            newArtistButton.setDisable(true);
            currentArtistsTable.setOnDragDetected(null);
        }
    }
}
