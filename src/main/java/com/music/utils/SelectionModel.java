package com.music.utils;

import com.music.entity.AbstractModel;

public class SelectionModel {
    private static SelectionModel selectionModel = null;
    private Class<? extends AbstractModel> model;

    private SelectionModel(){
    }

    public Class<? extends AbstractModel> getModel() {
        return model;
    }

    public void setModel(Class<? extends AbstractModel> model) {
        this.model = model;
    }

    public static SelectionModel getInstance(){
        if(selectionModel == null){
            selectionModel = new SelectionModel();
        }
        return selectionModel;
    }
}
