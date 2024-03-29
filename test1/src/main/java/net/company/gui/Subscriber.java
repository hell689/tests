package net.company.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.company.gui.controller.SubscriberController;

import java.net.URL;
import java.util.List;

public class Subscriber extends Application {
    // Заголовок окна
    private String title = "Subscriber";

    @Override
    public void start(Stage stage) throws Exception {
        // Получаем параметры
        List<String> params = getParameters().getUnnamed();
        // устанавливаем заголовок окна
        stage.setTitle(title);
        //Загружаем FXML разметку
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/view/subscriberView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        // Получаем контроллер и передаем в него параметры и Stage
        SubscriberController controller = loader.getController();
        controller.process(stage, params);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

}
