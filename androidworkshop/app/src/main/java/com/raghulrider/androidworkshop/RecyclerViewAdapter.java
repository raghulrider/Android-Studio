package com.raghulrider.androidworkshop;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WeatherLogViewHolder> {
    List<WeatherLog> logs;


    public RecyclerViewAdapter(List<WeatherLog> logs) {
        this.logs = logs;
    }

    public static class WeatherLogViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView city;
        TextView description;
        TextView temperature;
         WeatherLogViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            city = itemView.findViewById(R.id.cityname);
            temperature = itemView.findViewById(R.id.temper);
            description = itemView.findViewById(R.id.description);

        }
    }

    @Override
    public WeatherLogViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout,parent, false);
        WeatherLogViewHolder wvh = new WeatherLogViewHolder(view);
        return wvh;
    }

    @Override
    public void onBindViewHolder(WeatherLogViewHolder holder, int position) {
        holder.city.setText(logs.get(position).city);
        holder.temperature.setText(logs.get(position).temperature);
        holder.description.setText(logs.get(position).description);
    }

    @Override
    public void onAttachedToRecyclerView( RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }


}
