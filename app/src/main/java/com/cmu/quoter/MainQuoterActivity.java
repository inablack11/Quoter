package com.cmu.quoter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;


public class MainQuoterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quoter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_quoter, menu);
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

    public void onClick(View view){

        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("hi");
        alertDialog.setMessage("this is my app");
        final EditText input = new EditText(this);
        alertDialog.setView(input);

        alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Text entered", input.getText().toString());
            }
        });

        alertDialog.show();
    }

    private void dbInit() {
        SQLiteDatabase mydatabase = openOrCreateDatabase("quoterDB",MODE_PRIVATE,null);

    }
}
