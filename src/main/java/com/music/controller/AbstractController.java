package com.music.controller;

import com.music.configuration.SpringFxmlLoader;
import com.music.utils.StageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import sun.reflect.Reflection;

import java.io.IOException;

public abstract class AbstractController {

    protected StageManager stageManager = StageManager.getInstance();




//    protected SpringFxmlLoader loader = SpringFxmlLoader.getLoader();
//
//    public void loadView(String srcPath, boolean resizable, String title){
//            Parent parent = (Parent) loader.load(srcPath);
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle(title);
//            stage.setScene(new Scene(parent));
//            stage.setResizable(resizable);
//            stage.show();
//    }
}
