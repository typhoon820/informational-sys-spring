package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.music.model.*;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import com.music.utils.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class InsertionController extends AbstractController implements Initializable, BandObserver, GenreObserver {
    @FXML
    private VBox slideBox;

    @FXML
    private ComboBox<AbstractModel> comboBox;

    @FXML
    private GridPane insertionPane;
    private GridPaneWrapper pane = new GridPaneWrapper();

    private BandListController bandListController;
    private GenreListController genreListController;

    @FXML
    private JFXButton addButton;
    private List<Node> n = new ArrayList<>();

    //PersistenceService persistenceService = new PersistenceService();


    private AbstractModel model;
//
//    private Printer printer = new Printer();
//
//    private GenreDAO genreDAO = new GenreDAO();
//
//    private ModelDispatcher modelDispatcher = new ModelDispatcher();


    @FXML
    void addModel(ActionEvent event) {

        int i=0;
        ObservableList<Node> list = pane.getPane().getChildren();
        for(Node n: list){

            System.out.println(((JFXTextField)n).getText() + " ->"+i);
            i++;
        }
        //System.out.println((JFXTextField)(pane.getPane().g));

       // ((SongEntity)model).setName(list.get(4));

    }

    @FXML
    void choseItem(ActionEvent event) {
        pane.clear();
        model = comboBox.getSelectionModel().getSelectedItem();
        pane.printModelInfo(model);
    }

    void prepareGrid(VBox box) {

        AbstractModel a;
        initModelList((ComboBox<AbstractModel>) box.getChildren().get(0));


    }

    private void initModelList(ComboBox<AbstractModel> modelList) {
        ObservableList<AbstractModel> list = FXCollections.observableArrayList(new AlbumEntity(),
                new ArtistEntity(), new BandEntity(), new CdEntity(), new SongEntity(), new GenreEntity());
        modelList.setItems(list);
        //modelList.setItems(list);
        // modelList.set
        bandListController = (BandListController) stageManager.getLoader().getController("../views/bandList.fxml");
        bandListController.registerObserver(this);
        genreListController = (GenreListController) stageManager.getLoader().getController("../views/genreList.fxml");
        genreListController.registerObserver(this);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setActive(true);
        pane.setPane(insertionPane);
        initModelList(comboBox);
    }

    @Override
    public void update(AbstractModel model) {
    }

    @Override
    public void updateBand(BandEntity bandEntity) {
        if (pane.isActive()) {
            pane.invalidateStringFields(model);
            ((SongEntity) this.model).setBand(bandEntity);
            pane.clear();
            pane.printModelInfo(this.model);
        }
    }

    @Override
    public void updateGenre(GenreEntity genreEntity) {
        if (pane.isActive()) {
            pane.invalidateStringFields(this.model);
            ((SongEntity) this.model).setGenre(genreEntity);
            pane.clear();
            pane.printModelInfo(this.model);
        }
    }
}
