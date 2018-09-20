package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class Surprise extends Mood {

    // Default constructor
    public Surprise() {
        super(new Date());
    }

    // Date is provided
    public Surprise(Date newDate) {
        super(newDate);
    }

    // Returns the surprise
    public String getMood() {
        return "Surprise";
    }

}
