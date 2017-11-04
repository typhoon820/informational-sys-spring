package com.music.controller;


import com.Application;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.music.configuration.ControllerConfig;
import com.music.configuration.SpringFxmlLoader;
import com.music.model.*;
import com.music.service.SongService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;

import com.music.utils.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MenuController extends AbstractController implements Initializable, BandObserver, GenreObserver {

    ObservableList<SongEntity> songList = FXCollections.observableArrayList();
    ObservableList<AbstractModel> modelList = FXCollections.observableArrayList();

    private boolean sideGridPaneIsEmpty;

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
    private Tab cdTab;

    @FXML
    private AnchorPane secondPane;

    @FXML
    private JFXButton editButton;

    @FXML
    private JFXButton tempButton;

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
    private JFXHamburger insertionHamberger;

    @FXML
    private JFXDrawer insertionDrawer;


    @FXML
    private JFXComboBox<AbstractModel> comboBox;

    @FXML
    private GridPane insertionPane;

    @FXML
    private JFXButton addButton;


    private SongEntity currentSong;

    @Autowired
    private SongService songService;

    private AbstractModel model;

    private GridPaneWrapper paneWrapper = new GridPaneWrapper();

//    DatabaseHandler handler;
//    Printer printer = new Printer();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bandListController = (BandListController) stageManager.getLoader().getController("../views/bandList.fxml");
        bandListController.registerObserver(this);
        genreListController = (GenreListController) stageManager.getLoader().getController("../views/genreList.fxml");
        genreListController.registerObserver(this);
        paneWrapper.setPane(gridPane);
        paneWrapper.setActive(true);
        initCol();
        loadData();
        startShowGridPane();
        VBox box = (VBox) stageManager.getLoader().load("../views/insertionBar.fxml");
        insertionDrawer.setSidePane(box);
        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(insertionHamberger);
        burgerTask.setRate(-1);
        insertionHamberger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();

            if (insertionDrawer.isShown()) {
                insertionDrawer.close();
                paneWrapper.setActive(true);
            } else {
                insertionDrawer.open();
                paneWrapper.setActive(false);
            }

        });
    }

    private void initCol() {
        songNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("band"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        //albumCol.setCellValueFactory(new PropertyValueFactory<>("albums"));
    }

    private void loadData() {
        List<SongEntity> songs = songService.findAll();
        for (SongEntity s : songs) {
            songList.add(s);
        }
        songTable.getItems().setAll(songList);
    }


    private void startShowGridPane() {
        paneWrapper.clear();
        paneWrapper.printLogo();
    }

    @FXML
    void printRowInfo(MouseEvent event) {
        model = songTable.getSelectionModel().getSelectedItem();
        paneWrapper.clear();
        paneWrapper.printModelInfo(model);
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
        //startShowGridPane();
        songService.update((SongEntity) model);

    }
    //TODO: utils class transfer some methods to pane wrapper, add insertion

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
//
//    void prepareGrid(VBox box){
//
//        AbstractModel a;
//        initModelList((JFXComboBox<AbstractModel>) box.getChildren().get(0));
//
//
//    }

//    void initModelList(JFXComboBox<AbstractModel> modelList){
//        ObservableList<AbstractModel> list = FXCollections.observableArrayList( new Album(),
//                new Author(), new Band(), new CD(), new SongTemp(), new Genre());
//        modelList.setItems(list);
//        modelList.setItems(list);
//       // modelList.set
//    }
}
