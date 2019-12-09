package net.company.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class Subscriber extends Application {

    private String title = "Subscriber";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(title);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/view/subscriberView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);


        stage.show();
    }
}
