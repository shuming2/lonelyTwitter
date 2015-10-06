package ca.ualberta.cs.lonelytwitter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import ca.ualberta.cs.lonelytwitter.MyObserver;

/**
 * Created by shuming2 on 9/29/15.
 */
public class TweetList implements MyObservable, MyObserver{
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
        tweets.add(tweet);
        tweet.addObserver(this);
        notifyAllObservers();
    }

    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }

    public int count() {
        return tweets.size();
    }

    public void addTweet(Tweet tweet) {
        if (tweets.contains(tweet)) {
            throw new IllegalArgumentException();
        } else {
            mostRecentTweet = tweet;
            tweets.add(tweet);
        }
    }

    public Tweet[] getTweets() {

        Tweet list[] = new Tweet[tweets.size()];
        list = tweets.toArray(list);
        return list;
    }

    public boolean hasTweet(Tweet tweet) {
        if (!tweets.contains(tweet)) {
            return false;
        } else {
            return true;
        }
    }

    public void removeTweet(Tweet t) {
        tweets.remove(t);
    }

    public int getCount() {
        return tweets.size();
    }

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable){
        notifyAllObservers();
    }

}
