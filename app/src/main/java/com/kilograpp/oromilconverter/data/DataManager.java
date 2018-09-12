package com.kilograpp.oromilconverter.data;

import android.util.Log;

import com.kilograpp.oromilconverter.OromilConverterApplication;
import com.kilograpp.oromilconverter.data.network.RequestCourses;
import com.kilograpp.oromilconverter.data.network.entities.AValute;
import com.kilograpp.oromilconverter.data.network.entities.Response;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.util.ValuteResponseMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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

    public void testRequest(Subscriber<List<Valute>> sub) {
        request.requestCourses()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    ValuteResponseMapper.map(response).getValutes().add(Valute.builder()
                            .charCode("RUB").code("").id("").name("Рубль").value(1f).prev(1f).nom(1f)
                            .build());
                    saveDataInDataBase(response);

                    ArrayList<String>selected = new ArrayList<>(getSelectedValutes());
                    List<Valute>actualValutes = new ArrayList<>();
                    for (Valute valute:response.getValutes()) {
                        if (selected.contains(valute.getName())) {
                            actualValutes.add(valute);
                            Log.d("test", String.valueOf(response.getValutes().size()));
                        }
                    }
                    return actualValutes;
                })
                .doOnError(throwable ->{
                        throwable.printStackTrace();
                        sub.onNext(getDataFromDB());})
                .subscribe(sub);
    }

    public void saveDataInDataBase(Response data) {
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm -> realm.insertOrUpdate(data));
        realm.close();
    }

    public List<Valute> getDataFromDB() {
        List<Valute> valutes = new ArrayList<>();
        realm = Realm.getDefaultInstance();
        Response realmResponse = realm.where(Response.class).findFirst();
        if (realmResponse != null)
            valutes.addAll(realm.copyFromRealm(realmResponse).getValutes());
        realm.close();
        return valutes;
    }

    public void saveSelectedValutes(Set<String> data) {
        prefsHelper.saveSelectedValutes(data);
    }

    public Set<String> getSelectedValutes() {
        return prefsHelper.getSelectedValutes();
    }
}
