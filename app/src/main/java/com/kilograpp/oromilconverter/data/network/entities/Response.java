package com.kilograpp.oromilconverter.data.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Response extends RealmObject {

    @SerializedName("Date")
    public String date;
    @SerializedName("PreviousDate")
    public String pDate;
    @SerializedName("PreviousURL")
    public String pUrl;
    @SerializedName("Timestamp")
    public String timestamp;

    @SerializedName("Valute")
    public AllCurrency allCurrency;

    private RealmList<Currency> currencies = new RealmList<>();

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
