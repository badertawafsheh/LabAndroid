package com.example.Project_1171503_1171214.ui.Favouites;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Project_1171503_1171214.Car;
import com.example.Project_1171503_1171214.DataBaseHelper;
import com.example.Project_1171503_1171214.R;
import com.example.Project_1171503_1171214.carJsonParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class favouritFragment extends Fragment {


    public static favouritFragment newInstance() {
        return new favouritFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("My Shared Preference",
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("email", "");
        ArrayList<Car> reservedByMe = new ArrayList<>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext().getApplicationContext());
        Set<Integer> reservedIndex = new HashSet<>();
        Cursor cursor = dataBaseHelper.getFavByEmail(name);
        while (cursor.moveToNext()) {
            reservedIndex.add(Integer.parseInt(cursor.getString(1)));
        }
        for (Integer index : reservedIndex) {
            reservedByMe.add(carJsonParser.cars.get(index));
        }
        View view = inflater.inflate(R.layout.favourit_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerFavs);
        FavortieAdapter adapter = new FavortieAdapter(getActivity().getApplicationContext(), reservedByMe);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}