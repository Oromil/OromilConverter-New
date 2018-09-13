package com.kilograpp.oromilconverter.view.activity;

import com.kilograpp.oromilconverter.data.network.entities.Currency;
import com.kilograpp.oromilconverter.view.MvpView;

import java.util.List;

/**
 * Created by Oromil on 19.07.2017.
 */

public interface MainMvpView extends MvpView {
    void showProgress(boolean show);
    void updateData(List<Currency>data);
}
