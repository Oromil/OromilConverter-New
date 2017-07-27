package com.kilograpp.oromilconverter.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.adapters.MyRecyclerViewAdapter;
import com.kilograpp.oromilconverter.data.realm.Valute;
import com.kilograpp.oromilconverter.view.activity.MainActivity;
import com.kilograpp.oromilconverter.view.fragments.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Oromil on 19.07.2017.
 */

public class ConverterFragment extends Fragment {

    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;


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

        mAdapter = new MyRecyclerViewAdapter();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvConverterFragmentList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        CustomTextWatcher textWatcher = new CustomTextWatcher((EditText) view.findViewById(R.id.etQuantity));

        ((MainActivity) getActivity()).getMPresenter().loadData();
    }

    public void updateList(ArrayList<Valute> valutes) {
        Log.d("Test", "updateList");
        mAdapter.updateData(valutes);
    }

    private class CustomTextWatcher implements TextWatcher {

        private EditText mInputQuantity;

        public CustomTextWatcher(EditText inputQuantity){
            this.mInputQuantity = inputQuantity;
            this.mInputQuantity.addTextChangedListener(this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().equals(".")){
                mInputQuantity.setText("0.");
                mInputQuantity.setSelection(count+1);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("text", s+"");
            if (!s.toString().equals(""))
                mAdapter.changeItemsQuantity(mInputQuantity.getText().toString());
            else mAdapter.changeItemsQuantity("0");
        }
    }
}
