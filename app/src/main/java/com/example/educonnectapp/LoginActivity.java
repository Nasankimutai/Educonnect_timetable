package com.example.educonnectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassword;
    TextView tv;
    Button btn;
    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.editTextEmailAddress);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edPassword= findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textview_new_account);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"timetable", null,1);
                if(Email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all details",Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(Email,password)==1){
                        Toast.makeText(getApplicationContext(), "Login success",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Email",Email);
                        editor.apply();
                        // Inside LoginActivity after successful login
                        String loggedInUserEmail = Email; // Email obtained from EditText
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("loggedInUserEmail", loggedInUserEmail);
                        startActivity(intent);

                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid Email or Password",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        initToolbar();

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
    }
}