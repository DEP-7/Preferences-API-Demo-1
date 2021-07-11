package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import java.util.prefs.Preferences;

public class MainFormController {
    public AnchorPane root;
    public ColorPicker clrPicker;

    public void clrPicker_OnAction(ActionEvent actionEvent) {
        root.setBackground(new Background(new BackgroundFill(clrPicker.getValue(), null, null)));
        Preferences.userRoot().node("my-app-1").put("color", clrPicker.getValue().toString());
    }
}
