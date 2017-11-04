package com.music.utils;

import com.jfoenix.controls.JFXTextField;
import com.music.model.AbstractModel;
import com.music.model.SongEntity;
import com.music.model.Test;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GridPaneWrapper {
    GridPane pane;
    boolean active = false;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public GridPane getPane() {
        return pane;
    }

    public void setPane(GridPane pane) {
        this.pane = pane;
    }

    public void clear(){
        pane.getChildren().clear();
    }

    public void clear(int cellIndex){
        pane.getChildren().remove(cellIndex);
    }

    private void preparePane(AbstractModel model){
        Utils.adjustConstraints(pane, model);
        Utils.adjustGrid(pane, 2, model.getClass().getDeclaredFields().length-1);
    }

    public void printLogo(){
        Utils.adjustConstraints(pane, new Test());
        Utils.adjustGrid(pane, 1, 1);
        Utils.printInfoLogo(pane);
    }

    public void printModelInfo(AbstractModel model){
        preparePane(model);
        Utils.setTextFieldsData(pane, model);
    }

    public void invalidateStringFields(AbstractModel model){
        ObservableList<Node> list = pane.getChildren();
        List<Field> strFields = model.getStringFields();
        List<String> vals = new ArrayList<>();
        for(int i=0; i < strFields.size(); i++){
            vals.add(((JFXTextField)list.get(model.getClass().getDeclaredFields().length-1+i)).getText());
        }
        model.setStringFieldsValue(vals);
    }
}
