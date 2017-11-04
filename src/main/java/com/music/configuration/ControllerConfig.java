package com.music.configuration;

import com.AbstractJavaFXApplication;
import com.music.controller.AbstractController;
import com.music.controller.InsertionController;
import com.music.controller.MainController;
import com.music.controller.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ControllerConfig {

//    @Bean
//    public SpringFxmlLoader getLoader(){
//        return SpringFxmlLoader.getLoader();
//    }

    @Bean
    public ApplicationContext getContext(){
        return AbstractJavaFXApplication.getContext();
    }


////
////    @Bean(name ="mainView")
////    public ViewHolder getMainView() throws IOException{
////        return loadView("views/sample.fxml");
////    }
//
////    @Bean(name ="menuView")
////    public ViewHolder getMenuView() throws IOException{
////        return loadView("views/menu.fxml");
////    }
//
////    @Bean(name = "insertionBox")
////    public ViewHolder getInsertionBox() throws IOException{
////        return loadView("views/menu.fxml");
////    }
////
////    @Bean
////    public InsertionController getInsertionController() throws IOException{
////        return (InsertionController) getInsertionBox().getController();
////    }
//
////    @Bean
////    public MenuController getMenuController() throws IOException{
////        return (MenuController) getMenuView().getController();
////    }
////
////    @Bean
////    public MainController getMainController() throws IOException{
////        return (MainController) getMainView().getController();
////    }
//
//
//    protected ViewHolder loadView(String path) throws IOException{
//        InputStream fxmlStream = null;
//        try{
//            fxmlStream = getClass().getClassLoader().getResourceAsStream(path);
//            FXMLLoader loader = new FXMLLoader();
//            loader.load(fxmlStream);
//            return new ViewHolder(loader.getRoot(), loader.getController());
//        }
//        finally {
//            if (fxmlStream != null){
//                fxmlStream.close();
//            }
//        }
//    }
//
//
//
//    public class ViewHolder{
//
//        private Parent view;
//        private Object controller;
//
//        public ViewHolder(Parent view, Object controller){
//            this.view = view;
//            this.controller = controller;
//        }
//
//        public Parent getView() {
//            return view;
//        }
//
//        public void setView(Parent view) {
//            this.view = view;
//        }
//
//        public Object getController() {
//            return controller;
//        }
//
//        public void setController(Object controller) {
//            this.controller = controller;
//        }
//    }
}
