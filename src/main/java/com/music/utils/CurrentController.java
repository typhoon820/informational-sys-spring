package com.music.utils;

import com.music.controller.AbstractController;

public class CurrentController {
    private static CurrentController controller = null;
    private Class<? extends AbstractController> clazz;

    public static CurrentController getInstance() {
        if (controller == null){
            controller = new CurrentController();
        }
        return controller;
    }

    private CurrentController(){
    }

    public void setController(Class<? extends AbstractController> controller){
        clazz = controller;
    }

    public Class<? extends AbstractController> getController(){
        return clazz;
    }

}
