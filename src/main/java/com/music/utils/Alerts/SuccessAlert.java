package com.music.utils.Alerts;

import javafx.scene.control.Alert;

public class SuccessAlert implements AlertThrower {
    @Override
    public Alert showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(text);
        alert.setHeaderText("Success");
        return alert;
    }
}
