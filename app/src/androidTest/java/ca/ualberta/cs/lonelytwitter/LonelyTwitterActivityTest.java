package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */

/*
while(){
    write test code =>test fails
    write code =>test passes
}


 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;
    private ListView oldTweetsList;
    private Tweet tweet;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    //It's a UI test
    public void testEditATweet() {
        // starts lonelyTwitter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        // reset the app to a known state
        activity.getTweets().clear();

        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");

            }
        });
        getInstrumentation().waitForIdleSync();// make sure our UI thread finished

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        oldTweetsList = activity.getOldTweetList();
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Copy from https://developer.android.com/training/activity-testing/activity-functional-testing.html
        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());





        //test that the tweet being shown on the edit screen is the tweet we clicked on
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("hamburgers", ((Tweet) oldTweetsList.getItemAtPosition(0)).getText());

        //edit the text of that tweet
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                tweet.setText("donairs");
            }
        });
        getInstrumentation().waitForIdleSync();// make sure our UI thread finished

        //save our edits
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        //assert that our edits were saved into the tweet incorrectly
        oldTweetsList = activity.getOldTweetList();
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("donairs", tweet.getText());
        //assert that our edits are shown on the screen to the user
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("donairs", ((Tweet) oldTweetsList.getItemAtPosition(0)).getText());
        //back in the main activity

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        //end of test: clear the data
        activity.getTweets().clear();
        //end of test: make sure the edit activity is closed
        receiverActivity.finish();
    }

}