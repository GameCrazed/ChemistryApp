package com.example.pt;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

	public static MySQLiteHelper db;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create a new WebView for HTML to run
		WebView webview = new WebView(this);

		// Enable JavaScriptin WebView
		webview.getSettings().setJavaScriptEnabled(true);

		// Add the WebAppInterface class to the JavaScript so the HTML button
		// can access the methods from the WebAppInterface class.
		// JavaScriptInterface is called "ID".
		webview.addJavascriptInterface(new WebAppInterface(this), "ID");

		setContentView(webview);

		// Read & display elements from a HTML file in the WebView
		try {
			InputStream stream = this.getAssets().open("elements.html");
			int streamSize = stream.available();
			byte[] buffer = new byte[streamSize];
			stream.read(buffer);
			stream.close();
			String html = new String(buffer);
			webview.loadData(html, "text/html", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		webview.getSettings().setBuiltInZoomControls(true);

		db = new MySQLiteHelper(this);
		db.addALL(db);

	}

	// This method returns the atomic number, mass, and electrons of the element
	// and is then used to populate the body of the AlertDialog.
	// This method is called in the WebAppInterface class, in the method called
	// displayElement @ line 22.
	public static String fillDialog(String atomicSymbol) {
		return db.getElement(atomicSymbol);

	}

	// This method works the same as above except is returns the atomic name and
	// symbol which is shown in the title of the AlertDialog.
	// Again this is called from the WebAppInterface class @ line 23.
	public static String fillHeading() {
		return db.getName();

	}

	// This method will return the atomicNumber. The way the database is set up,
	// this is the only way to call it.
	// Use MainActivity.getNumber() in the calculations.
	public static int getNumber() {
		return db.getNumberFromDB();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_Calculate_Switch) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
