package com.music.controller;


import com.Application;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.music.configuration.ControllerConfig;
import com.music.configuration.SpringFxmlLoader;
import com.music.model.AbstractModel;
import com.music.model.SongEntity;
import com.music.model.Test;
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
public class MenuController implements Initializable {

    ObservableList<SongEntity> songList = FXCollections.observableArrayList();
    ObservableList<AbstractModel> modelList =  FXCollections.observableArrayList();

    private boolean sideGridPaneIsEmpty;

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

    @FXML
    private TableColumn<SongEntity, Integer> albumCol;

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

    private SpringFxmlLoader loader = new SpringFxmlLoader();

//    DatabaseHandler handler;
//    Printer printer = new Printer();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //handler = DatabaseHandler.getInstance();
        //songDAO = new SongDAO();
        initCol();
        loadData();
        startShowGridPane();




            VBox box = (VBox) loader.load("../views/insertionBar.fxml", Application.context);

            insertionDrawer.setSidePane(box);
            //CprepareGrid(box);


            HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(insertionHamberger);
            burgerTask.setRate(-1);
            insertionHamberger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();

                if (insertionDrawer.isShown())
                    insertionDrawer.close();
                else
                    insertionDrawer.open();

            });



    }

    private void initCol() {
        songNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("band"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        albumCol.setCellValueFactory(new PropertyValueFactory<>("albums"));
    }

    private void loadData() {
           try {
               List<SongEntity> songs = songService.findAll();
               for (SongEntity s : songs) {
                   songList.add(s);
               }
               songTable.getItems().setAll(songList);
           }
           catch (NullPointerException e){

           }
    }


    private void startShowGridPane() {
        gridPane.getChildren().clear();
        Utils.adjustConstraints(gridPane, new Test());
        Utils.adjustGrid(gridPane, 1, 1);
        //printer.setStrategy(new LogoPringStrategy());
        //printer.print(gridPane, null);

    }

    @FXML
    void printRowInfo(MouseEvent event) {
        model = songTable.getSelectionModel().getSelectedItem();
        gridPane.getChildren().clear();
        Utils.adjustConstraints(gridPane, model);
        Utils.adjustGrid(gridPane, 2, 4);
        //printer.setStrategy(new SongPrintStrategy());
        //printer.print(gridPane, model);

    }

    private void setColWidth(int index, double width) {
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(width);
        gridPane.getColumnConstraints().set(index, col1);
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
        startShowGridPane();
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
