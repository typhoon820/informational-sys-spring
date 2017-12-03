package com.music.Security;

import com.music.entity.UserEntity;

public class CurrentUser {
    private UserEntity userEntity;
    private static CurrentUser currentUser = null;

    private CurrentUser(){
    }

    public static CurrentUser getLoggedInUser(){
        if (currentUser == null){
            currentUser = new CurrentUser();
        }
        return currentUser;
    }

    public UserEntity getUser(){
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
