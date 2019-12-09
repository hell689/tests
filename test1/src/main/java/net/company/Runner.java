package net.company;

import javafx.application.Application;
import net.company.gui.Publisher;
import net.company.gui.Subscriber;

public class Runner {

    public static void main(String[] args) {
        Application.launch(Publisher.class, args);
        //Application.launch(Subscriber.class, args);
    }

}
