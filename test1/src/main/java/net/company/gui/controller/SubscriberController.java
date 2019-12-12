package net.company.gui.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.company.soc.MulticastSocketServer;

import java.io.IOException;
import java.util.List;

public class SubscriberController {
    @FXML
    private ListView<String> archiveList;

    private MulticastSocketServer multicastSocketServer;

    private ObservableList<String> news;

    @FXML
    public void initialize() {
        news = FXCollections.observableArrayList();
        archiveList.setItems(news);
    }

    /**
     * Инициализация MulticastSocketServer, запуск потока обработки входящих сообщений
     *
     * @param stage    Stage
     * @param settings настройки MulticastSocketServer [1]-address, [2]-port, [3]-ttl
     * @throws IOException
     */
    public void process(Stage stage, List<String> settings) throws IOException {
        multicastSocketServer = new MulticastSocketServer(
                Integer.parseInt(settings.get(2)), settings.get(1), Integer.parseInt(settings.get(3)));
        //Поток для обработки входящих сообщений
        Task task = new Task() {
            @Override
            protected Void call() throws Exception {
                try {
                    while (!Thread.interrupted()) {
                        String text = multicastSocketServer.recieve();
                        if (!text.isEmpty()) {
                            Platform.runLater(() -> {
                                news.add(text);
                            });
                        }
                    }
                } catch (IOException e) {
                    System.err.println(e.getCause() + e.getMessage());
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                thread.interrupt();
                try {
                    //посылаем сообщение, чтобы "отщелкнуть" recieve MulticastSocket-a
                    multicastSocketServer.send(new byte[0]);
                    multicastSocketServer.leaveGroup();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
    }

}
