package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shuming2 on 9/15/15.
 */
public abstract class Mood {
    private String mood;
    private Date date;

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Mood(String mood) {
        this.mood = mood;
        this.date = new Date(); //default current date time

    }

    public Mood( String mood, Date date) {
        this.mood = mood;
        this.date = date;
    }

    public abstract Boolean isGood();
}
