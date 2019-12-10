package net.company;

import javafx.application.Application;
import net.company.gui.Publisher;
import net.company.gui.Subscriber;

public class Runner {

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("-server")) {
                Application.launch(Publisher.class, args);
            }
        } else {
            Application.launch(Subscriber.class, args);
        }

    }
}
