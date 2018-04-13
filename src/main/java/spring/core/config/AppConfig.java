package spring.core.config;

import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import spring.core.beans.App;
import spring.core.beans.Client;
import spring.core.beans.Event;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@PropertySource("classpath:client.properties")
@ComponentScan
public class AppConfig {
    @Value("${id}")
    private String id;

    @Value("${name}")
    private String name;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Client client() {
        return new Client(id, name);
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }

    @Bean
    public App app() {
        return new App();
    }
}
