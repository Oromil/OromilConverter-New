package com.kilograpp.oromilconverter.data;

import android.util.Log;

import com.kilograpp.oromilconverter.OromilConverterApplication;
import com.kilograpp.oromilconverter.data.network.RequestCourses;
import com.kilograpp.oromilconverter.data.network.entities.Currency;
import com.kilograpp.oromilconverter.data.network.entities.Response;
import com.kilograpp.oromilconverter.util.CurrencyResponseMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Oromil on 13.06.2017.
 */

public class DataManager {

    private RequestCourses request;
    private Realm realm;
    private PreferencesHelper prefsHelper;


    public DataManager() {
        request = OromilConverterApplication.getRequest();
        prefsHelper = new PreferencesHelper();
    }

    public void testRequest(Subscriber<List<Currency>> sub) {
        request.requestCourses()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    CurrencyResponseMapper.map(response).getCurrencies().add(Currency.builder()
                            .charCode("RUB").code("").id("").name("Рубль").value(1f).prev(1f).nom(1f)
                            .build());
                    saveDataInDataBase(response);

                    ArrayList<String> selected = new ArrayList<>(getSelectedCurrencies());

                    if (selected.size() == 0)
                        return response.getCurrencies();

                    List<Currency> actualCurrencies = new ArrayList<>();
                    for (Currency currency : response.getCurrencies()) {
                        if (selected.contains(currency.getName())) {
                            actualCurrencies.add(currency);
                            Log.d("test", String.valueOf(response.getCurrencies().size()));
                        }
                    }
                    return actualCurrencies;
                })
                .doOnError(throwable -> {
                    throwable.printStackTrace();
                    sub.onNext(getDataFromDB());
                })
                .subscribe(sub);
    }

    public void saveDataInDataBase(Response data) {
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm -> realm.insertOrUpdate(data));
        realm.close();
    }

    public List<Currency> getDataFromDB() {
        List<Currency> currencies = new ArrayList<>();
        realm = Realm.getDefaultInstance();
        Response realmResponse = realm.where(Response.class).findFirst();
        if (realmResponse != null)
            currencies.addAll(realm.copyFromRealm(realmResponse).getCurrencies());
        realm.close();
        return currencies;
    }

    public void saveSelectedCurrencies(Set<String> data) {
        prefsHelper.saveSelectedCurrencies(data);
    }

    public Set<String> getSelectedCurrencies() {
        return prefsHelper.getSelectedCurrencies();
    }
}
