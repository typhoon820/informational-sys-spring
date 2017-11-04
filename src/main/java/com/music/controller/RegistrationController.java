package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.music.model.UserEntity;
import com.music.service.UserService;
import com.music.service.UserStatusService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


import com.music.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RegistrationController extends AbstractController implements Initializable {

    //private DatabaseHandler handler;

    private Encoder encoder;

    @Autowired
    private UserService userDAO;

    @Autowired
    private UserStatusService statusService;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // handler = DatabaseHandler.getInstance();
        //userDAO = new UserDAO();
        encoder = Encoder.getInstance();
    }



    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField repeatedPassword;

    @FXML
    private JFXButton regButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void cancel(ActionEvent event) {
        closeStage();
        stageManager.showStage("../views/sample.fxml", false, "login");
    }

    @FXML
    void register(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        System.out.println("===================" + userDAO.findByName(login.getText()));

        if (userDAO.findByName(login.getText()) != null) {
            if (Utils.showWarningAlert("Login is already in use").get() == ButtonType.OK) {
                login.setText("");
                password.setText("");
                repeatedPassword.setText("");
            }
        }
        else {
            if (password.getText().equals(repeatedPassword.getText())) {
                UserEntity user = new UserEntity();
                //user.setId(20);
                user.setLogin(login.getText());
                user.setPassword(encoder.getMethods().encryptPassword(password.getText()));
               // user.setUserStatus(statusService.findByName("User"));
                userDAO.save(user);
                if (Utils.showSuccessAlert("You have been registered successfully").get() == ButtonType.OK){
                    closeStage();
                    stageManager.showStage("../views/sample.fxml", false, "login");
                }

            } else {
                if (Utils.showWarningAlert("Passwords do not match").get() == ButtonType.OK) {
                    password.setText("");
                    repeatedPassword.setText("");
                }
            }

        }
    }

    private void closeStage(){
        ((Stage)login.getScene().getWindow()).close();
    }
}
