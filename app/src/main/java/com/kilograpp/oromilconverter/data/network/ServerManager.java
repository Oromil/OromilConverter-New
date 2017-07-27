package com.kilograpp.oromilconverter.data.network;

import com.kilograpp.oromilconverter.OromilConverterApplication;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Oromil on 13.06.2017.
 */

public class ServerManager {

    RequestCourses request;

    public ServerManager(){
        request = OromilConverterApplication.getRequest();
    }


    public void requestData(Subscriber<Courses> newSubscriber){
        request.requestCourses()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newSubscriber);
    }
}
