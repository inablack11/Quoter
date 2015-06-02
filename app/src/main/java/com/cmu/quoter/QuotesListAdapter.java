package com.cmu.quoter;

/**
 * Created by inablack11 on 6/2/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class QuotesListAdapter extends ArrayAdapter<Quote> {

    protected static final String LOG_TAG = QuotesListAdapter.class.getSimpleName();

    private List<Quote> items;
    private int layoutResourceId;
    private Context context;

    public QuotesListAdapter(Context context, int layoutResourceId, List<Quote> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        QuoteHolder holder = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new QuoteHolder();
        holder.quote = items.get(position);
        holder.removePaymentButton = (ImageButton) row.findViewById(R.id.remove_quote);
        holder.removePaymentButton.setTag(holder.quote);

        holder.name = (TextView) row.findViewById(R.id.quote_text);
        setNameTextChangeListener(holder);
//        holder.value = (TextView)row.findViewById(R.id.atomPay_value);
//        setValueTextListeners(holder);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(QuoteHolder holder) {
        holder.name.setText(holder.quote.getQuote());
    }

    private void setNameTextChangeListener(final QuoteHolder holder) {
        holder.name.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                holder.quote.setQuote(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static class QuoteHolder {
        Quote quote;
        TextView name;
        ImageButton removePaymentButton;
    }


}
