package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shuming2 on 9/15/15.
 */
public class Mood_Good extends Mood {
    public Mood_Good(String mood) {
        super(mood);
    }

    public Mood_Good(String mood, Date date) {
        super(mood, date);
    }



    public Boolean isGood() {
        return Boolean.TRUE;
    }

}
