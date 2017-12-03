package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.music.Security.Encoder;
import com.music.entity.UserEntity;
import com.music.service.UserService;
import com.music.service.UserStatusService;
import com.music.utils.Alerts.SuccessAlert;
import com.music.utils.Alerts.WarningAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


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
        System.out.println("===================" + userDAO.findByName(login.getText()));
        if (userDAO.findByName(login.getText()) != null) {
            if (showAlert(new WarningAlert(),"Login is already in use").showAndWait().get() == ButtonType.OK) {
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
                if (showAlert(new SuccessAlert(),"You have been registered successfully").showAndWait().get() == ButtonType.OK){
                    closeStage();
                    stageManager.showStage("../views/sample.fxml", false, "login");
                }

            } else {
                if (showAlert(new WarningAlert(), "Passwords do not match").showAndWait().get() == ButtonType.OK) {
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
