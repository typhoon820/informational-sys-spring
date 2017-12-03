package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.music.entity.AbstractModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdditionalInfoController extends AbstractController implements Initializable{
    @FXML
    private TableView<AbstractModel> tableList;

    @FXML
    private TableColumn<AbstractModel, String> mainColumn;

    @FXML
    private JFXButton addButton;

    @FXML
    AbstractModel model;

    @FXML
    void addNewEntity(ActionEvent event) {
    }
    @FXML
    void selectItem(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //private void loadData
}
