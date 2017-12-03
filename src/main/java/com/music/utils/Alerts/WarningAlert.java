package com.music.utils.Alerts;

import javafx.scene.control.Alert;

public class WarningAlert implements AlertThrower{
    @Override
    public Alert showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(text);
        alert.setHeaderText("Warning");
        return alert;
    }
}
