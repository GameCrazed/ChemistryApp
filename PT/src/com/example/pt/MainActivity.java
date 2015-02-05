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

	public MySQLiteHelper db;

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
		// setContentView(R.layout.activity_main.);

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

		db = new MySQLiteHelper(this);

		db.addALL(db);
		db.getElement("Al");

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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
