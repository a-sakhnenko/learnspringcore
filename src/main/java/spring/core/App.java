package spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.beans.Client;
import spring.core.beans.Event;
import spring.core.beans.EventType;
import spring.core.loggers.EventLogger;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public void logEvent(EventType type, Event event) {
        String msg = event.getMsg().replaceAll(client.getId(), client.getName());
        event.setMsg(msg);
        EventLogger logger = loggers.getOrDefault(type, defaultLogger);
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        ctx.registerShutdownHook();

        App app = (App) ctx.getBean("app");

        Event event = (Event) ctx.getBean("event");
        event.setMsg("Some event for user 1");
        app.logEvent(null, event);

        event = (Event) ctx.getBean("event");
        event.setMsg("Some event for user 2");
        app.logEvent(EventType.INFO, event);

        event = (Event) ctx.getBean("event");
        event.setMsg("Some event for user 3");
        app.logEvent(EventType.ERROR, event);
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }
}
