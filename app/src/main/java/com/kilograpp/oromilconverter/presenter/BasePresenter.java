package com.kilograpp.oromilconverter.presenter;

import com.kilograpp.oromilconverter.view.MvpView;

/**
 * Created by Oromil on 19.07.2017.
 */

public class BasePresenter<T extends MvpView> implements Presenter {

    T mMvpView;

    @Override
    public void attachView(MvpView mvpView) {
        this.mMvpView = (T) mvpView;
        onViewAttached();
    }

    @Override
    public void detachView() {
        this.mMvpView = null;
    }

    public void onViewAttached() {

    }

    public T getmMvpView() {
        return mMvpView;
    }

    public boolean checkViewAttached() {
        return mMvpView != null;
    }
}
