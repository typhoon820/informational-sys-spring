package com.music.utils.Alerts;


import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class TextInputDialog {

    public Dialog showDialog(String text) {
        Dialog dialog = new javafx.scene.control.TextInputDialog();
        dialog.setHeaderText("You are trying to add a "+ text+" album. " +
                "Please enter version of the song in that album");
        dialog.setTitle("Add album");
        dialog.getDialogPane().setPrefSize(300, 220);
        return dialog;
    }
}
