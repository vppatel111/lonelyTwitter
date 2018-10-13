package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.event.EventListenerSupport;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button searchButton = (Button) findViewById(R.id.searchButton);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				Tweet newTweet = new NormalTweet(text);
				tweetList.add(newTweet);
				new ElasticsearchTweetController.AddTweetTask().execute(newTweet);
				adapter.notifyDataSetChanged();
			}
		});

		searchButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				tweetList.clear();

				try {
					// We need to maintain the original arraylist reference so we have to manually
					// clear all elements and add them back in for it to display correctly.
					ArrayList<Tweet> searchedTweets = new ElasticsearchTweetController.SearchTweetTask().execute(bodyText.getText().toString()).get();
					tweetList.addAll(searchedTweets);

					adapter.notifyDataSetChanged();
				} catch (Exception e) {

				}

			}
		});


	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// We need to use "get" for the class to return a string
		try {
			tweetList = new ElasticsearchTweetController.GetTweetTask().execute("").get();
		} catch (Exception e) {

		}

		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

}