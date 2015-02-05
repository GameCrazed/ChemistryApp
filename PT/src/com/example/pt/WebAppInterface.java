package com.example.pt;

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

	public void displayElement(String atomicSymbol) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				mContext);

		alertDialogBuilder.setMessage(MainActivity.fillDialog(atomicSymbol));
		alertDialogBuilder.setTitle(MainActivity.fillHeading());

		// set neutral button to exit alert
		alertDialogBuilder.setNeutralButton("Back",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		// show alert
		alertDialog.show();

	}
}
