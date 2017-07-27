package com.kilograpp.oromilconverter;

import android.app.Application;
import android.util.Log;

import com.kilograpp.oromilconverter.data.network.RequestCourses;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.log.AndroidLogger;
import io.realm.log.RealmLog;
import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oromil on 13.06.2017.
 */

public class OromilConverterApplication extends Application {

    @Getter
    static RequestCourses request;
    @Getter
    static OromilConverterApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        createRetrofit();
        createRealm();
    }

    private void createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.fixer.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

        request = retrofit.create(RequestCourses.class);}

    private void createRealm(){
        Realm.init(getApplicationContext());
        RealmLog.add(new AndroidLogger(Log.WARN));
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("OromilConverterDb.realm")
                .migration((realm, oldVersion, newVersion) -> realm.deleteAll())
                .build();
        Realm.compactRealm(configuration);
        Realm.removeDefaultConfiguration();
        Realm.setDefaultConfiguration(configuration);
    }
}
