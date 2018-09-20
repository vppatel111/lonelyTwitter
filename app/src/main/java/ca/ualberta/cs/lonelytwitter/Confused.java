package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class Confused extends Mood {

    // Default constructor.
    public Confused() {
        super(new Date());
    }

    // Date is given.
    public Confused(Date newDate) {
        super(newDate);
    }

    // Returns the confusion.
    public String getMood() {
        return "Confused";
    }

}
