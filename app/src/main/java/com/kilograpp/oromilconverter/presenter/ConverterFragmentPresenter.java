package com.kilograpp.oromilconverter.presenter;

import com.kilograpp.oromilconverter.adapters.MyRecyclerViewAdapter;
import com.kilograpp.oromilconverter.data.DataManager;
import com.kilograpp.oromilconverter.view.fragments.ConverterMvpView;

public class ConverterFragmentPresenter extends BasePresenter<ConverterMvpView> {

    private DataManager mDataManager;

    public ConverterFragmentPresenter() {
        mDataManager = new DataManager();
    }

    @Override
    public void onViewAttached() {
        mMvpView.setRecyclerViewAdapter(new MyRecyclerViewAdapter(v -> mMvpView.showKeyboard(true)));
    }
}
