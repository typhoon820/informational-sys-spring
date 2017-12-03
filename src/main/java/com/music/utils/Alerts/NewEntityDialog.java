package com.music.utils.Alerts;

import javafx.scene.control.Dialog;

public class NewEntityDialog  {

    public Dialog showDialog(String text) {
        Dialog dialog = new javafx.scene.control.TextInputDialog();
        dialog.setHeaderText("Enter name of the " + text+ " you want to add");
        dialog.getDialogPane().setPrefSize(300, 220);
        return dialog;
    }
}
