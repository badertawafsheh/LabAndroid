package com.example.Project_1171503_1171214.ui.reservtion;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Project_1171503_1171214.Car;
import com.example.Project_1171503_1171214.DataBaseHelper;
import com.example.Project_1171503_1171214.R;
import com.example.Project_1171503_1171214.carJsonParser;

import java.util.ArrayList;

public class reservtionFragment extends Fragment {


    public static reservtionFragment newInstance() {
        return new reservtionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("My Shared Preference",
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("email", "");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext().getApplicationContext());
        ArrayList<Pair<Car, String>> reservedByMe = new ArrayList<>();
        Cursor cursor = dataBaseHelper.getReservesByEmail(name);
        while (cursor.moveToNext()) {
            Pair<Car, String> stringPair = new Pair<>(carJsonParser.cars.get(Integer.parseInt(cursor.getString(1))), cursor.getString(2));
            reservedByMe.add(stringPair);
        }

        View view = inflater.inflate(R.layout.reservtion_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerRes);
        reservationAdapter resAdapter = new reservationAdapter(getActivity().getApplicationContext(), reservedByMe);
        recyclerView.setAdapter(resAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

}