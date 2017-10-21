package com.music.configuration;

import com.AbstractJavaFXApplication;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFxmlLoader {


    public Object load(String url, ApplicationContext context){
        try(InputStream fxmlStream = AbstractJavaFXApplication.class.getResourceAsStream(url)){
            System.err.println(SpringFxmlLoader.class.getResourceAsStream(url));
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(clazz -> context.getBean(clazz));
            return loader.load(fxmlStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
