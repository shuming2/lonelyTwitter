package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shuming2 on 9/15/15.
 */
public class BadMood extends Mood {

    public BadMood(String mood) {
        super(mood);
    }

    public BadMood(String mood, Date date) {
        super(mood, date);
    }



    public Boolean isGood() {
        return Boolean.FALSE;
    }



}
