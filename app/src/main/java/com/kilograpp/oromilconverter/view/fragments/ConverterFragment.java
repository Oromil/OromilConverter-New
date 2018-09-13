package com.kilograpp.oromilconverter.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.adapters.MyRecyclerViewAdapter;
import com.kilograpp.oromilconverter.data.network.entities.Currency;
import com.kilograpp.oromilconverter.presenter.ConverterFragmentPresenter;
import com.kilograpp.oromilconverter.view.activity.MainActivity;

import java.util.List;

/**
 * Created by Oromil on 19.07.2017.
 */

public class ConverterFragment extends Fragment implements ConverterMvpView {

    private RecyclerView recyclerView;
    private ConverterFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_converter, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvConverterFragmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                showKeyboard(false);
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        presenter = new ConverterFragmentPresenter();
        presenter.attachView(this);
    }

    @Override
    public void updateList(List<Currency> currencies) {
        ((MyRecyclerViewAdapter) recyclerView.getAdapter()).updateData(currencies);
    }

    @Override
    public void showKeyboard(boolean show) {
        ((MainActivity)getActivity()).setKeyboardShowing(show);
    }

    @Override
    public void setRecyclerViewAdapter(MyRecyclerViewAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
