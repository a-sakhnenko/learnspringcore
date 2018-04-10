package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.loggers.EventLogger;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public void logEvent(Event event) {
        String msg = event.getMsg().replaceAll(client.getId(), client.getName());
        event.setMsg(msg);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        ctx.registerShutdownHook();

        App app = (App) ctx.getBean("app");

        Event event = (Event) ctx.getBean("event");
        event.setMsg("Some event for user 1");
        app.logEvent(event);

        event = (Event) ctx.getBean("event");
        event.setMsg("Some event for user 2");
        app.logEvent(event);

        event = (Event) ctx.getBean("event");
        event.setMsg("Some event for user 3");
        app.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }
}
