package com.kilograpp.oromilconverter;

import android.app.Application;

import com.kilograpp.oromilconverter.data.network.RequestCourses;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oromil on 13.06.2017.
 */

public class OromilConverterApplication extends Application {

    static RequestCourses request;
    static OromilConverterApplication instance;

    public static RequestCourses getRequest() {
        return request;
    }

    public static OromilConverterApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        createRetrofit();
        createRealm();
    }

    private void createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

        request = retrofit.create(RequestCourses.class);}

    private void createRealm(){
        Realm.init(getApplicationContext());
//        RealmLog.add(new AndroidLogger(Log.WARN));
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("OromilConverterDb.realm")
                .migration((realm, oldVersion, newVersion) -> realm.deleteAll())
                .build();
        Realm.compactRealm(configuration);
        Realm.removeDefaultConfiguration();
        Realm.setDefaultConfiguration(configuration);
    }


}
