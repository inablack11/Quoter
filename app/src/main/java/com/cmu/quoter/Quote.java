package com.cmu.quoter;

/**
 * Created by inablack11 on 5/31/2015.
 */
public class Quote {
    private long id;
    private String quote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return quote;
    }
}
