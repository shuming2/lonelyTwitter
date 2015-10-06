package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by shuming2 on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver {
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
/*
    public void setUp() {
    }

    public void tearDown() {
    }
*/
    public void testHoldsStuff() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }

    public void testHoldsManyThings() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertEquals(list.count(), 1);
        list.add(new NormalTweet("test"));
        assertEquals(list.count(), 2);
    }

    public void testNoDuplicate() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        list.addTweet(new NormalTweet("test"));
        assertEquals(list.count(), 1);
    }
    public void testGetTweets() {
        TweetList list = new TweetList();
        Tweet tweet1 = new NormalTweet("test1");
        list.addTweet(tweet1);
        Tweet tweet2 = new NormalTweet("test2");
        list.addTweet(tweet2);
        assertTrue(list.getTweets() == new Tweet[]{tweet1, tweet2});
    }

    public void testHasTweet() {
        TweetList list = new TweetList();
        Tweet tweet1 = new NormalTweet("test1");
        list.addTweet(tweet1);
        assertTrue(list.hasTweet(tweet1));
    }

    private Boolean weWereNotified;

    public void myNotify(MyObservable observable) {
        weWereNotified = Boolean.TRUE;
    }


    public void testObservable() {
        TweetList list = new TweetList();
        // needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        weWereNotified = Boolean.FALSE;
        list.add(tweet);
        assertTrue(weWereNotified);
    }
    public void testModifyTweetInList() {
        TweetList list = new TweetList();
        // needs an addObserver
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        weWereNotified = Boolean.FALSE;
        tweet.setText("different tweet");
        assertTrue(weWereNotified);
    }




}