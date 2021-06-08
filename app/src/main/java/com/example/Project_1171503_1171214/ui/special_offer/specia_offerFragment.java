package com.example.Project_1171503_1171214.ui.special_offer;

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
import com.example.Project_1171503_1171214.R;
import com.example.Project_1171503_1171214.carJsonParser;

public class specia_offerFragment extends Fragment {

    public static specia_offerFragment newInstance() {
        return new specia_offerFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specia_offer_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerOffers);
        specialAdapter listAdapter2 = new specialAdapter(getActivity().getApplicationContext(), carJsonParser.cars );
        recyclerView.setAdapter(listAdapter2);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }



}