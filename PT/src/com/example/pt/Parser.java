package com.example.pt;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class Parser extends Activity {

	// Method that will parse the JSON file and will return a JSONObject
	public JSONObject parser() {
		String JSONString = null;
		JSONObject JSONObject = null;

		try {

			// open the inputStream to the file
			InputStream inputStream = getAssets().open("table.json");

			int sizeOfJSONFile = inputStream.available();

			// array that will store all the data
			byte[] bytes = new byte[sizeOfJSONFile];

			// reading data into the array from the file
			inputStream.read(bytes);

			// close the input stream
			inputStream.close();

			JSONString = new String(bytes, "UTF-8");
			JSONObject = new JSONObject(JSONString);

		} catch (IOException ex) {

			ex.printStackTrace();
			return null;

		} catch (JSONException x) {

			x.printStackTrace();
			return null;
		}

		return JSONObject;
	}

	public void use(Context c) throws JSONException {

		Toast.makeText(c, "BEFORE", Toast.LENGTH_SHORT).show(); // Appears

		// Get the JSON object from the data
		JSONObject parent = this.parser(); // This line causing problem. Nothing
		// happens after executing this line

		Toast.makeText(c, "AFTER", Toast.LENGTH_SHORT).show(); // Does not
		// appear

		// THis will store all the values inside "Hydrogen" in a element string
		String element = parent.getString("Hydrogen");

		Toast.makeText(c, element, Toast.LENGTH_SHORT).show();

		// THis will store "1" inside atomicNumber
		String atomicNumber = parent.getJSONObject("Hydrogen").getString(
				"atomic_number");

		Toast.makeText(c, atomicNumber, Toast.LENGTH_SHORT).show();

	}

}