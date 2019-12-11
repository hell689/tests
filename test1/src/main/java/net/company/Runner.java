package net.company;

import javafx.application.Application;
import net.company.gui.Publisher;
import net.company.gui.Subscriber;
import net.company.util.SettingsAnalyzer;

public class Runner {

    public static void main(String[] args) {
        String[] settings = SettingsAnalyzer.analyzeSettings(args);
        if (settings[0].equals("server")) {
                Application.launch(Publisher.class, settings);
        } else {
            Application.launch(Subscriber.class, settings);
        }
    }
}
