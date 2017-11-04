package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.model.BandEntity;
import com.music.model.SongEntity;
import com.music.service.BandService;
import com.music.utils.BandObserver;
import com.music.utils.Observable;
import com.music.utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class BandListController extends AbstractController implements Initializable, Observable {

    @FXML
    private TableView<BandEntity> tableList;

    @FXML
    private TableColumn<BandEntity, String> mainColumn;

    @FXML
    private JFXButton addButton;

    private ObservableList<BandEntity> bandList = FXCollections.observableArrayList();

    private List<BandObserver> observers = new LinkedList<>();

    //@FXML
    private BandEntity band;
    private SongEntity song;
    private boolean initialized = false;

    @Autowired
    BandService bandService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        initialized = true;
    }

    private void loadData() {
        if (initialized){
            tableList.getItems().clear();
            bandList.clear();
        }
        mainColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        for (BandEntity b : bandService.findAll()) {
            bandList.add(b);
        }
        tableList.getItems().setAll(bandList);
    }

    @FXML
    void addNewEntity(ActionEvent event) {
    }

    @FXML
    void selectItem(MouseEvent event) {
        band = tableList.getSelectionModel().getSelectedItem();
        notifyObservers();

    }

    @Override
    public void registerObserver(Observer o) {
        observers.add((BandObserver) o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (BandObserver o : observers) {
            o.updateBand(band);
        }
    }
}
