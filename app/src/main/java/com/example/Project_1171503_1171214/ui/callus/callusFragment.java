package com.example.Project_1171503_1171214.ui.callus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Project_1171503_1171214.R;

public class callusFragment extends Fragment {
    Button b;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.callus_fragment, container, false);
        ImageButton callus = (ImageButton) view.findViewById(R.id.callus);
        ImageButton findus = (ImageButton) view.findViewById(R.id.findus);
        ImageButton email = (ImageButton) view.findViewById(R.id.mailus);

        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent();
                newIntent.setAction(Intent.ACTION_DIAL);
                newIntent.setData(Uri.parse("tel:+”0599000000"));
                startActivity(newIntent);
            }
        });

        findus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                myIntent.setAction(Intent.ACTION_VIEW);
                myIntent.setData(Uri.parse("geo:19.076,72.8777"));
                startActivity(myIntent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822");
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, "CarDealer@cars.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "CarDelar");
                intent.putExtra(Intent.EXTRA_TEXT, "content of the message");
                startActivity(intent);
            }
        });
        return view;
    }

}