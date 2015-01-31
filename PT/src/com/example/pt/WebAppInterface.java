package com.example.pt;

import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class WebAppInterface extends Activity {
	Context mContext;

	/** Instantiate the interface and set the context */
	WebAppInterface(Context c) {
		mContext = c;
	}

	public void displayElement(String toast) {
		// Show toast of the atomic symbol - ID
		// Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				mContext);

		alertDialogBuilder.setTitle(toast);

		// This turd needs to pick up data from the JSON file and display it.
		alertDialogBuilder.setMessage(toast);

		// set neutral button to exit alert
		alertDialogBuilder.setNeutralButton("Back",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		// show alert
		alertDialog.show();

		Parser a = new Parser();
		try {
			a.use(mContext);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
