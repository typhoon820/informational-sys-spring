package com;


import com.music.configuration.SpringFxmlLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractJavaFXApplication extends Application {

    private static String[] savedArgs;

    public static ConfigurableApplicationContext context;
    protected Parent root;

    private SpringFxmlLoader loader = new SpringFxmlLoader();

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        //context.getAutowireCapableBeanFactory().autowireBean(this);

      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        root = (Parent) loader.load("../views/menu.fxml",context);
    }


    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }


    protected static void launchApp(Class<? extends AbstractJavaFXApplication> appClass, String[] args){
        AbstractJavaFXApplication.savedArgs = args;
        Application.launch(appClass,args);
    }
}
