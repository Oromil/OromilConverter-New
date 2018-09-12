package com.kilograpp.oromilconverter.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.adapters.MyRecyclerViewAdapter;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.presenter.ConverterFragmentPresenter;

import java.util.List;
import java.util.Objects;

/**
 * Created by Oromil on 19.07.2017.
 */

public class ConverterFragment extends Fragment implements ConverterMvpView {

    //    private MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ConverterFragmentPresenter presenter;
    private boolean isKeyboardShowing = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("Test", "onCreateView");
        View v = inflater.inflate(R.layout.fragment_converter, null);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Test", "onViewCreated");


//        adapter = new MyRecyclerViewAdapter();

        recyclerView = view.findViewById(R.id.rvConverterFragmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                showKeyboard(false, null);

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        presenter = new ConverterFragmentPresenter();
        presenter.attachView(this);
//        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateList(List<Valute> valutes) {
        Log.d("Test", "updateList");
        ((MyRecyclerViewAdapter) recyclerView.getAdapter()).updateData(valutes);
    }

    @Override
    public void showKeyboard(boolean show, View view) {
        InputMethodManager inputManager = (InputMethodManager) Objects.requireNonNull(getActivity())
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if ((show && !isKeyboardShowing || !show && isKeyboardShowing) && inputManager != null) {
            inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            isKeyboardShowing = show;
        }
    }

    @Override
    public void setRecyclerViewAdapter(MyRecyclerViewAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
