package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class Confused extends Mood {

    // Handles default case
    public Confused() {
        super(new Date());
    }

    public Confused(Date newDate) {
        super(newDate);
    }

    // Returns the confusion.
    public String getMood() {
        return "Confused";
    }

}
