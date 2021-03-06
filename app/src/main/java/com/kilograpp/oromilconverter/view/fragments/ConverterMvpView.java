package com.kilograpp.oromilconverter.view.fragments;

import com.kilograpp.oromilconverter.adapters.MyRecyclerViewAdapter;
import com.kilograpp.oromilconverter.data.network.entities.Currency;
import com.kilograpp.oromilconverter.view.MvpView;

import java.util.List;

public interface ConverterMvpView extends MvpView {
    void updateList(List<Currency> data);

    void showKeyboard(boolean show);

    void setRecyclerViewAdapter(MyRecyclerViewAdapter adapter);
}
