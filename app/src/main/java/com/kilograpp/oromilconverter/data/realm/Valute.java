package com.kilograpp.oromilconverter.data.realm;

import android.icu.util.Calendar;
import android.view.View;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Oromil on 19.07.2017.
 */

public class Valute extends RealmObject{

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Float course;
    @Getter
    @Setter
    private Float quantity;

    public Valute(){}

    public Valute(String name, Float course){
        this.name = name;
        this.course = course;
        this.quantity = 0f;
    }
}
