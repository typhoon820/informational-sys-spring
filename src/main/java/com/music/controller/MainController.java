package com.music.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.music.DAO.UserDAO;
import com.music.model.UserEntity;
import com.music.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import com.music.utils.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.soap.Addressing;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

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


        System.out.println(Utils.makeStringBeautiful("songDaoObject"));

    }

    @FXML
    void cancel(ActionEvent event) {
        closeStage();
        loadView("/sample/view/registration.fxml", false, "Registration");
    }


    @FXML
    void login(ActionEvent event) {



        String tLogin = login.getText();
        String pWord = password.getText();
//        UserEntity u = new UserEntity();
//        u.setLogin(tLogin);
//        u.setPassword(pWord);
//        userService.save(u);

        UserEntity loggingInUser = userService.findByName(tLogin);
        List<UserEntity> list = userService.findAll();
        System.out.println(tLogin + "   "+loggingInUser.getLogin());
        if (loggingInUser == null){
            if (Utils.showWarningAlert("No user with such login").get() == ButtonType.OK) {
                login.setText("");
                password.setText("");
            }
            return;
        }


        if (encoder.getMethods().checkPassword(password.getText(), loggingInUser.getPassword())) {
            closeStage();
            // System.out.println();
            loadView("/main/resources/views/menu.fxml", true, "Menu");
        } else {
            if (Utils.showWarningAlert("Password is incorrect").get() == ButtonType.OK) {
                login.setText("");
                password.setText("");
            }
        }
    }

    private void closeStage() {
        ((Stage) login.getScene().getWindow()).close();
    }


}
