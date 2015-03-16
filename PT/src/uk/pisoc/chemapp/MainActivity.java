package uk.pisoc.chemapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ListView;

public class MainActivity extends Activity {
    public  int index = 0; //Uses to address elements array. To be incremented after every element is selected
    static MainActivity INSTANCE;   //instantiates a MainActivity so other classes can access methods
    boolean calculate =false;  //holds whether the 'element selector' is active (for calculator)
   public  Element[] elements = new Element[2];

    public void setCalculate(boolean newCalc){
        calculate = newCalc;
    }

    public boolean getCalculate(){
        return calculate;
    }
    public int getIndex(){
        return index;
    }
    public  void setElements(String newSymbol, Double newMass, int index){
        int newIndex = index;

        Element x1 = new Element();
        x1.setAtomicMass(newMass);
        x1.setAtomicSymbol(newSymbol);

        Element x2 = new Element();
        x2.setAtomicMass(newMass);
        x2.setAtomicSymbol(newSymbol);

//        elements[newIndex].setAtomicSymbol(newSymbol);
//        elements[newIndex].setAtomicMass(newMass);

        elements[0] = x1;
        elements[1] = x2;
        index++;

    }


    public static Element TEST(String Symbol){

        return db.getArray(Symbol);
    }


    public Element getElement( int index){
        return getActivityInstance().getElement(index);
    }
    public  void setIndex(int newIndex){
        index = newIndex;
    }

	/*
	 * Explanation of why db is static and why the methods fillDialog and
	 * fillHeading were created. Only one instant of the database class is
	 * required which is created in this class, MainActivity. Next, the
	 * WebAppInterface needs to query the database and display element
	 * information in AlertDialog. The WebAppInterface class takes care of the
	 * AlertDialog and webview and keeps MainActivity clutter-free.
	 * 
	 * The problem is that the instance db cannot be called from
	 * WebAppInterface, a new instance would need to be created and populated
	 * from WebAppInterface which would cause the app to crash.
	 * 
	 * This why the I've created methods in this class for the WebAppInterface
	 * to use.
	 */

	// Database is the class which creates and populates the database.
	public static Database db;

	@SuppressLint("SetJavaScriptEnabled")



	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        INSTANCE = this;
        Log.w("equals", "App started");
		// Create a new WebView for HTML to run
		WebView webView = new WebView(this);

		// Enable JavaScriptin WebView
		webView.getSettings().setJavaScriptEnabled(true);

		// Add the WebAppInterface class to the JavaScript so the HTML button
		// can access the methods from the WebAppInterface class.
		// JavaScriptInterface is called "Symbol".
		webView.addJavascriptInterface(new WebAppInterface(this), "Symbol");

		setContentView(webView);

		// Read & display elements from a HTML file in the WebView
		try {
			InputStream stream = this.getAssets().open("elements.html");
			int streamSize = stream.available();
			byte[] buffer = new byte[streamSize];
			stream.read(buffer);
			stream.close();
			String html = new String(buffer);
			webView.loadData(html, "text/html", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDisplayZoomControls(false);

		// Instantiate the object of Database class.
		db = new Database(this);
		// Populate the database with all 118 elements.
		db.addALL(db);

	}

    public static MainActivity getActivityInstance(){

        return INSTANCE;
    }


    // This method returns the atomic number, mass, and electrons of the element
    // and is then used to populate the body of the AlertDialog.
    // This method is called in the WebAppInterface class, in the method called
    // displayElement.
	// Below method is calling another method called getElement which is in the
	// Database class - please see that class for info on method.
	public static String fillDialog(String atomicSymbol) {
		return db.getElement(atomicSymbol);

	}

	// This method works the same as above except is returns the atomic name and
	// symbol which is shown in the title of the AlertDialog.
	// Again this is called from the WebAppInterface class.
	// Below method is calling another method called getName which is in the
	// Database class.
	public static String fillHeading() {
		return db.getName();

	}

	// This method will return the atomicNumber. The way the database is set up,
	// this is the only way to call it.
	// Use MainActivity.getNumber() in the calculations.
	public static String getNumber(String atomicSymbol) {
		return db.getElement(atomicSymbol);
	}


    public  static double getElementMass(){
        return db.getAtomicMassDB();

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
            MainActivity.getActivityInstance().calculate = !(calculate);

			return true;
		}
        if(item.getItemId() == R.id.action_go_calculator){
         Vector<Element> elements = new Vector();

         elements = WebAppInterface.Vector();
      //   String[] elementString = new String[elements.size()+ 2];

           String[] elementString = new String[elements.size()+ 1];

            String tempStr;
            Double tempDbl;
            Double totalMass = 0.0;
        // for(int i = 0; i < elementString.length - 2;i++){

           for(int i = 0; i < elementString.length -1;i++){

             elementString[i] = elements.elementAt(i).getAtomicSymbol();
             tempDbl=  elements.elementAt(i).getAtomicMass();
             totalMass = totalMass + tempDbl;
             tempStr = tempDbl.toString();
             elementString[i] = elementString[i] + ", " + tempStr;
         }



        Intent intent = new Intent(this, Calculator.class);
        String totalMassStr = totalMass.toString();
        intent.putExtra("Extra_Element_String", elementString);
        intent.putExtra("Extra_Total_Mass", totalMassStr);
        WebAppInterface.Vector().clear();
        startActivity(intent);

        }
		return super.onOptionsItemSelected(item);
	}
    public Bundle setBundle(Bundle b, Element1 element){
        b.putInt("atomicNo",element.atomicNo);
        b.putString("symbol",element.symbol);
        b.putString("name", element.name);
        b.putDouble("mass",element.mass);
        b.putString("electrons", element.electrons);
        return b;

    }

}
