package net.company.gui.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import net.company.lan.MulticastSocketServer;

import java.io.IOException;

public class SubscriberController {
    @FXML
    private TextArea archiveText;

    private MulticastSocketServer multicastSocketServer;

    private Task task;

    @FXML
    public void initialize(){
        archiveText.appendText("init\n");
        task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                String text;
                try {
                    while (true) {
                        text = multicastSocketServer.recieve();
                        archiveText.appendText(text + "\n\n");
                    }
                } catch (IOException e) {
                    System.err.println(e.getCause() + e.getMessage());
                }
                return null;
            }
        };
        new Thread(task).start();
    };

    public void setMulticastSocketServer (MulticastSocketServer server) {
        multicastSocketServer = server;
    }


}
