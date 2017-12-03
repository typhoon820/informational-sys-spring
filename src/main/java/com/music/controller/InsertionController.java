package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.music.entity.*;
import com.music.service.ArtistService;
import com.music.service.BandService;
import com.music.service.SongService;
import com.music.utils.Observers.ArtistObserver;
import com.music.utils.Observers.BandObserver;
import com.music.utils.Observers.BandsObserver;
import com.music.utils.Observers.GenreObserver;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class InsertionController extends AbstractController implements Initializable,
                                                                    BandObserver,
                                                                    GenreObserver,
                                                                    ArtistObserver,
                                                                    BandsObserver{
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

//    static AbstractModel model;
//    static SongEntity currentSong;
//    static ArtistEntity currentArtist;
//    static BandEntity currentBand;

    @Autowired
    ArtistService artistService;
    @Autowired
    SongService songService;
    @Autowired
    BandService bandService;

    @FXML
    void addModel(ActionEvent event) {
        int i=0;
        ObservableList<Node> list = pane.getPane().getChildren();
        for(Node n: list){

            System.out.println(((JFXTextField)n).getText() + " ->"+i);
            i++;
        }
        pane.invalidateStringFields(currentModel);
        if(currentModel instanceof ArtistEntity){
            //((ArtistEntity) model).setBands(CurrentArtist.getInstance().getArtistEntity().getBands());
            artistService.save((ArtistEntity) currentModel);
            currentModel = new ArtistEntity();
        }
        if (currentModel instanceof SongEntity){
            songService.save((SongEntity)currentModel);
            currentModel= new SongEntity();
        }
        if(currentModel instanceof BandEntity){
            bandService.save((BandEntity)currentModel);
            currentModel = new BandEntity();
        }
        pane.clear();
        pane.printModelInfo(currentModel);
    }

    @FXML
    void choseItem(ActionEvent event) {
        pane.clear();
        currentModel = comboBox.getSelectionModel().getSelectedItem();
        System.out.println(currentModel);
        //currentModel = model;
        SelectionModel.getInstance().setModel(currentModel.getClass());
        System.out.println(SelectionModel.getInstance().getModel());
        pane.printModelInfo(currentModel);
//        if(currentModel instanceof SongEntity){
//            CurrentSong.getInstance().setSongEntity((SongEntity)model);
//            currentSong = (SongEntity)model;
//        }
//        if (model instanceof ArtistEntity){
//            CurrentArtist.getInstance().setArtistEntity((ArtistEntity) model);
//            CurrentArtistBandList.getInstance().setArtistBandEntityList(new ArrayList<>());
//            currentArtist = (ArtistEntity)model;
//        }
//        if (model instanceof BandEntity){
//            currentBand = (BandEntity)model;
//        }


    }

    void prepareGrid(VBox box) {

        AbstractModel a;
        initModelList((ComboBox<AbstractModel>) box.getChildren().get(0));


    }

    private void initModelList(ComboBox<AbstractModel> modelList) {
        ObservableList<AbstractModel> list = FXCollections.observableArrayList(new ArtistEntity(),
                new BandEntity(),
                new SongEntity());
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

        //((Stage)(addButton.getScene().getWindow())).initOwner(((MenuController)stageManager.getLoader().getController("../views/menu.fxml")).mainPane.getScene().getWindow());
    }

    @Override
    public void update(AbstractModel model) {
    }

    @Override
    public void updateBand(BandEntity bandEntity) {
        
        if (pane.isActive()) {
            pane.invalidateStringFields(currentModel);
            ((SongEntity) currentModel).setBand(bandEntity);
            pane.clear();
            pane.printModelInfo(currentModel);
        }
    }

    @Override
    public void updateGenre(GenreEntity genreEntity) {
        if (pane.isActive()) {
            pane.invalidateStringFields(currentModel);
            ((SongEntity) currentModel).setGenre(genreEntity);
            pane.clear();
            pane.printModelInfo(currentModel);
        }
    }

    @Override
    public void updateArtist(ArtistEntity artistEntity) {

    }

    @Override
    public void updateBands(List<BandEntity> bands) {
//        if (pane.isActive()){
//            pane.invalidateStringFields(this.model);
//            ((ArtistEntity)this.model).setBands(bands);
//            pane.printModelInfo(this.model);
//        }
    }
}
