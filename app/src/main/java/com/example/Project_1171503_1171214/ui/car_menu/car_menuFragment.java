package com.example.Project_1171503_1171214.ui.car_menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.Project_1171503_1171214.Car;
import com.example.Project_1171503_1171214.DataBaseHelper;
import com.example.Project_1171503_1171214.R;
import com.example.Project_1171503_1171214.carJsonParser;

import java.util.ArrayList;
import java.util.List;

public class car_menuFragment extends Fragment {
    DataBaseHelper dataBaseHelper;
    static View view;

    public static car_menuFragment newInstance() {
        return new car_menuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.car_menu_fragment, container, false);
        Button filterBtn = (Button) view.findViewById(R.id.filterbtn);

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });
        fillcars(carJsonParser.cars, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    public void filter() {


        EditText model = view.findViewById(R.id.model);

        List<Car> filteredList = new ArrayList<>();

        if (model.getText() != null && !model.getText().toString().equals("")) {
            for (int i = 0; i < carJsonParser.cars.size(); i++) {
                if (carJsonParser.cars.get(i).getModel().equals(model.getText().toString())) {
                    filteredList.add(carJsonParser.cars.get(i));
                }
            }
        } else {
            filteredList = carJsonParser.cars;
        }

        EditText price = view.findViewById(R.id.price);
        List<Car> filteredList1 = new ArrayList<>();

        if (price.getText() != null && !price.getText().toString().equals("")) {
            for (int i = 0; i < carJsonParser.cars.size(); i++) {
                if (filteredList.get(i).getPrice() == Double.valueOf(price.getText().toString())) {
                    filteredList1.add(filteredList.get(i));
                }
            }
        } else {
            filteredList1 = filteredList;
        }
        Spinner types = view.findViewById(R.id.names);
        List<Car> filteredList2 = new ArrayList<>();

        if (!types.getSelectedItem().toString().equals("select name"))
            for (int i = 0; i < filteredList1.size(); i++) {
                if (filteredList1.get(i).getMake().equalsIgnoreCase(types.getSelectedItem().toString()))
                    filteredList2.add(filteredList1.get(i));
            }
        else
            filteredList2 = filteredList1;
        fillcars(filteredList2, view);
    }


    public void fillcars(List<Car> cars, View root) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerCars);
        recyclerView.removeAllViewsInLayout();
        recyclerView.removeAllViews();
        CarAdapter listAdapter = new CarAdapter(getActivity().getApplicationContext(), cars);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }


}