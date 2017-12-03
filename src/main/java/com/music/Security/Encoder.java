package com.music.Security;


import org.jasypt.util.password.BasicPasswordEncryptor;

public class Encoder {
    private static Encoder encoder;

    private BasicPasswordEncryptor encryptor;

    private Encoder(){
        initPasswordEncoder();
    }

    public static Encoder getInstance(){
        if (encoder == null){
            return new Encoder();
        }
        return encoder;
    }

    private void initPasswordEncoder(){
        encryptor = new BasicPasswordEncryptor();
    }

    public BasicPasswordEncryptor getMethods(){
        return encryptor;
    }
}
