package com.kilograpp.oromilconverter.util;

import com.kilograpp.oromilconverter.data.network.entities.Currency;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Oromil on 20.07.2017.
 */

public class CalculateUtil {

    public static void calculate(String s, Currency fromCurrency, Currency toCurrency){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", dfs);
        df.setRoundingMode(RoundingMode.CEILING);
        toCurrency.setQuantity(Float.valueOf(df.format(Float.valueOf(s)/  toCurrency.getCourse()* fromCurrency.getCourse())));
    }
}
