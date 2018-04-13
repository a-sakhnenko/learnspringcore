package spring.core.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import spring.core.config.AppConfig;
import spring.core.config.LoggerConfig;
import spring.core.loggers.EventLogger;

import java.util.Map;

@Component
public class App {
    @Autowired
    private Client client;
    @Autowired
    @Qualifier("cacheFileEventLogger")
    private EventLogger defaultLogger;
    @Autowired
    private Map<EventType, EventLogger> loggers;

    public void logEvent(EventType type, Event event) {
        String msg = event.getMsg().replaceAll(client.getId(), client.getName());
        event.setMsg(msg);
        EventLogger logger = loggers.getOrDefault(type, defaultLogger);
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, LoggerConfig.class);
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

        System.out.println(app.client.getGreeting());
    }

    public App() {
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public void setLoggers(Map<EventType, EventLogger> loggers) {
        this.loggers = loggers;
    }
}
