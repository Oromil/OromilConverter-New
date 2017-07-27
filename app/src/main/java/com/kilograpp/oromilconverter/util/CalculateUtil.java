package com.kilograpp.oromilconverter.util;

import com.kilograpp.oromilconverter.data.realm.Valute;

/**
 * Created by Oromil on 20.07.2017.
 */

public class CalculateUtil {

    public static void calculate(String s, Valute fromValute, Valute toValute){
        toValute.setQuantity(Float.valueOf(s)/fromValute.getCourse()*toValute.getCourse());
    }
}
