package com.kilograpp.oromilconverter.presenter;

/**
 * Created by Oromil on 19.07.2017.
 */

public interface Presenter <V extends MvpView>{
    void attachView(V mvpView);
    void detachView();
}
