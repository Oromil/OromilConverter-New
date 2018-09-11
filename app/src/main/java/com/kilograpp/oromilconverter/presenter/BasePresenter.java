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
    }

    @Override
    public void detachView() {
        this.mMvpView = null;
    }

    public T getmMvpView(){
        return mMvpView;
    }

    public boolean checkViewAttached(){
        if (mMvpView == null)
            return false;
        else
            return true;
    }
}
