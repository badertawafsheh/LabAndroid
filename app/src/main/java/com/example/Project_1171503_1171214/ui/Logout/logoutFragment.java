package com.example.Project_1171503_1171214.ui.Logout;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Project_1171503_1171214.R;
import com.example.Project_1171503_1171214.SignINActivity;
import com.example.Project_1171503_1171214.SignUPActivity;

public class logoutFragment extends Fragment {
SignINActivity signin ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().finish();
     return inflater.inflate(R.layout.logout_fragment, container, false);


    }



}