package com.kilograpp.oromilconverter.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.adapters.MyRecyclerViewAdapter;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.presenter.ConverterFragmentPresenter;

import java.util.List;

/**
 * Created by Oromil on 19.07.2017.
 */

public class ConverterFragment extends Fragment implements ConverterMvpView{

    private MyRecyclerViewAdapter mAdapter;

    private ConverterFragmentPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("Test", "onCreateView");
        View v = inflater.inflate(R.layout.fragment_converter, null);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Test", "onViewCreated");

        mPresenter = new ConverterFragmentPresenter();
        mPresenter.attachView(this);

        mAdapter = new MyRecyclerViewAdapter();

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rvConverterFragmentList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.loadData();
    }

    @Override
    public void updateList(List<Valute> valutes) {
        Log.d("Test", "updateList");
        mAdapter.updateData(valutes);
    }

    @Override
    public void showProgress(boolean show) {

    }
}
