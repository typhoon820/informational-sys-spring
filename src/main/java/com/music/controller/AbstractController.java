package com.music.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.reflect.Reflection;

import java.io.IOException;

public abstract class AbstractController {
    public void loadView(String srcPath, boolean resizable, String title){
        try{
            Parent parent = FXMLLoader.load(Reflection.getCallerClass(2).getResource(srcPath));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.setResizable(resizable);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
