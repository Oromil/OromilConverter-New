package com.kilograpp.oromilconverter.presenter;

import com.kilograpp.oromilconverter.data.DataManager;
import com.kilograpp.oromilconverter.data.network.entities.Currency;
import com.kilograpp.oromilconverter.view.activity.MainMvpView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.Subscriber;

/**
 * Created by Oromil on 13.06.2017.
 */

public class MainActivityPresenter extends BasePresenter<MainMvpView> {

    private DataManager dataManager;

    public MainActivityPresenter() {
        dataManager = new DataManager();
    }

    public void loadData() {
        mMvpView.showProgress(true);
        dataManager.testRequest(new Subscriber<List<Currency>>() {
            @Override
            public void onCompleted() {
                mMvpView.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                mMvpView.showProgress(false);
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Currency> currencies) {
                mMvpView.updateData(currencies);
            }
        });
    }

    public void saveSelectedCurrencies(Integer[] selectedIndexes) {

        Set<String> data = new HashSet<>();

        for (Integer index : selectedIndexes) {
            data.add(getCurrenciesNames().get(index));
        }

        dataManager.saveSelectedCurrencies(data);
    }

    private List<String> getSelectedCurrencies() {
        if (dataManager.getSelectedCurrencies() != null)
            return new ArrayList<>(dataManager.getSelectedCurrencies());
        else return new ArrayList<>();
    }

    public List<String> getCurrenciesNames() {
        List<String> names = new ArrayList<>();

        for (Currency currency : dataManager.getDataFromDB()) {
            names.add(currency.getName());
        }

        return names;
    }

    public Integer[] getSelectedCurrenciesIndexes() {
        List<String> items = getCurrenciesNames();
        List<Integer> selectedIndexes = new ArrayList<>();

        for (String s : getSelectedCurrencies()) {
            selectedIndexes.add(items.indexOf(s));
        }

        if (selectedIndexes.size() == 0) {
            for (int i = 0; i < items.size(); i++)
                selectedIndexes.add(i);
        }

        return selectedIndexes.toArray(new Integer[selectedIndexes.size()]);
    }
}
