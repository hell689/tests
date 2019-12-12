package net.company.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.company.gui.controller.PublisherController;
import net.company.soc.MulticastSocketServer;

import java.net.URL;
import java.util.List;

public class Publisher extends Application {
    // Заголовок окна
    private String title = "Publisher";

    private MulticastSocketServer multicastSocketServer;

    @Override
    public void start(Stage stage) throws Exception {
        List<String> params = getParameters().getUnnamed();
        stage.setTitle(title);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/view/publisherView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        PublisherController controller = loader.getController();
        controller.process(stage, params);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}
