package com.example.Project_1171503_1171214;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SignINActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new
            DataBaseHelper(SignINActivity.this, "Person", null, 1);

    EditText email, password;
    CheckBox remeber;
    public static String extra_email = "com.example.Project_1171503_1171214";


    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        email = findViewById(R.id.EmailBox);
        password = findViewById(R.id.PasswordBox);
        remeber = findViewById(R.id.login_rememberme);
        FloatingActionButton login = (FloatingActionButton) findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String data = DatabaseUtils.dumpCursorToString(dataBaseHelper.getAllPerson());
                System.out.println(data);

                int count = dataBaseHelper.searchBy_email_pass(email.getText().toString(), password.getText().toString());
                System.out.println("----------------------------------------------the count = " + count);
                if (count >= 1) {
                    password.setText("");

                    startActivity(new Intent(SignINActivity.this, Customer_main_activity.class).putExtra(extra_email, email.getText().toString()));

                } else {

                    Toast toast = Toast.makeText(SignINActivity.this,
                            "the user name or pass is incorect", Toast.LENGTH_SHORT);
                    toast.show();

                }


            }
        });
        sharedPrefManager = SharedPrefManager.getInstance(this);
        if (!sharedPrefManager.readString("email", "noValue").equals("noValue")) {
            email.setText(sharedPrefManager.readString("email", "noValue"));
           // password.setText(sharedPrefManager.readString("password", "noValue"));
            remeber.setChecked(true);
        }
        remeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (remeber.isChecked()) {
                    sharedPrefManager = SharedPrefManager.getInstance(SignINActivity.this);
                    sharedPrefManager.writeString("email", email.getText().toString());
                    sharedPrefManager.writeString("password", password.getText().toString());
                } else if (!remeber.isChecked()) {
                    sharedPrefManager = SharedPrefManager.getInstance(SignINActivity.this);
                    sharedPrefManager.writeString("email", "noValue");
                    sharedPrefManager.writeString("password", "noValue");
                }
            }
        });


    }


    public void OpenSignupPage(View view) {
        Intent intent = new Intent(SignINActivity.this, SignUPActivity.class);

        SignINActivity.this.startActivity(intent);
        finish();

    }


}