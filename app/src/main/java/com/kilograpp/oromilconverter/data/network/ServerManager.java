package com.kilograpp.oromilconverter.data.network;

import com.kilograpp.oromilconverter.OromilConverterApplication;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.data.network.entities.ValuteImpl;
import com.kilograpp.oromilconverter.util.ValuteResponseMapper;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Oromil on 13.06.2017.
 */

public class ServerManager {

    RequestCourses request;

    public ServerManager() {
        request = OromilConverterApplication.getRequest();
    }

    public void testRequest(Subscriber<List<Valute>> sub) {
        request.requestCourses()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(responseTest -> {
                    ValuteResponseMapper.map(responseTest).getValutes().add(ValuteImpl.builder()
                            .charCode("RUB").code("").id("").name("Рубль").value(1f).prev(1f).nom(1f)
                            .build());
                    return ValuteResponseMapper.map(responseTest).getValutes();
                })
                .subscribe(sub);

    }
}
