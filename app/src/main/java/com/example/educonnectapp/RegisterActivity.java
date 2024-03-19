package com.example.educonnectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edFirst_name, edLast_name, edEmail, edPassword, edConfirm_password;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edFirst_name = findViewById(R.id.editTextFirst_name);
        edLast_name = findViewById(R.id.editTextLast_name);
        edEmail = findViewById(R.id.editTextEmail);
        edPassword = findViewById(R.id.editTextPassword);
        edConfirm_password = findViewById(R.id.editTextConfirm_password);
        btn = findViewById(R.id.buttonCreate_account);
        tv = findViewById(R.id.textView_existing_user);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String First_name = edFirst_name.getText().toString();
                String Last_name = edLast_name.getText().toString();
                String Email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String Confirm_password = edConfirm_password.getText().toString();
                Database db = new Database(getApplicationContext(), "timetable", null, 1);

                if (First_name.isEmpty() || Last_name.isEmpty() || Email.isEmpty() || password.isEmpty()
                        || Confirm_password.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "please fill all details", Toast.LENGTH_SHORT).show();
                } else
                    if (password.compareTo(Confirm_password)==0) {
                        if (isValid(password)){
                            db.create_account(First_name,Last_name,Email,password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain atleast 8 characters having a letter digit and special character", Toast.LENGTH_LONG).show();


                        }

                }else{
                        Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_SHORT).show();
                    }

            }
        });

    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if (passwordhere.length() < 8) {
            return false;
        }else {
            for (int p = 0; p <passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1=1;
                }

            }
            for (int r= 0; r <passwordhere.length(); r++) {
                if (Character.isLetter(passwordhere.charAt(r))) {
                    f2=1;
                }

            }
            for (int s = 0; s <passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}