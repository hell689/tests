package net.company.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.company.gui.controller.PublisherController;
import net.company.gui.controller.SubscriberController;
import net.company.lan.MulticastSocketServer;

import java.net.URL;
import java.util.List;

public class Publisher extends Application {
    // Заголовок окна
    private String title = "Publisher";

    private MulticastSocketServer multicastSocketServer;

    @Override
    public void start(Stage stage) throws Exception {
        List<String> params = getParameters().getUnnamed();
        multicastSocketServer = new MulticastSocketServer(
                Integer.parseInt(params.get(2)), params.get(1), Integer.parseInt(params.get(3)));
        stage.setTitle(title);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/view/publisherView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        PublisherController controller = loader.getController();
        controller.setMulticastSocketServer(multicastSocketServer);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }


    @Override
    public void stop() throws Exception {
        multicastSocketServer.close();
        multicastSocketServer = null;
        super.stop();
    }

}
