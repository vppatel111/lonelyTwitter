package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear); // Find clear button.
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		// Add an click listener to the clear button
		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				tweets.clear(); // Clear the tweet array list
				adapter.notifyDataSetChanged(); // Update the list view
				saveInFile();

			}

		});

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

//				// Old Lab Assignment #2
//				// Adds moods into a generic list and adds them to the final string.
//				ArrayList<Mood> moodList = new ArrayList<Mood>();
//				Confused confused = new Confused();
//				Surprise surprised = new Surprise();
//
//				moodList.add(confused);
//				moodList.add(surprised);
//
//				setResult(RESULT_OK);
//
//				// Build the string
//
//
//				// Add the moods
//				for (Mood mood : moodList) {
//					text += mood.getMood();
//				}

				// Only need this for current assignment.
				String text = bodyText.getText().toString();
				ImportantTweet newTweet = new ImportantTweet();

				try {
					newTweet.setMessage(text); // We have to deal with the exception
					tweets.add(newTweet);
				} catch (TooLongTweetException e) {
					// Handle the exception.
				}

				adapter.notifyDataSetChanged(); // Magic~
				saveInFile();

			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart(); // Have to call the super to setup our basic things.
		loadFromFile();

		// This means current context
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		try {

			FileInputStream fis = openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);

			Gson gson = new Gson();

			//We create a 'token' to tell java what to convert to
			Type typeListTweets = new TypeToken<ArrayList<ImportantTweet>>(){}.getType();
			tweets = gson.fromJson(reader, typeListTweets); //typeListTweets means nothing on its own

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter writer = new BufferedWriter(osw);

			Gson gson = new Gson();
			gson.toJson(tweets, writer);
			writer.flush();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}