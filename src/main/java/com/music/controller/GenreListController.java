package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.model.AbstractModel;
import com.music.model.BandEntity;
import com.music.model.GenreEntity;
import com.music.service.GenreService;
import com.music.utils.GenreObserver;
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
public class GenreListController extends AbstractController implements Initializable, Observable{

    @FXML
    private TableView<GenreEntity> tableList;

    @FXML
    private TableColumn<GenreEntity, String> mainColumn;

    @FXML
    private JFXButton addButton;

    //@FXML
    private GenreEntity genre;

    private boolean initialized = false;

    @Autowired
    GenreService genreService;

    private List<GenreObserver> observers = new LinkedList<>();

    ObservableList<GenreEntity> genreList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        initialized = true;

    }


    private void loadData() {
        if (initialized){
            tableList.getItems().clear();
            genreList.clear();
        }
        mainColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        for (GenreEntity g : genreService.findAll()) {
            genreList.add(g);
        }
        tableList.getItems().setAll(genreList);
    }

    @FXML
    void addNewEntity(ActionEvent event) {
    }
    @FXML
    void selectItem(MouseEvent event) {
        genre = tableList.getSelectionModel().getSelectedItem();
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add((GenreObserver) o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (GenreObserver o: observers){
            o.updateGenre(genre);
        }
    }
}
