package com.kilograpp.oromilconverter.presenter;

import com.kilograpp.oromilconverter.view.MvpView;

/**
 * Created by Oromil on 19.07.2017.
 */

public interface Presenter <V extends MvpView>{
    void attachView(V mvpView);
    void detachView();
}
