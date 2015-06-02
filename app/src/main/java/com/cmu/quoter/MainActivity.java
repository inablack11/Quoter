package com.cmu.quoter;

/**
 * Created by inablack11 on 5/31/2015.
 */

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    private QuotesDataSource datasource;
    private QuotesListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new QuotesDataSource(this);
        datasource.open();

        List<Quote> values = datasource.getAllQuotes();

        adapter.addAll(values);
        initListViewAdapter();

    }

    public void removeQuoteOnClickHandler(View v) {
        Quote itemToRemove = (Quote) v.getTag();
        adapter.remove(itemToRemove);
        removeQuote(itemToRemove);
    }

    private void initListViewAdapter() {
        adapter = new QuotesListAdapter(this, R.layout.list_item, new ArrayList<Quote>());
        ListView quotesListView = (ListView) findViewById(R.id.quotes_list_view);
        quotesListView.setAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add:
                onAdd(adapter);
                break;

        }
        adapter.notifyDataSetChanged();
    }

    private void removeQuote(Quote itemToRemove) {

        datasource.deleteQuote(itemToRemove);
        adapter.remove(itemToRemove);

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

