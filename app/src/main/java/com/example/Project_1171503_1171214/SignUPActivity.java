package com.example.Project_1171503_1171214;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUPActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new
            DataBaseHelper(SignUPActivity.this, "Person", null, 1);


    TextView first_name;
    TextView last_name;
    TextView Email;
    TextView Phone;
    TextView Password;
    TextView Confirm_password;
    Spinner Gender;
    Spinner city;
    Spinner country;
    Button register;
    TextView zip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        String[] country_list = {"Palistine", "Egypt", "Jordan", "Sudia Arabi"};
        String[] city_Palistine = {"Nables", "Ramallah", "Tulkarem"};
        String[] city_Egypt = {"Cairo", "Alexandria", "Gizeh"};
        String[] city_jordan = {"Amman", "Irbid", "Alzarqa"};
        String[] city_sudia = {"Dammam", "Dhahran", "Al Bahah"};


        first_name = findViewById(R.id.signUpFirstnameBox);
        last_name = findViewById(R.id.signUpLastnameBox);
        Email = findViewById(R.id.signUpEmailBox);
        Phone = findViewById(R.id.signUpPhoneBox);
        Confirm_password = findViewById(R.id.signUpPassBox);
        Password = findViewById(R.id.signUpPassBox2);
        zip = findViewById(R.id.Zipcode);
        Gender = findViewById(R.id.spinnerGinder);
        city = findViewById(R.id.spinnerCity);
        country = findViewById(R.id.spinnerCountry);
        register = findViewById(R.id.button2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Gender.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Contry, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        country.setAdapter(adapter3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.city_Palistine, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        city.setAdapter(adapter2);


        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if (arg0.equals(country)) {

                    if (country.getSelectedItem().equals("Palistine")) {
                        ArrayAdapter<String> s1 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_Palistine);
                        s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s1);
                        zip.setText("+970");
                    } else if (country.getSelectedItem().equals("Egypt")) {
                        ArrayAdapter<String> s2 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_Egypt);
                        s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s2);
                        zip.setText("+20");

                    } else if (country.getSelectedItem().equals("Jordan")) {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_jordan);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+962");

                    } else {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_sudia);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+966");


                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person newperson = new Person();


                boolean Test_empty_field = Password.getText().toString().isEmpty() || Confirm_password.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || first_name.getText().toString().isEmpty() || last_name.getText().toString().isEmpty() || Phone.getText().toString().isEmpty();
                if (Test_empty_field == true) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please Fill require field", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (!Password.getText().toString().equals(Confirm_password.getText().toString())) {

                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please make sure that two password are matching ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                //Regular Expression
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                //Compile regular expression to get the pattern
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(Email.getText().toString());
                if (matcher.matches() == false) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please make sure that Email is in proper formate ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                int email_alrady = dataBaseHelper.searchBy_email(Email.getText().toString());
                if (email_alrady >= 1) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "This email allrady sigin in  ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }

                String regex2 = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
                //Compile regular expression to get the pattern
                Pattern pattern2 = Pattern.compile(regex2);
                System.out.println("---------the pass is :   " + Password.getText().toString());
                Matcher matcher2 = pattern2.matcher(Password.getText().toString());
                if (matcher2.matches() == false) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please make sure that Password match with specification", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (first_name.getText().toString().length() < 3 || last_name.getText().toString().length() < 3) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please make sure that your first and last name have three charcter at least ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                newperson.setEmail(Email.getText().toString());
                newperson.setPassword(Password.getText().toString());
                newperson.setGender(Gender.getSelectedItem().toString());
                newperson.setCity(city.getSelectedItem().toString());
                newperson.setCountry(country.getSelectedItem().toString());
                newperson.setFirst_name(first_name.getText().toString());
                newperson.setSecond_name(last_name.getText().toString());
                newperson.setPhone(Phone.getText().toString());
                dataBaseHelper.insertPerson(newperson);

                Intent intent = new Intent(SignUPActivity.this, SignINActivity.class);
                OpenSignINPage();
                finish();
            }
        });


    }

    public void OpenSignINPage() {
        Intent intent = new Intent(SignUPActivity.this, SignINActivity.class);

        SignUPActivity.this.startActivity(intent);
        finish();

    }
}