package net.company.gui.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import net.company.lan.MulticastSocketServer;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class PublisherController {
    @FXML
    private TextArea addNewsText;
    @FXML
    private TextArea archiveText;

    private MulticastSocketServer multicastSocketServer;

    @FXML
    public void initialize() {
        Task task = new Task() {
            @Override
            protected Void call() throws Exception {
                String text;
                try {
                    while (true) {
                        text = multicastSocketServer.recieve();
                        archiveText.appendText(text + "\n---------\n");
                    }
                } catch (IOException e) {
                    System.err.println(e.getCause() + e.getMessage());
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    public void addNewsButtonClicked(ActionEvent actionEvent) {

        if (!addNewsText.getText().isEmpty()) {
            String text = addNewsText.getText();
            try {
                multicastSocketServer.send(text.getBytes());
                addNewsText.clear();
            } catch (IOException e) {
                System.err.println("Ошибка сокета: " + e.getMessage());
            }
        }
    }

    public void setMulticastSocketServer (MulticastSocketServer server) {
        multicastSocketServer = server;
    }
}
