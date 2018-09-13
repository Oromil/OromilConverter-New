package com.kilograpp.oromilconverter.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.data.network.entities.Currency;
import com.kilograpp.oromilconverter.util.CalculateUtil;
import com.kilograpp.oromilconverter.view.custom.CustomEditText;
import com.kilograpp.oromilconverter.view.custom.CustomTextWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oromil on 13.06.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Currency> currencyList;
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
        currencyList = new ArrayList<>();
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
        Currency currency = currencyList.get(position);
        holder.currencyName.setText(currency.getName());

        if (activeItem == position) {
            if (currencyList.get(position).getQuantity() == 0f)
                holder.currencyQuantity.getText().clear();
            else
                holder.currencyQuantity.setText(String.valueOf(currency.getQuantity()));

            holder.currencyQuantity.requestFocus();
        } else {
            holder.currencyQuantity.setText(String.valueOf(currency.getQuantity()));
        }

        holder.itemView.setOnClickListener(v -> {
            if (activeItem != position) {
                notifyItemChanged(activeItem);
                activeItem = position;
                holder.currencyQuantity.getText().clear();
                holder.currencyQuantity.requestFocus();
            }
            onClickListener.onClick(v);
        });
    }


    @Override
    public int getItemCount() {
        return currencyList.size();
    }


    public void updateData(List<Currency> currencies) {
        if (parent != null)
            parent.requestFocus();
        currencyList.clear();
        currencyList.addAll(currencies);
        activeItem = -1;
        notifyDataSetChanged();
    }

    private void changeItemsQuantity(String quantity) {
        for (Currency currency : currencyList) {
            if (currencyList.indexOf(currency) == activeItem)
                currency.setQuantity(Float.valueOf(quantity));
            else {
                CalculateUtil.calculate(quantity, currencyList.get(activeItem), currency);
                notifyItemChanged(currencyList.indexOf(currency));
            }
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView currencyName;
        CustomEditText currencyQuantity;

        MyViewHolder(View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.tvCurrencyName);
            currencyQuantity = itemView.findViewById(R.id.tvQuantity);
            currencyQuantity.addTextChangedListener(mWatcher);
        }
    }
}
