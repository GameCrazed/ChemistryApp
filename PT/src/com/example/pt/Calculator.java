package com.example.pt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;


public class Calculator extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Intent intent = getIntent();
        String[] elements = intent.getStringArrayExtra("Extra_Element_String");
        String totalMass = intent.getStringExtra("Extra_Total_Mass");
        int lastIndex = elements.length;
        elements[lastIndex-1] = "Total Mass = " + totalMass;

       ListView lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);
       lv.setAdapter(adapter);
       String[] tempClear = new String[elements.length];
       elements = tempClear;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private AdapterView.OnItemClickListener mCH = new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          //  String Element = (String)adapterView.getItemAtPosition(i);
           // stringArray[2] = Element;
           // TextView tv = (TextView) findViewById(R.id.test_text_view);
            //tv.setText(stringArray[2]);
        }
    };
}
