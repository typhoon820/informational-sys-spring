package com;

import com.music.configuration.SpringFxmlLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application extends AbstractJavaFXApplication {


    private SpringFxmlLoader loader = SpringFxmlLoader.getLoader();

//    @Qualifier("mainView")
//    @Autowired
//    private ControllerConfig.ViewHolder view;
//
//    @Qualifier("menuView")
//    @Autowired
//    ControllerConfig.ViewHolder viewHolder;


    @Override
    public void start(Stage stage) throws Exception {


        Parent root = (Parent)loader.load("../views/sample.fxml");
        stage.setTitle("login");
        stage.setScene(new Scene(root, 300, 200));
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }
}
