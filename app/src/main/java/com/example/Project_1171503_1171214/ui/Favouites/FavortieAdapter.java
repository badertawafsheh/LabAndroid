package com.example.Project_1171503_1171214.ui.Favouites;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.Project_1171503_1171214.Car;
import com.example.Project_1171503_1171214.DataBaseHelper;
import com.example.Project_1171503_1171214.R;

import java.util.List;

class FavortieAdapter extends RecyclerView.Adapter<FavortieAdapter.ListViewHolder> {

    private List<Car> cars;
    Context context;
    DataBaseHelper dataBaseHelper;


    public FavortieAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_list_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int i) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("My Shared Preference",
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("email", "");
        final int carid = i;

        listViewHolder.textView.setText(cars.get(i).getMake() + "\n" + cars.get(i).getModel() + "\n" + cars.get(i).getPrice() + "\n" + cars.get(i).getYear());

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        ImageButton imageButton;

        public ListViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.car_name_in_fav_list_item);
            imageButton = (ImageButton) itemView.findViewById(R.id.reserve_button_in_fav_list);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}