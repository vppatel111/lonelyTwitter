package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

/**
 * Created by romansky on 10/20/16.
 */
public class ElasticsearchTweetController {

    private static JestDroidClient client = null;

    public static class AddTweetTask extends AsyncTask<Tweet, Void, Void> {

        protected Void doInBackground(Tweet ... params) {
            setClient();

            Tweet tweet = params[0];
            Index index = new Index.Builder(tweet)
                    .index("vishal-wednesday")
                    .type("tweet").build();

            try {
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    tweet.setTweetId(result.getId());
                }
            } catch (IOException e) {

            }
            return null;
        }
    }

    public static class GetTweetTask extends AsyncTask<String, Void, ArrayList<Tweet>> {

        protected ArrayList<Tweet> doInBackground(String... params) {
            setClient();

            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            Search search = new Search.Builder(params[0])
                    .addIndex("vishal-wednesday")
                    .addType("tweet")
                    .build();

            try {
                JestResult result = client.execute(search);

                if (result.isSucceeded()) {
                    List<NormalTweet> tweetList;

                    // Returns all tweets as a list, but we need to define as a type.
                    // We can't save into an arrayList because result only gives us a datatype of
                    // list, we simply add back into the array list.
                    tweetList = result.getSourceAsObjectList(NormalTweet.class);

                    Log.d("VISHALTAG", "doInBackground: Get successful " + tweetList.size());
                    tweets.addAll(tweetList);
                }
            } catch (IOException e) {

            }

            return tweets;
        }

    }

    // TODO: Change the index
    public static class SearchTweetTask extends AsyncTask<String, Void, ArrayList<Tweet>> {

        protected ArrayList<Tweet> doInBackground(String... params) {
            setClient();

            ArrayList<Tweet> tweets = new ArrayList<Tweet>();

            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"filtered\" : {\n" +
                    "            \"query\" : {\n" +
                    "                \"query_string\" : {\n" +
                    "                    \"query\" : \"test\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            \"filter\" : {\n" +
                    "                \"term\" : { \"message\" : \"" + params[0] + "\" }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            Search search = new Search.Builder(query)
                    .addIndex("vishal-wednesday")
                    .addType("tweet")
                    .build();

            try {
                JestResult result = client.execute(search);

                if (result.isSucceeded()) {
                    List<NormalTweet> tweetList;

                    // Returns all tweets as a list, but we need to define as a type.
                    // We can't save into an arrayList because result only gives us a datatype of
                    // list, we simply add back into the array list.
                    tweetList = result.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(tweetList);
                }
            } catch (IOException e) {

            }

            return tweets;
        }

    }

    // Create the JestClient as a singleton.
    public static void setClient() {
        if (client == null) {
            DroidClientConfig config = new DroidClientConfig
                    .Builder("http://cmput301.softwareprocess.es:8080/").build(); // Builder pattern.
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}