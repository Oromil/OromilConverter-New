package com.kilograpp.oromilconverter.data.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("Date")
    public String date;
    @SerializedName("PreviousDate")
    public String pDate;
    @SerializedName("PreviousURL")
    public String pUrl;
    @SerializedName("Timestamp")
    public String timestamp;

    @SerializedName("Valute")
    public AllValutes allValutes;

    private ArrayList<Valute> valutes = new ArrayList<>();

    public List<Valute> getValutes() {
        return valutes;
    }
}
