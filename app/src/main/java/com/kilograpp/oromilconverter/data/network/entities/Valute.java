package com.kilograpp.oromilconverter.data.network.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Valute extends RealmObject {

    @SerializedName("ID")
    private String id;

    @SerializedName("NumCode")
    private String code;

    @SerializedName("CharCode")
    private String charCode;
    @SerializedName("Nominal")
    private Float nom;
    @SerializedName("Name")
    private String name;
    @SerializedName("Value")
    private Float value;
    @SerializedName("Previous")
    private Float prev;

    private Float quantity = 0f;

    public String getName() {
        return name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public Float setQuantity(Float value) {
        return quantity = value;
    }

    public Float getCourse() {
        return value / nom;
    }

    public static Builder builder() {
        return new Valute().new Builder();
    }

    public class Builder {

        Builder() {
        }

        public Builder id(String id) {
            Valute.this.id = id;
            return this;
        }

        public Builder code(String code) {
            Valute.this.code = code;
            return this;
        }

        public Builder charCode(String charCode) {
            Valute.this.charCode = charCode;
            return this;
        }

        public Builder nom(Float nom) {
            Valute.this.nom = nom;
            return this;
        }

        public Builder name(String name) {
            Valute.this.name = name;
            return this;
        }

        public Builder value(Float value) {
            Valute.this.value = value;
            return this;
        }

        public Builder prev(Float prev) {
            Valute.this.prev = prev;
            return this;
        }

        public Valute build() {
            return Valute.this;
        }
    }
}
