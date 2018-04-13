package spring.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.beans.EventType;
import spring.core.loggers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoggerConfig {

    @Bean
    public EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public EventLogger fileEventLogger() {
        return new FileEventLogger("tempLogFile");
    }

    @Bean
    public EventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("tempLogFile", 2, new ArrayList<>());
    }

    @Bean
    public EventLogger combinedEventLogger() {
        return new CombinedEventLogger(Arrays.asList(fileEventLogger(), consoleEventLogger()));
    }

    @Bean
    public Map<EventType, EventLogger> loggers() {
        HashMap<EventType, EventLogger> map = new HashMap<>();
        map.put(EventType.INFO, consoleEventLogger());
        map.put(EventType.ERROR, fileEventLogger());
        return map;
    }


}
