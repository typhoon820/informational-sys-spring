package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.Security.CurrentUser;
import com.music.entity.ArtistBandEntity;
import com.music.entity.ArtistEntity;
import com.music.entity.BandEntity;
import com.music.entity.GenreEntity;
import com.music.service.ArtistBandService;
import com.music.service.BandService;
import com.music.utils.Alerts.NewEntityDialog;
import com.music.utils.CurrentArtist;
import com.music.utils.CurrentController;
import com.music.utils.Observers.ArtistObserver;
import com.music.utils.Observers.BandsObserver;
import com.music.utils.Observers.Observable;
import com.music.utils.Observers.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
public class ArtistBandListController extends AbstractController implements Initializable, Observable {

    private ObservableList<BandEntity> allBandsList = FXCollections.observableArrayList();
    private ObservableList<ArtistBandEntity> artistBandList = FXCollections.observableArrayList();

    private ArtistEntity currentArtist;
    @FXML
    private TableView<ArtistBandEntity> prevBandTable;

    @FXML
    private TableColumn<ArtistBandEntity, String> bandColumn;

    @FXML
    private TableColumn<ArtistBandEntity, Date> fromColumn;

    @FXML
    private TableColumn<ArtistBandEntity, Date> toColumn;

    @FXML
    private Label currentBand;

    @FXML
    private TableView<BandEntity> availableBandTable;

    @FXML
    private TableColumn<BandEntity, String> availableBandColumn;
    @FXML
    private JFXButton leaveBandButton;

    @FXML
    private JFXButton joinBandButton;

    @FXML
    private JFXButton newBandButton;

    @Autowired
    private BandService bandService;
    @Autowired
    private ArtistBandService artistBandService;

    List<BandEntity> bands = new ArrayList<>();

    private List<ArtistObserver> observers = new ArrayList<>();

    private void initCols() {
        bandColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("leaveDate"));
        availableBandColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadAllBands() {
        allBandsList.clear();
        allBandsList.addAll(bandService.findAll());
        availableBandTable.getItems().setAll(allBandsList);
    }

    //TODO: FIX view

    private void loadPrevBandList() {
        artistBandList.clear();
        artistBandList.setAll(artistBandService.findPrevArtistBand(currentArtist));
        prevBandTable.getItems().setAll(artistBandList);
        BandEntity currentBandEntity = artistBandService.findCurrentBand(currentArtist);
        if (currentBandEntity == null) {
            currentBand.setText("N/A");
        } else {
            currentBand.setText(currentBandEntity.getName());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentArtist = (ArtistEntity) currentModel;
        System.out.println(new Date());
        initCols();
        loadAllBands();
        loadPrevBandList();
        System.out.println("hi! current controller is " + CurrentController.getInstance().getController());
        if(!CurrentUser.getLoggedInUser().getUser().getUserStatus().getStatus().equals("Admin")){
            joinBandButton.setDisable(true);
            leaveBandButton.setDisable(true);
            newBandButton.setDisable(true);
        }
    }

    @FXML
    void addNewBand(ActionEvent event) {
        Optional<String> res = new NewEntityDialog().showDialog("band").showAndWait();
        if (bandService.findByName(res.get()) == null) {
            BandEntity band = new BandEntity();
            band.setName(res.get());
            bandService.save(band);
            loadAllBands();
        }
    }

    @FXML
    void joinBand(ActionEvent event) {
        BandEntity newBand = availableBandTable.getSelectionModel().getSelectedItem();
        currentBand.setText(newBand.getName());
        BandEntity current = artistBandService.findCurrentBand(currentArtist);
        if (current != null) {
            leaveCurrentBand();
        }
        List<ArtistBandEntity> bands = new ArrayList<>();
        ArtistBandEntity newArtistBand = new ArtistBandEntity();
        newArtistBand.setLeaveDate(null);
        newArtistBand.setJoinDate(new Date());
        newArtistBand.setAuthorId(newBand);
        newArtistBand.setArtistId(currentArtist);
        //bands = currentArtist.getBands();
        currentArtist.getBands().add(newArtistBand);

//        if (bands.size() == 0) {
//            bands.add(newArtistBand);
//        } else {
//            bands.set(bands.size() - 1, newArtistBand);
//        }
        //CurrentArtist.getInstance().getArtistEntity().setBands(bands);
        //loadPrevBandList();
    }

    @FXML
    void leaveBand(ActionEvent event) {
        leaveCurrentBand();
        //loadPrevBandList();

    }

    private void leaveCurrentBand() {

        BandEntity current;
        List<ArtistBandEntity> artistBandEntity;
        current = artistBandService.findCurrentBand(currentArtist);
        artistBandEntity = currentArtist.getBands();
        if (current != null) {
            for (ArtistBandEntity ab : artistBandEntity) {
                if (ab.getAuthorId().equals(current)) {
                    ab.setLeaveDate(new Date());
                    artistBandList.add(ab);
                    prevBandTable.getItems().setAll(artistBandList);
                }
            }
        }

        //loadPrevBandList();

    }

    @Override
    public void registerObserver(Observer o) {
        observers.add((ArtistObserver) o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove((ArtistObserver) o);
    }

    @Override
    public void notifyObservers() {
        for (ArtistObserver o : observers) {
            //o.updateArtist();
        }
    }
}
