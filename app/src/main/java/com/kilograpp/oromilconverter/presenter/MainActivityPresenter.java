package com.kilograpp.oromilconverter.presenter;

import com.kilograpp.oromilconverter.data.DataManager;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
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

        dataManager.testRequest(new Subscriber<List<Valute>>() {
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
            public void onNext(List<Valute> valutes) {
                mMvpView.updateData(valutes);
            }
        });
    }

    public void saveSelectedValutes(Integer[] selectedIndexes) {

        Set<String> data = new HashSet<>();

        for (Integer index : selectedIndexes) {
            data.add(getValutesNames().get(index));
        }

        dataManager.saveSelectedValutes(data);
    }

    private List<String> getSelectedValutes() {
        if (dataManager.getSelectedValutes() != null)
            return new ArrayList<>(dataManager.getSelectedValutes());
        else return new ArrayList<>();
    }

    public List<String> getValutesNames() {
        List<String> names = new ArrayList<>();

        for (Valute valute : dataManager.getDataFromDB()) {
            names.add(valute.getName());
        }

        return names;
    }

    public Integer[] getSelectedValutesIndexes() {
        List<String> items = getValutesNames();
        List<Integer> selectedIndexes = new ArrayList<>();

        for (String s : getSelectedValutes()) {
            selectedIndexes.add(items.indexOf(s));
        }

        if (selectedIndexes.size() == 0) {
            for (int i = 0; i < items.size(); i++)
                selectedIndexes.add(i);
        }

        return selectedIndexes.toArray(new Integer[selectedIndexes.size()]);
    }
}
