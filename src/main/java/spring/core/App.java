package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.loggers.EventLogger;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent("Some event for user 1");
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }
}