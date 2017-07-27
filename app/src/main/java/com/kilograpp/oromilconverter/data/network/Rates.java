package com.kilograpp.oromilconverter.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kilograpp.oromilconverter.data.realm.Valute;

import java.util.ArrayList;

import lombok.Getter;

public class Rates {

    private Rates(){
        rUB = 1f;
        valutesList = new ArrayList<>();
    }

    private Float rUB;

    @SerializedName("USD")
    @Expose
    private Float uSD;
    @SerializedName("GBP")
    @Expose
    private Float gBP;
    @SerializedName("EUR")
    @Expose
    private Float eUR;
    @SerializedName("AUD")
    @Expose
    private Float aUD;

    private ArrayList<Valute> valutesList;

    public ArrayList<Valute> getValutesList(){
        addValutesToList();
        return valutesList;
    }

    private void addValutesToList(){
        valutesList.add(new Valute("RUB", rUB));
        valutesList.add(new Valute("USD", uSD));
        valutesList.add(new Valute("GBP", gBP));
        valutesList.add(new Valute("EUR", eUR));
        valutesList.add(new Valute("AUD", aUD));
    }

}
