import controller.MainFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.event.AncestorEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/MainForm.fxml"));
        AnchorPane root = loader.load();
        MainFormController controller = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Preferences API Demo 1");
        primaryStage.show();

        String color = Preferences.userRoot().node("my-app-1").get("color", "white");
        boolean isMaximized = Preferences.userRoot().node("my-app-1").getBoolean("is-maximized", false);
        double xPos = Preferences.userRoot().node("my-app-1").getDouble("x-pos", -1);
        double yPos = Preferences.userRoot().node("my-app-1").getDouble("y-pos", -1);
        double width = Preferences.userRoot().node("my-app-1").getDouble("width", -1);
        double height = Preferences.userRoot().node("my-app-1").getDouble("height", -1);

        root.setBackground(new Background(new BackgroundFill(Color.web(color), null, null)));
        primaryStage.setMaximized(isMaximized);

        if (!isMaximized) {
            if (width == -1 && height == -1) {
                primaryStage.setWidth(root.getPrefWidth());
                primaryStage.setHeight(root.getPrefHeight());
            }else{
                primaryStage.setWidth(width);
                primaryStage.setHeight(height);
            }

            if (xPos == -1 && yPos == -1) {
                primaryStage.centerOnScreen();
            }else{
                primaryStage.setX(xPos);
                primaryStage.setY(yPos);
            }
        }

        primaryStage.setOnCloseRequest(event -> {

            Preferences.userRoot().node("my-app-1").putBoolean("is-maximized", primaryStage.isMaximized());
            if (!primaryStage.isMaximized()) {
                Preferences.userRoot().node("my-app-1").putDouble("x-pos", primaryStage.getX());
                Preferences.userRoot().node("my-app-1").putDouble("y-pos", primaryStage.getY());
                Preferences.userRoot().node("my-app-1").putDouble("width", primaryStage.getWidth());
                Preferences.userRoot().node("my-app-1").putDouble("height", primaryStage.getHeight());
            }
        });
    }
}
