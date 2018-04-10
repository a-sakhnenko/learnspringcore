package spring.core.beans;

public class Client {
    private String id;
    private String name;

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
}
