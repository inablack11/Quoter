package com.cmu.quoter;

/**
 * Created by inablack11 on 5/31/2015.
 */
import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {
    private QuotesDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new QuotesDataSource(this);
        datasource.open();

        List<Quote> values = datasource.getAllQuotes();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Quote> adapter = new ArrayAdapter<Quote>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Quote> adapter = (ArrayAdapter<Quote>) getListAdapter();
        Quote comment = null;
        switch (view.getId()) {
            case R.id.add:
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
//                comment = datasource.createQuote(comments[nextInt]);
//                adapter.add(comment);
                onAdd(adapter);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Quote) getListAdapter().getItem(0);
                    datasource.deleteQuote(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    public void onAdd(final ArrayAdapter<Quote> adapter){

        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("add new quote");
        alertDialog.setMessage("this is my app");
        final EditText input = new EditText(this);
        alertDialog.setView(input);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Text entered", input.getText().toString());
                Quote comment = null;
                comment = datasource.createQuote(input.getText().toString());
                adapter.add(comment);
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}

