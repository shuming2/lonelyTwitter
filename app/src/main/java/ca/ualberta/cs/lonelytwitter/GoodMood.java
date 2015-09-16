package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by shuming2 on 9/15/15.
 */
public class GoodMood extends Mood {
    public GoodMood(String mood) {
        super(mood);
    }

    public GoodMood(String mood, Date date) {
        super(mood, date);
    }



    public Boolean isGood() {
        return Boolean.TRUE;
    }

}
