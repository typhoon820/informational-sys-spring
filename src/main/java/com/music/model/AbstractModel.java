package com.music.model;

public abstract class AbstractModel {

    @Override
    public String toString(){
        return this.getClass().getSimpleName().replaceAll("Entity","");
    }
}
