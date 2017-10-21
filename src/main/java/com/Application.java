package com;

import com.music.configuration.ControllerConfig;
import com.music.configuration.SpringFxmlLoader;
import com.music.controller.MainController;
import com.music.view.autogen.FxmlEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application extends AbstractJavaFXApplication {

    private static final SpringFxmlLoader loader = new SpringFxmlLoader();

//    @Qualifier("mainView")
//    @Autowired
//    private ControllerConfig.ViewHolder view;
//
//    @Qualifier("menuView")
//    @Autowired
//    ControllerConfig.ViewHolder viewHolder;


    @Override
    public void start(Stage stage) throws Exception {


        //Parent root1 = (Parent)load("/sample.fxml");
        stage.setTitle("login");
        stage.setScene(new Scene(root, 300, 200));
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }
}
