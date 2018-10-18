package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.List;
import java.util.ListIterator;

public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(LonelyTwitterActivity.class); // Pass the activity class, because we will be accessing
                                            // it. Essentially performing reflection. Note: For this
                                            // lab, it will not be relevant.
    }

    public void testAddTweet() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Tweet");
        tweets.addTweet(tweet);
        assertTrue(tweets.hasTweet(tweet));

    }

    public void testDuplicateTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Tweet");

        try {
            tweets.addTweet(tweet);
            tweets.addTweet(tweet);
            assertTrue(Boolean.FALSE); //If it gets here, it failed to throw an exception.
        } catch (IllegalArgumentException E) {
            assertTrue(Boolean.TRUE); // Caught the exception
        }
    }

    public void testDeleteTweet() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Tweet");
        tweets.addTweet(tweet);
        tweets.deleteTweet(tweet);
        assertFalse(tweets.hasTweet(tweet));

    }

    public void testGetTweetChronologicalOrder() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Tweet");
        tweets.addTweet(tweet);

        Tweet tweet1 = new NormalTweet("Tweet1");
        tweets.addTweet(tweet1);

        List<Tweet> sortedTweets = tweets.getTweets();


        // Checks for chronological order from oldest to newest, includes cases where dates are equal.
        for (int i = 0; i < sortedTweets.size() - 1; i++) {
            assertTrue(sortedTweets.get(i).getDate().compareTo(sortedTweets.get(i+1).getDate()) <= 0);
        }

    }

    public void testHasTweet() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Tweet");
        tweets.addTweet(tweet);

        assertTrue(tweets.hasTweet(tweet));
    }

    public void testGetCount() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Tweet");
        tweets.addTweet(tweet);
        Tweet tweet1 = new NormalTweet("Tweet1");
        tweets.addTweet(tweet1);

        // Check if the test correctly counts 2 tweets.
        assertEquals(2, tweets.getCount());

    }

    public void testGetTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Tweet");
        tweets.addTweet(tweet);

        Tweet returnedTweet = tweets.getTweet(0);

        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
        assertEquals(returnedTweet.getDate(), tweet.getDate());
    }
}
