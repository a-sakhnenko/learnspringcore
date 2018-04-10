package spring.core.loggers;

import spring.core.beans.Event;

public interface EventLogger {
    void logEvent(Event event);
}
