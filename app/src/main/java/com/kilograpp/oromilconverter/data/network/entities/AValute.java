package com.kilograpp.oromilconverter.data.network.entities;

import io.realm.RealmModel;
import io.realm.RealmObject;

public abstract class AValute{
    public abstract String getName();
    public abstract Float getQuantity();
    public abstract Float setQuantity(Float value);
    public abstract Float getCourse();
}
