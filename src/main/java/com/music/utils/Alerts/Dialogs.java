package com.music.utils.Alerts;

import javafx.scene.control.Dialog;

public class Dialogs {
    public static Dialog show(DialogShowable dialog){
        return dialog.show();
    }
}
