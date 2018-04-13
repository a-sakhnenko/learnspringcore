package spring.core.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client {
    @Value("${id}")
    private String id;
    @Value("${name}")
    private String name;
    @Value("${greeting}")
    private String greeting;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
