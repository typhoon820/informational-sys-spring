package com.music.configuration;

import com.AbstractJavaFXApplication;
import com.music.controller.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;



public class SpringFxmlLoader {


    ApplicationContext context;

    private static SpringFxmlLoader loader = null;

    private SpringFxmlLoader(){
        initContext();
    }

    public static SpringFxmlLoader getLoader(){
        if (loader==null){
            loader = new SpringFxmlLoader();
        }
        return loader;

    }

    private void initContext(){
        context = AbstractJavaFXApplication.getContext();
    }

    public AbstractController getController(String path){
        initContext();
        try(InputStream fxmlStream = AbstractJavaFXApplication.class.getResourceAsStream(path)){
            System.err.println(SpringFxmlLoader.class.getResourceAsStream(path));
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(clazz -> this.context.getBean(clazz));
            loader.load(fxmlStream);
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object load(String url){
        initContext();
        try(InputStream fxmlStream = AbstractJavaFXApplication.class.getResourceAsStream(url)){
            System.err.println(SpringFxmlLoader.class.getResourceAsStream(url));
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(clazz -> this.context.getBean(clazz));
            return loader.load(fxmlStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
