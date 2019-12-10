package net.company.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import net.company.lan.MulticastSocketServer;

import java.io.IOException;

public class PublisherController {
    @FXML
    private TextArea addNewsText;
    @FXML
    private TextArea archiveText;

    public void addNewsButtonClicked(ActionEvent actionEvent) {
        if (!addNewsText.getText().isEmpty()) {
            String text = addNewsText.getText();
            try {
                MulticastSocketServer server = new MulticastSocketServer(6689, "224.1.2.3", 2);
                server.send(text.getBytes());
                text = server.recieve();
                archiveText.appendText(text + "\n\n");
                addNewsText.clear();
                server.leaveGroup();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
