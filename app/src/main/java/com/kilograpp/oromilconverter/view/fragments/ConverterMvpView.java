package com.kilograpp.oromilconverter.view.fragments;

import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.view.MvpView;

import java.util.List;

public interface ConverterMvpView extends MvpView {
    void updateList(List<Valute> data);

    void showProgress(boolean show);
}
