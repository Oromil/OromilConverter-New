package com.kilograpp.oromilconverter.data.network.entities;

import io.realm.RealmModel;

public interface Valute extends RealmModel{
    String getName();
    Float getQuantity();
    Float setQuantity(Float value);
    Float getCourse();
}
