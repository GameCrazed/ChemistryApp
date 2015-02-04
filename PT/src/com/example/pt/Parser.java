package com.example.pt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

public class Parser extends Activity {

	// Method that will parse the JSON file and will return a JSONObject
	@SuppressWarnings("unused")
	public String parser() {
		String JSONString = null;
		JSONObject JSONObject;

		try {

			// InputStream inputStream = getAssets().open("table.json");
			// InputStream inputStream = new
			// InputStream("C:\\Users\\Tyba\\Android\\PT\\assets\\table.json");

			// InputStream inputStream = new FileInputStream(
			// "//PT//assets//table.json");
			
			Log.w("1", "1");

			AssetManager assetManager = getAssets();
			Log.w("2", "2");
			InputStream inputStream = assetManager.open("table.json");
			Log.w("3", "3");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			Log.w("4", "4");
			Log.w("The file is ....", in.readLine());

			StringBuilder buf = new StringBuilder();

			while ((JSONString = in.readLine()) != null) {
				buf.append(JSONString);
				Log.w("IN_LOOP", JSONString);
			}

			in.close();

		} catch (IOException ex) {
			Log.e("YOUR_APP_LOG_TAG", "I got an error", ex);

		}

		return JSONString;

	}

	@SuppressWarnings("unused")
	public void use(Context c) {

		Toast.makeText(c, "BEFORE", Toast.LENGTH_SHORT).show(); // Appears

		// Get the JSON object from the data
		// JSONObject parent = this.parser();

		String parent = this.parser();

		final String TAG1 = "TOAST";
		Log.i(TAG1, "I got an error");

		Toast.makeText(c, "AFTER", Toast.LENGTH_SHORT).show();
	}

}