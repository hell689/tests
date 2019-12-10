package net.company.gui.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import net.company.lan.MulticastSocketServer;

import java.io.IOException;

public class SubscriberController {
    @FXML
    private TextArea archiveText;

    private Task task;

    @FXML
    public void initialize(){
        archiveText.appendText("init\n");
        task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                String text;
                try (MulticastSocketServer server = new MulticastSocketServer(6689, "224.1.2.3", 2);){
                    while (true) {
                        text = server.recieve();
                        archiveText.appendText(text + "\n\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    };


}
