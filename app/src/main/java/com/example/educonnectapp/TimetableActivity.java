package com.example.educonnectapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TimetableActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String SELECT_DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        setupUIViews();
        initToolbar();
        setupListView();

    }

    private void setupUIViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.lv_timetable);
        sharedPreferences = getSharedPreferences("MY DAY", MODE_PRIVATE);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timetable");
    }

    private void setupListView(){

        String[] title = getResources().getStringArray(R.array.Timetable);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: {
                        startActivity(new Intent(TimetableActivity.this, DayDetailActivity.class));
                        sharedPreferences.edit().putString(SELECT_DAY, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(TimetableActivity.this, DayDetailActivity.class));
                        sharedPreferences.edit().putString(SELECT_DAY, "Tuesday").apply();

                        break;
                    }
                    case 2: {
                        startActivity(new Intent(TimetableActivity.this, DayDetailActivity.class));
                        sharedPreferences.edit().putString(SELECT_DAY, "Wednesday").apply();

                        break;
                    }
                    case 3: {
                        startActivity(new Intent(TimetableActivity.this, DayDetailActivity.class));
                        sharedPreferences.edit().putString(SELECT_DAY, "Thursday").apply();

                        break;
                    }
                    case 4: {
                        startActivity(new Intent(TimetableActivity.this, DayDetailActivity.class));
                        sharedPreferences.edit().putString(SELECT_DAY, "Friday").apply();

                        break;
                    }
                    default: break;
                }
            }
        });
    }
    public class SimpleAdapter extends BaseAdapter{

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title, String[] description){
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.timetable_single_item, null);
            }
            title = (TextView)convertView.findViewById(R.id.tv_timetable);
            description = (TextView)convertView.findViewById(R.id.tv_description);
            imageView = (ImageView)convertView.findViewById(R.id.iv_timetable);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            if(titleArray[position].equalsIgnoreCase("Monday")){
                imageView.setImageResource(R.drawable.monday);
            } else if(titleArray[position].equalsIgnoreCase("Tuesday")) {
                imageView.setImageResource(R.drawable.tuesday);
            } else if (titleArray[position].equalsIgnoreCase("Wednesday")) {
                imageView.setImageResource(R.drawable.wednesday);
            } else if(titleArray[position].equalsIgnoreCase("Thursday")) {
                imageView.setImageResource(R.drawable.thursday);
            }else{
                imageView.setImageResource(R.drawable.friday);
            }

            return convertView;
        }
    }
}