package com.example.Project_1171503_1171214.ui.reservtion;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.Project_1171503_1171214.Car;
import com.example.Project_1171503_1171214.R;

import java.util.List;

class reservationAdapter extends RecyclerView.Adapter<reservationAdapter.ListViewHolder> {

    private List<Pair<Car, String>> cars;
    Context context;


    public reservationAdapter(Context context, List<Pair<Car, String>> cars) {
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.reservation_list_item, viewGroup, false);
        return new ListViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int i) {
        final int carid = i;
        listViewHolder.textView.setText(cars.get(i).first.getMake() + "\n" + cars.get(i).first.getModel() + "\n" + cars.get(i).first.getPrice() + "\n" + cars.get(i).second);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public ListViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.reserve_text_in_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}