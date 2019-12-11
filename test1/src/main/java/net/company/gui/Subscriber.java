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
import net.company.gui.controller.SubscriberController;
import net.company.lan.MulticastSocketServer;

import java.net.URL;
import java.util.List;

public class Subscriber extends Application {
    // Заголовок окна
    private String title = "Subscriber";

    private MulticastSocketServer multicastSocketServer;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(title);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/view/subscriberView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        List<String> params = getParameters().getUnnamed();
        multicastSocketServer = new MulticastSocketServer(
                Integer.parseInt(params.get(2)), params.get(1));
        SubscriberController controller = loader.getController();
        controller.setMulticastSocketServer(multicastSocketServer);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        multicastSocketServer.close();
        super.stop();
    }
}
