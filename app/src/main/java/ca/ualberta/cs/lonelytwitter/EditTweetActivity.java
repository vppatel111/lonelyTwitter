package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

// Credit to: https://github.com/watts1/lonelyTwitter/tree/lab6finish
// For creation on EditTweetActivity.
public class EditTweetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        // Get the intent data.
        Intent intent = getIntent();
        String tweetMessage = intent.getStringExtra(LonelyTwitterActivity.ITEM_MESSAGE);

        // Convert the given date back into a date format, its given in an array.
        Date tweetDate = new Date(intent.getLongExtra(LonelyTwitterActivity.ITEM_MESSAGE, 0));

        TextView textView = (TextView) findViewById(R.id.editDisplayText);
        textView.setText(tweetMessage + ": " + tweetDate);
    }
}
