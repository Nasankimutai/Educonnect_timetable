package com.example.educonnectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeTextView;
    ImageButton timetableButton, notesButton, subjectDetailsButton, whatsappButton;
    RecyclerView notificationsRecyclerView;
    NotificationAdapter notificationAdapter;
    List<String> notifications;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new Database(this, "timetable", null, 1);

        welcomeTextView = findViewById(R.id.textView);
        timetableButton = findViewById(R.id.imageButton);
        notesButton = findViewById(R.id.imageButton4);
        subjectDetailsButton = findViewById(R.id.imageButton2);
        whatsappButton = findViewById(R.id.imageButton5);
        notificationsRecyclerView = findViewById(R.id.notificationsRecyclerView);

        // Retrieve the logged-in user's email from the intent
        String loggedInUserEmail = getIntent().getStringExtra("loggedInUserEmail");

        // Retrieve the logged-in user's first name using their email
        String loggedInUserFirstName = getLoggedInUserFirstName(loggedInUserEmail);

        // Display the welcome message with the logged-in user's first name
        welcomeTextView.setText(String.format("Welcome %s", loggedInUserFirstName));
        // Button click listeners
        timetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TimetableActivity.class));
            }
        });

        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotesActivity.class));
            }
        });

        subjectDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SubjectDetailsActivity.class));
            }
        });

        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, WhatsAppActivity.class));
            }
        });

        notifications = new ArrayList<>();
        notifications.add("Notification 1");
        notifications.add("Notification 2");
        notifications.add("Notification 3");

        // Initialize RecyclerView
        notificationAdapter = new NotificationAdapter(notifications);
        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notificationsRecyclerView.setAdapter(notificationAdapter);
    }

    // Function to get logged-in user's first name from the database
    // Function to get logged-in user's first name from the database
    private String getLoggedInUserFirstName(String email) {
        String firstName = "";
        Cursor cursor = db.getUserFirstName(email);
        if (cursor != null && cursor.moveToFirst()) {
            int firstNameColumnIndex = cursor.getColumnIndex("First_name");
            if (firstNameColumnIndex != -1) {
                firstName = cursor.getString(firstNameColumnIndex);
            } else {
                // Handle the case where the column does not exist
                // Log an error message or display a toast
                Toast.makeText(this, "Column 'First_name' not found", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            // Handle the case where the cursor is null or empty
            // Log an error message or display a toast
            Toast.makeText(this, "Cursor is null or empty", Toast.LENGTH_SHORT).show();
        }
        return firstName;
    }





}
