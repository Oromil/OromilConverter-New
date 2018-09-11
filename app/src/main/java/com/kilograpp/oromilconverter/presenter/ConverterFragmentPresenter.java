package com.kilograpp.oromilconverter.presenter;

import com.kilograpp.oromilconverter.data.network.ServerManager;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.view.fragments.ConverterMvpView;

import java.util.List;

import rx.Subscriber;

public class ConverterFragmentPresenter extends BasePresenter<ConverterMvpView> {

    private ServerManager mServerManager;

    public ConverterFragmentPresenter() {
        mServerManager = new ServerManager();
    }

    public void loadData() {

        mServerManager.testRequest(new Subscriber<List<Valute>>() {
            @Override
            public void onCompleted() {
                mMvpView.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                mMvpView.showProgress(false);
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Valute> valutes) {
                mMvpView.updateList(valutes);
            }
        });
    }
}
