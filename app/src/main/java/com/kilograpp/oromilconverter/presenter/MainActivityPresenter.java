package com.kilograpp.oromilconverter.presenter;

import android.app.Activity;
import android.util.Log;

import com.kilograpp.oromilconverter.data.network.ServerManager;
import com.kilograpp.oromilconverter.data.network.Courses;
import com.kilograpp.oromilconverter.data.realm.Valute;
import com.kilograpp.oromilconverter.view.activity.MainActivity;
import com.kilograpp.oromilconverter.view.fragments.ConverterFragment;

import java.util.ArrayList;

import io.realm.Realm;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observables.AsyncOnSubscribe;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * Created by Oromil on 13.06.2017.
 */

public class MainActivityPresenter extends BasePresenter<MainMvpView> {

    private ServerManager mServerManager;
    private Realm mRealm;
    public MainActivityPresenter(){
        mServerManager = new ServerManager();
    }

    public void loadData(){
        Log.d("Test","loadData");
        updateData().subscribe(o -> {},
                throwable -> loadDataFromDataBase()
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.newThread())
                        .subscribe(valutes -> getmMvpView().getConverterFragment().updateList(valutes)));
    }

    public Observable updateData(){
        Log.d("Test","updateData");
        return Observable.create(subscriber -> mServerManager.requestData(new Subscriber<Courses>() {
            @Override
            public void onCompleted() {
                subscriber.onNext(null);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("RX updateData Error", e+"");
                subscriber.onError(e);
            }

            @Override
            public void onNext(Courses o) {
                ArrayList<Valute>valutes = o.getRates().getValutesList();

                ConverterFragment converterFragment = getmMvpView().getConverterFragment();
                        converterFragment.updateList(valutes);

                saveDataInDataBase(valutes)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.newThread())
                        .subscribe(o1 -> {},
                                throwable -> Log.d("DataSaving Error", throwable+""));
            }
        }));
    }

    private Observable saveDataInDataBase(ArrayList valutes){
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(mRealm1 -> mRealm1.insertOrUpdate(valutes));
        mRealm = null;
        return Observable.just("");
    }

    private Observable<ArrayList<Valute>> loadDataFromDataBase(){
        return Observable.create(subscriber -> {
            ArrayList<Valute> valutes = new ArrayList<>();
            mRealm = Realm.getDefaultInstance();
            mRealm.executeTransaction(realm -> valutes.addAll(realm.where(Valute.class).findAll()));
            mRealm = null;
            subscriber.onNext(valutes);
        });
    }
}
