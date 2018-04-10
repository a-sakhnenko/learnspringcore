package spring.core.loggers;

import spring.core.beans.Event;

import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    public CacheFileEventLogger(String fileName, int cacheSize, List<Event> cache) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = cache;
    }
}
