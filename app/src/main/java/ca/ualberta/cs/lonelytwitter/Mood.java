package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public abstract class Mood {

    // Date is in the package scope
    protected Date date;

    // Each emotion should implement getMood individually.
    public abstract String getMood();

    // Default constructor, we will initialize Mood with a date.
    public Mood(Date setDate) {
        this.date = setDate;
    }

    // Getter method for date
    public Date getDate() {
        return this.date;
    }

    // Setter method for date
    public void setDate(Date newDate) {
        this.date = newDate;
    }

}
