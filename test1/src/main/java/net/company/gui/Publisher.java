package net.company.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Publisher extends Application {
    // Заголовок окна
    private String title = "Publisher";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(title);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/view/publisherView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }
}
