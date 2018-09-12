package com.kilograpp.oromilconverter.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.view.custom.CustomEditText;
import com.kilograpp.oromilconverter.view.custom.CustomTextWatcher;
import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.data.network.entities.AValute;
import com.kilograpp.oromilconverter.util.CalculateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oromil on 13.06.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Valute> mValutesList;
    private View.OnClickListener onClickListener;

    private int mActiveItem;

    private CustomTextWatcher mWatcher = new CustomTextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().equals(".") || s.toString().equals(""))
                changeItemsQuantity("0.");
            else
                changeItemsQuantity(s.toString());
        }
    };

    public MyRecyclerViewAdapter(View.OnClickListener listener) {
        onClickListener = listener;
        mValutesList = new ArrayList<>();
        mActiveItem = -1;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_converter_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fillItemView(mValutesList.get(position).getName(),
                String.valueOf(mValutesList.get(position).getQuantity()));

        holder.itemView.setOnClickListener(v -> {
            if (mActiveItem != position) {
                notifyItemChanged(mActiveItem);
                mActiveItem = position;
                holder.focusEditText();
            }
            onClickListener.onClick(v);
        });
    }


    @Override
    public int getItemCount() {
        return mValutesList.size();
    }


    public void updateData(List<Valute> valutes) {
        mValutesList.clear();
        mValutesList.addAll(valutes);
        notifyDataSetChanged();
    }

    private void changeItemsQuantity(String quantity) {
        for (Valute valute : mValutesList) {
            if (mValutesList.indexOf(valute) == mActiveItem)
                valute.setQuantity(Float.valueOf(quantity));
            else {
                CalculateUtil.calculate(quantity, mValutesList.get(mActiveItem), valute);
                notifyItemChanged(mValutesList.indexOf(valute));
            }
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView valuteName;
        CustomEditText valuteQuantity;

        MyViewHolder(View itemView) {
            super(itemView);
            valuteName = itemView.findViewById(R.id.tvValuteName);
            valuteQuantity = itemView.findViewById(R.id.tvQuantity);
            valuteQuantity.addTextChangedListener(mWatcher);
        }

        void fillItemView(String name, String quantity) {
            valuteName.setText(name);
            valuteQuantity.setText(quantity);
        }

        void focusEditText() {
            valuteQuantity.setText("");
            valuteQuantity.requestFocus();
        }
    }
}
