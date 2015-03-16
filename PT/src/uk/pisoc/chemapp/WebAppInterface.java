package uk.pisoc.chemapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;

import java.util.Vector;

public class WebAppInterface extends Activity {
	Context mContext;

	/** Instantiate the interface and set the context */
	WebAppInterface(Context c) {
		mContext = c;

	}

   static Vector<String> symbols = new Vector();
  public static Vector<Element> FINE = new Vector();


    public static Vector<Element> Vector(){

        for (int i=0; i<symbols.size(); i++){
            String x1 = symbols.get(i);
            FINE.add(i, MainActivity.TEST(x1));

        }
        return FINE;

    }


	public void displayElement(String atomicSymbol) {
        boolean calculate = MainActivity.getActivityInstance().getCalculate();


        if (calculate == true) {
            Context context = this;
            int index;
            symbols.add(atomicSymbol);
            try {
                  index = MainActivity.getActivityInstance().getIndex();
            }
            catch (NullPointerException nPE){
                nPE.printStackTrace();
                 index = 0;
            }

            MainActivity.fillDialog(atomicSymbol);
            Double mass = MainActivity.getElementMass();


            try {
                MainActivity.getActivityInstance().setElements(atomicSymbol,mass,index);
               // MainActivity.getActivityInstance().setIndex(index++);
            }
            catch (NullPointerException nPE){
                nPE.printStackTrace();
                nPE.getMessage();
            }
        } else if (calculate != true) {
            // AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            // mContext);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    new ContextThemeWrapper(mContext, android.R.style.Theme_Holo));

            alertDialogBuilder.setMessage(MainActivity.fillDialog(atomicSymbol));
            alertDialogBuilder.setTitle(MainActivity.fillHeading());

            // Back button.
            alertDialogBuilder.setNeutralButton("Back",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            // alertDialog.getWindow().setLayout(800,900);

        }
    }
}
