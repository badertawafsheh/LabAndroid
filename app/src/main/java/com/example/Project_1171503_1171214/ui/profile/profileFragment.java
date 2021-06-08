package com.example.Project_1171503_1171214.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Project_1171503_1171214.Customer_main_activity;
import com.example.Project_1171503_1171214.DataBaseHelper;
import com.example.Project_1171503_1171214.Person;
import com.example.Project_1171503_1171214.R;
import com.example.Project_1171503_1171214.SignINActivity;
import com.example.Project_1171503_1171214.SignUPActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class profileFragment extends Fragment {
    TextView head;
    TextView first_name;
    TextView last_name;
    TextView phone;
    TextView password;
    TextView check_password;
    Button confirm;
    ImageView profile_change;


    DataBaseHelper dataBaseHelper = new
            DataBaseHelper(getActivity(), "Person", null, 1);


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        Customer_main_activity activity = (Customer_main_activity) getActivity();
        final String email = activity.getMyData();


        DataBaseHelper dataBaseHelper = new
                DataBaseHelper(getContext().getApplicationContext(), "Person", null, 1);
        head = (TextView) view.findViewById(R.id.editprofile_name_phone);
        profile_change = view.findViewById(R.id.profile_pic);
        first_name = (TextView) view.findViewById(R.id.editProfileFirstnameBox);
        last_name = (TextView) view.findViewById(R.id.editProfileLastnameBox);
        phone = (TextView) view.findViewById(R.id.editProfilePhoneBox);
        password = (TextView) view.findViewById(R.id.editProfilePassBox);
        check_password = (TextView) view.findViewById(R.id.editProfilePassBox2);
        confirm = view.findViewById(R.id.editprofile_savebtn);
        Cursor login_person = dataBaseHelper.searchBy_email_(email);
        if (login_person.moveToFirst()) {
            first_name.setText(login_person.getString(login_person.getColumnIndex("First_Name")));
            last_name.setText(login_person.getString(login_person.getColumnIndex("Last_Name")));
            head.setText(first_name.getText().toString() + " " + last_name.getText().toString());
            phone.setText(login_person.getString(login_person.getColumnIndex("Phone")));
            password.setText(login_person.getString(login_person.getColumnIndex("PASSWORD")));
            check_password.setText(login_person.getString(login_person.getColumnIndex("PASSWORD")));

        }
        login_person.close();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (first_name.getText().toString().length() < 3 || last_name.getText().toString().length() < 3) {
                    Toast toast = Toast.makeText(getActivity(),
                            "Please make sure that your first and last name have three charcter at least ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (!password.getText().toString().equals(check_password.getText().toString())) {

                    Toast toast = Toast.makeText(getActivity(),
                            "Please make sure that two password are matching ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                String regex2 = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
                //Compile regular expression to get the pattern
                Pattern pattern2 = Pattern.compile(regex2);
                Matcher matcher2 = pattern2.matcher(password.getText().toString());
                if (matcher2.matches() == false) {
                    Toast toast = Toast.makeText(getActivity(),
                            "Please make sure that Password match with specification", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                dataBaseHelper.update_person(email, first_name.getText().toString(), last_name.getText().toString(), phone.getText().toString(), password.getText().toString());
                Toast toast2 = Toast.makeText(getActivity(),
                        "The change was save", Toast.LENGTH_SHORT);
                toast2.show();
            }
        });

        profile_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallary = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallary, 1004);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1004) {
            if (resultCode == Activity.RESULT_OK) {
                Uri pic_uri = data.getData();
                profile_change.setImageURI(pic_uri);
            }
        }
    }

    public void change_pic(View view) {


    }


}