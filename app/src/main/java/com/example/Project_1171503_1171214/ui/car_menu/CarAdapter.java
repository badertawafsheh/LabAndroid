package com.example.Project_1171503_1171214.ui.car_menu;

import android.app.Dialog;
import android.content.Context;

import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.example.Project_1171503_1171214.Car;
import com.example.Project_1171503_1171214.DataBaseHelper;
import com.example.Project_1171503_1171214.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.Project_1171503_1171214.ui.car_menu.car_menuFragment.view;

class CarAdapter extends RecyclerView.Adapter<CarAdapter.ListViewHolder> {

    private List<Car> cars;
    Context context;

    public CarAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("My Shared Preference",
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("email", "");
        LayoutInflater inflater = LayoutInflater.from((viewGroup.getContext()));
        View view = inflater.inflate(R.layout.list_item_car_type, viewGroup, false);
        Dialog dialog = new Dialog(viewGroup.getContext());
        dialog.setContentView(R.layout.dialog_item);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        listViewHolder.item_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView year = dialog.findViewById(R.id.dialog_yearView);
                TextView make = dialog.findViewById(R.id.dialog_makeView);
                TextView distance = dialog.findViewById(R.id.dialog_disView);
                TextView model = dialog.findViewById(R.id.dialog_modelView);
                TextView price = dialog.findViewById(R.id.dialog_priceView);
                price.setText(String.valueOf(cars.get(listViewHolder.getAdapterPosition()).getPrice()));
                year.setText(String.valueOf(cars.get(listViewHolder.getAdapterPosition()).getYear()));
                model.setText(String.valueOf(cars.get(listViewHolder.getAdapterPosition()).getModel()));
                distance.setText(String.valueOf(cars.get(listViewHolder.getAdapterPosition()).getDistance()));
                make.setText(String.valueOf(cars.get(listViewHolder.getAdapterPosition()).getMake()));
System.out.println(listViewHolder.getAdapterPosition());
                final ImageButton FavButton = dialog.findViewById(R.id.favbtn);
                final ImageButton reserveButton = dialog.findViewById(R.id.cortbtn);
                reserveButton.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        dataBaseHelper.insertReserve(name, listViewHolder.getAdapterPosition(), dtf.format(now));

                    }
                });
                FavButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                        dataBaseHelper.insertFavorite(name, listViewHolder.getAdapterPosition());
                        FavButton.setImageResource(R.drawable.add1_30px);


                    }
                });

                dialog.show();
            }
        });


        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int i) {
        final int carid = i;
        SharedPreferences sharedPreferences = context.getSharedPreferences("My Shared Preference",
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("email", "");
        listViewHolder.textView.setText(cars.get(i).getMake());
      listViewHolder.textView1.setText(cars.get(i).getModel());




    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public TextView textView1;


        public LinearLayout item_layout;

        public ListViewHolder(View itemView) {
            super(itemView);
            item_layout = (LinearLayout) itemView.findViewById(R.id.item);
            textView = (TextView) itemView.findViewById(R.id.itemtitle);
            textView1 = (TextView) itemView.findViewById(R.id.itemtitle2);

        }

        @Override
        public void onClick(View v) {
        }
    }
}
