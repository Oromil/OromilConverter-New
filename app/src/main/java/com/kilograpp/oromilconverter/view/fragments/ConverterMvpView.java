package com.kilograpp.oromilconverter.view.fragments;

import android.view.View;

import com.kilograpp.oromilconverter.adapters.MyRecyclerViewAdapter;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.view.MvpView;

import java.util.List;

public interface ConverterMvpView extends MvpView {
    void updateList(List<Valute> data);

    void showKeyboard(boolean show, View view);

    void setRecyclerViewAdapter(MyRecyclerViewAdapter adapter);
}
