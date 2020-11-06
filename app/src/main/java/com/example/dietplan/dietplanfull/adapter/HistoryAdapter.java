package com.example.dietplan.dietplanfull.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dietplan.dietplanfull.R;
import com.example.dietplan.dietplanfull.model.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    ArrayList<History> data = new ArrayList<>();

    public HistoryAdapter(ArrayList<History> data){
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tipe, kkal, date;

        public ViewHolder(View view){
            super(view);
            tipe = (TextView) view.findViewById(R.id.tipeSnack);
            kkal = (TextView) view.findViewById(R.id.kkal);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_row,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        History h = data.get(position);
        holder.tipe.setText("Tipe Snack : "+h.getTypeSnack());
        holder.kkal.setText("Total Energy : "+h.getEnergy());
        holder.date.setText("Date : "+h.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
