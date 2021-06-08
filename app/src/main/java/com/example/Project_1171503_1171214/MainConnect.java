package com.example.Project_1171503_1171214;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainConnect extends AppCompatActivity {
    Button button;
    LinearLayout linearLayout;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_main);
        setProgress(false);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int connectionFlag = 0;
                connectivityManager = (ConnectivityManager) getSystemService((Context.CONNECTIVITY_SERVICE));
                networkInfo = connectivityManager.getActiveNetworkInfo();
                if ((networkInfo != null) && (networkInfo.isConnected())) {
                    connectionFlag = 1;
                }

                if (connectionFlag == 1) {

                    ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainConnect.this);
                    connectionAsyncTask.execute("http://www.mocky.io/v2/5bfea5963100006300bb4d9a");
                    Intent intent = new Intent(MainConnect.this, SignINActivity.class);
                    MainConnect.this.startActivity(intent);
                    finish();

                } else {
                    Toast toast = Toast.makeText(MainConnect.this,
                            "No Internet Connection", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.layout);

    }

    public void setButtonText(String text) {
        button.setText(text);
    }

    public void fillCars(List<Car> cars) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        linearLayout.removeAllViews();
        for (int i = 0; i < cars.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(cars.get(i).toString());
            linearLayout.addView(textView);
        }
    }

    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
