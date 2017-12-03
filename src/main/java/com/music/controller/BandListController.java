package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.Security.CurrentUser;
import com.music.entity.BandEntity;
import com.music.entity.SongEntity;
import com.music.service.BandService;
import com.music.utils.Alerts.NewEntityDialog;
import com.music.utils.Alerts.TextInputDialog;
import com.music.utils.Observers.BandObserver;
import com.music.utils.CurrentController;
import com.music.utils.Observers.Observable;
import com.music.utils.Observers.Observer;
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
import java.util.Optional;
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
        if(!CurrentUser.getLoggedInUser().getUser().getUserStatus().getStatus().equals("Admin")) {
            addButton.setDisable(true);
        }
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
        Optional<String> res = new NewEntityDialog().showDialog("band").showAndWait();
        if (bandService.findByName(res.get()) == null){
            BandEntity band = new BandEntity();
            band.setName(res.get());
            bandService.save(band);
            loadData();
        }

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
            if (o.getClass().equals(CurrentController.getInstance().getController()))
            o.updateBand(band);
        }
    }
}
