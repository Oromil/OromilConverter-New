package com.kilograpp.oromilconverter.data.network.entities;

import com.google.gson.annotations.SerializedName;

public class ValuteImpl implements Valute {

    @SerializedName("ID")
    String id;

    @SerializedName("NumCode")
    String code;

    @SerializedName("CharCode")
    String charCode;
    @SerializedName("Nominal")
    Float nom;
    @SerializedName("Name")
    String name;
    @SerializedName("Value")
    Float value;
    @SerializedName("Previous")
    Float prev;

    private Float quantity = 0f;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Float getQuantity() {
        return quantity;
    }

    @Override
    public Float setQuantity(Float value) {
        return quantity = value;
    }

    @Override
    public Float getCourse() {
        return value/nom;
    }

    public static Builder builder(){
        return new ValuteImpl(). new Builder();
    }

    public class Builder{

        public Builder(){}

        public Builder id(String id){
            ValuteImpl.this.id = id;
            return this;
        }

        public Builder code(String code){
            ValuteImpl.this.code = code;
            return this;
        }
        public Builder charCode(String charCode){
            ValuteImpl.this.charCode = charCode;
            return this;
        }
        public Builder nom(Float nom){
            ValuteImpl.this.nom = nom;
            return this;
        }
        public Builder name(String name){
            ValuteImpl.this.name = name;
            return this;
        }
        public Builder value(Float value){
            ValuteImpl.this.value = value;
            return this;
        }
        public Builder prev(Float prev){
            ValuteImpl.this.prev = prev;
            return this;
        }

        public ValuteImpl build(){
            return ValuteImpl.this;
        }
    }
}
