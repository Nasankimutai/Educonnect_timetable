package com.example.educonnectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.editTextEmailAddress);
        edPassword= findViewById(R.id.editTextConfirm_password);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textview_new_account);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                if(Email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all details",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Login success",Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }
}