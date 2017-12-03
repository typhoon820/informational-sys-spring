package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.music.Security.CurrentUser;
import com.music.Security.Encoder;
import com.music.entity.UserEntity;
import com.music.service.UserService;
import com.music.utils.Alerts.WarningAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainController extends AbstractController implements Initializable {


    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton cancelButton;

    private Encoder encoder;

//    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //    private DatabaseHandler handler;
    @Autowired
    UserService userService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //handler = DatabaseHandler.getInstance();
        //userDAO = new UserDAO();
        encoder = Encoder.getInstance();


    }

    @FXML
    void cancel(ActionEvent event) {
        closeStage();
        stageManager.showStage("../views/registration.fxml", false, "Registration");
    }


    @FXML
    void login(ActionEvent event) {


        //System.out.println(showDialog("111").showAndWait().get());


        String tLogin = login.getText();
        String pWord = password.getText();

        UserEntity loggingInUser = userService.findByName(tLogin);
        if (loggingInUser == null){

            if (showAlert(new WarningAlert(),"No user with such login").showAndWait().get() == ButtonType.OK) {
                login.setText("");
                password.setText("");
            }
            return;
        }

        if (encoder.getMethods().checkPassword(password.getText(), loggingInUser.getPassword())) {
            CurrentUser.getLoggedInUser().setUserEntity(loggingInUser);
            closeStage();
            // System.out.println();
            stageManager.showStage("../views/menu.fxml", true, "Menu");
        } else {
            if (showAlert(new WarningAlert(),"Password is incorrect").showAndWait().get() == ButtonType.OK) {
                login.setText("");
                password.setText("");
            }
        }
    }

    private void closeStage() {
        ((Stage) login.getScene().getWindow()).close();
    }


}
