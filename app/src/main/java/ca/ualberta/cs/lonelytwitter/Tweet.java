package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shuming2 on 9/15/15.
 */

/*NOTES
constructors methods attributes

_______type name,
private_attributes
protected_attributes
_______
public

"this" is the current object.
ExampleClass
exampleMethod

Throwable <- Exception <- Runtime Exception <- IllegalArgumentException
                       <- IO Exception
 */
public abstract class Tweet extends Object implements Tweetable {
    private String text;
    private Date date;
    ArrayList<Mood> moodList;


    public String getText() {
        return text;
    }

    public void setText(String text) throws IOException {

            if (text.length() <= 140) {
                this.text = text;
            } else throw new IOException("Tweets cannot be that long, Shakespear!");

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tweet(String text) {
        this.text = text;
        this.date = new Date(); //default current date time

    }

    public Tweet( String tweet, Date date) {
        text = tweet;
        this.date = date;
    }

    public abstract Boolean isImportant();

}
