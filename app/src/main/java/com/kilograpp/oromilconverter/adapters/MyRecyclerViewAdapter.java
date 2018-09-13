package com.kilograpp.oromilconverter.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.data.network.entities.Valute;
import com.kilograpp.oromilconverter.util.CalculateUtil;
import com.kilograpp.oromilconverter.view.custom.CustomEditText;
import com.kilograpp.oromilconverter.view.custom.CustomTextWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oromil on 13.06.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Valute> mValutesList;
    private View.OnClickListener onClickListener;

    // TODO: 12.09.2018 костыль
    private View parent;

    private int activeItem;

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
        activeItem = -1;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_converter_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Valute valute = mValutesList.get(position);
        holder.valuteName.setText(valute.getName());

        if (activeItem == position) {
            if (mValutesList.get(position).getQuantity() == 0f)
                holder.valuteQuantity.getText().clear();
            else
                holder.valuteQuantity.setText(String.valueOf(valute.getQuantity()));

            holder.valuteQuantity.requestFocus();
        } else {
            holder.valuteQuantity.setText(String.valueOf(valute.getQuantity()));
        }

        holder.itemView.setOnClickListener(v -> {
            if (activeItem != position) {
                notifyItemChanged(activeItem);
                activeItem = position;
                holder.valuteQuantity.getText().clear();
                holder.valuteQuantity.requestFocus();
            }
            onClickListener.onClick(v);
        });
    }


    @Override
    public int getItemCount() {
        return mValutesList.size();
    }


    public void updateData(List<Valute> valutes) {
        if (parent != null)
            parent.requestFocus();
        mValutesList.clear();
        mValutesList.addAll(valutes);
        activeItem = -1;
        notifyDataSetChanged();
    }

    private void changeItemsQuantity(String quantity) {
        for (Valute valute : mValutesList) {
            if (mValutesList.indexOf(valute) == activeItem)
                valute.setQuantity(Float.valueOf(quantity));
            else {
                CalculateUtil.calculate(quantity, mValutesList.get(activeItem), valute);
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
    }
}
