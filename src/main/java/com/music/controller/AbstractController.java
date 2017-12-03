package com.music.controller;

import com.music.configuration.SpringFxmlLoader;
import com.music.entity.AbstractModel;
import com.music.utils.Alerts.AlertThrower;
import com.music.utils.Alerts.TextInputDialog;
import com.music.utils.StageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import sun.reflect.Reflection;

import java.io.IOException;

public abstract class AbstractController {

    protected StageManager stageManager = StageManager.getInstance();
    protected Alert showAlert(AlertThrower at, String text){
        return at.showAlert(text);
    }
    protected Dialog showDialog(String text){
        return new TextInputDialog().showDialog(text);
    }

    protected static AbstractModel currentModel;
}
