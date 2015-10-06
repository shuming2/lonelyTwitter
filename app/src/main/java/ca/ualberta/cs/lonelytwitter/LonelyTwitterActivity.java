package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

	private static final String FILENAME = "file.sav";//model
	private EditText bodyText;//view
	private ListView oldTweetsList;//view
	ArrayList<Tweet> tweets = new ArrayList<Tweet>();//model
	ArrayAdapter<Tweet> adapter; //= new ArrayAdapter<Tweet>(this,
			//R.layout.list_item, tweets);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);//view
		setContentView(R.layout.main);//view

		bodyText = (EditText) findViewById(R.id.body);//view
		Button saveButton = (Button) findViewById(R.id.save);//view
		Button clearButton = (Button) findViewById(R.id.clear);//view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);//view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();//controlller
				tweets.add(new NormalTweet(text));//controlller
				adapter.notifyDataSetChanged();//view
				saveInFile();//model
				//saveInFile(text, new Date(System.currentTimeMillis()));
				//finish();
			}
		});
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setResult(RESULT_OK);
				tweets.clear(); //controlller
				adapter.notifyDataSetChanged();//view
				saveInFile();//model
				//saveInFile(text, new Date(System.currentTimeMillis()));
				//finish();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();//model
		//ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);//model
		oldTweetsList.setAdapter(adapter);//model
	}

	private void loadFromFile() {
		//ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);//model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));//model
			Gson gson = new Gson();//model
			//Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();//model
			tweets = gson.fromJson(in, listType);//model
		//	String line = in.readLine();
		//	while (line != null) {
		//		tweets.add(line);
		//		line = in.readLine();
		//	}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//throw new RuntimeException(e);
			//e.printStackTrace();
			tweets = new ArrayList<Tweet>();//model
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);//model
			//e.printStackTrace();
		}
		//return tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);//model
			OutputStreamWriter writer = new OutputStreamWriter(fos);//model
			Gson gson = new Gson();//model
			gson.toJson(tweets,writer);//model
			writer.flush();//model
			//fos.write();
			fos.close();//model
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);//model
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);//model
		}
	}
}