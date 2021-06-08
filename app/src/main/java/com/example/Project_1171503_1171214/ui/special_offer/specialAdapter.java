package com.example.Project_1171503_1171214.ui.special_offer;

import androidx.annotation.RequiresApi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class specialAdapter extends RecyclerView.Adapter<specialAdapter.ListViewHolder> {


    private List<Car> cars;
    Context context;

    public specialAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.special_offer_list_item,viewGroup,false);
        return new ListViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int i) {
        final int carid = i;
        SharedPreferences sharedPreferences = context.getSharedPreferences("My Shared Preference",
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("email", "");

        if(cars.get(i).isOffers()==true) {
            String details = "";
            details += "Year: " + cars.get(i).getYear() + "\n";
            details += "Make: " + cars.get(i).getMake() + "\n";
            details += "Model: " + cars.get(i).getModel() + "\n";
            details += "Distance: " + cars.get(i).getDistance() + "\n";
            details += "Price: " + cars.get(i).getPrice() + "\n";
            details += "Offer: " + cars.get(i).isOffers() + "\n";

            System.out.println(details);
            listViewHolder.textView.setText(details);

            listViewHolder.reserveButtonOffers.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    dataBaseHelper.insertReserve(name, listViewHolder.getAdapterPosition(), dtf.format(now));

                }
            });
            listViewHolder.FavButtonOffers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                    dataBaseHelper.insertFavorite(name, listViewHolder.getAdapterPosition());

                    listViewHolder.FavButtonOffers.setImageResource(R.drawable.add1_30px);

                }
            });
        }

    }
    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        public ImageButton reserveButtonOffers;
        public ImageButton FavButtonOffers;
        public ListViewHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.special_offers_text);
            FavButtonOffers = (ImageButton) itemView.findViewById(R.id.fav_button_menuO);
            reserveButtonOffers = (ImageButton) itemView.findViewById(R.id.reserve_button_menuO);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
        }
    }
}