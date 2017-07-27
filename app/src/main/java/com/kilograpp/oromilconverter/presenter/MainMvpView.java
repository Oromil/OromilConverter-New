package com.kilograpp.oromilconverter.presenter;

import android.support.v4.app.Fragment;

import com.kilograpp.oromilconverter.view.fragments.ConverterFragment;

/**
 * Created by Oromil on 19.07.2017.
 */

public interface MainMvpView extends MvpView {
    void showProgress(boolean show);
    Fragment getActiveFragment();
    ConverterFragment getConverterFragment();
}
