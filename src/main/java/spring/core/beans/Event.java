package spring.core.beans;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class Event {
    private String msg;
    private int id;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        id = new Random().nextInt();
        this.date = date;
        this.df = df;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date='" + df.format(date)+ '\'' +
                "}\n";
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
