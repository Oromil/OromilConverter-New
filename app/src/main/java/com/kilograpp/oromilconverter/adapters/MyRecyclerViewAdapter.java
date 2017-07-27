package com.kilograpp.oromilconverter.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kilograpp.oromilconverter.R;
import com.kilograpp.oromilconverter.data.realm.Valute;
import com.kilograpp.oromilconverter.util.CalculateUtil;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Created by Oromil on 13.06.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    @Getter
    private ArrayList<Valute> mValutesList;

    private int mActiveItem;

    public MyRecyclerViewAdapter(){
        mValutesList = new ArrayList<>();
        mActiveItem = 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_converter_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.valuteName.setText(mValutesList.get(position).getName());
        holder.valuteQuantity.setText(String.valueOf(mValutesList.get(position).getQuantity()));
        holder.itemView.setOnClickListener(v -> mActiveItem = position);
    }

    @Override
    public int getItemCount() {
        return mValutesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView valuteName;
        TextView valuteQuantity;
        public MyViewHolder(View itemView) {
            super(itemView);
            valuteName = (TextView) itemView.findViewById(R.id.tvValuteName);
            valuteQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
        }
    }

    public void updateData(ArrayList<Valute>valutes){
        mValutesList.clear();
        mValutesList.addAll(valutes);
        notifyDataSetChanged();
    }

    public void changeItemsQuantity(String quantity){
        for (Valute valute:mValutesList) {
            if (mValutesList.indexOf(valute)==mActiveItem)
                valute.setQuantity(Float.valueOf(quantity));
            else CalculateUtil.calculate(quantity, mValutesList.get(mActiveItem), valute);
        }
        notifyDataSetChanged();
    }
}
