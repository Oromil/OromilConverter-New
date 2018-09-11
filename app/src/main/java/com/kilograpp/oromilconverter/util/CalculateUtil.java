package com.kilograpp.oromilconverter.util;

import com.kilograpp.oromilconverter.data.network.entities.Valute;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Oromil on 20.07.2017.
 */

public class CalculateUtil {

    public static void calculate(String s, Valute fromValute, Valute toValute){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        toValute.setQuantity(Float.valueOf(df.format(Float.valueOf(s)/  toValute.getCourse()*fromValute.getCourse())));
    }
}
