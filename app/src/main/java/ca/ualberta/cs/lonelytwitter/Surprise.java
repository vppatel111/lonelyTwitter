package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class Surprise extends Mood {

    public Surprise() {
        super(new Date());
    }

    public Surprise(Date newDate) {
        super(newDate);
    }

    public String getMood() {
        return "Surprise";
    }

}
