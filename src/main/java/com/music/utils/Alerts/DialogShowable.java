package com.music.utils.Alerts;


import javafx.scene.control.Dialog;
import org.springframework.stereotype.Component;

@Component
public interface DialogShowable {
    Dialog show();
}
