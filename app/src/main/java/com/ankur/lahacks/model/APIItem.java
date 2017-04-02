package com.ankur.lahacks.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ankur on 02/04/17.
 */

public class APIItem {

    @SerializedName("bucket_results")
    List<List<String>> items;

    @SerializedName("quotes")
    List<String> quotes;

    public List<List<String>> getItems() {
        return items;
    }

    public List<String> getQuotes() {
        return quotes;
    }
}
