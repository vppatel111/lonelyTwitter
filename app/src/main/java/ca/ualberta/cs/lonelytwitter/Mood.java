package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public abstract class Mood {
    //Date setter and gette methds
    //2 Constructors, one sets the date to default and the other takes an arguement
    //Encapsulation should be followed
    //Mood dependant format method which returns a string representing a mood
    // Add a way for tweet to have a list of moods.

    // In general we need, classes, methods, attributes, access modifiers,
    // encapsulation, construtors, inheritance, polymorphism, abstract base classes and
    // generic types.

    private Date date;

    public Mood() {
        date = new Date();
    }

    public Mood(Date pDate) {
        date = pDate;
    }


}
