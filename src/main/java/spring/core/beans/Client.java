package spring.core.beans;

public class Client {
    private String id;
    private String name;
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
}
