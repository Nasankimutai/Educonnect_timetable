package com.example.educonnectapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DayDetailActivity extends AppCompatActivity {

    private ListView listView;
    private androidx.appcompat.widget.Toolbar toolbar;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] time1;
    public static String[] time2;
    public static String[] time3;
    public static String[] time4;
    public static String[] time5;
    private String[] preferredDay;
    private String[] preferredTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_day_detail);

        setupUIViews();
        setupListView();
    }

    private void setupUIViews(){
        listView = (ListView)findViewById(R.id.lv_Day_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbarDay_detail);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(TimetableActivity.sharedPreferences.getString(TimetableActivity.SELECT_DAY, null));
    }
    private void setupListView(){
        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);

        time1 = getResources().getStringArray(R.array.time1);
        time2 = getResources().getStringArray(R.array.time2);
        time3 = getResources().getStringArray(R.array.time3);
        time4 = getResources().getStringArray(R.array.time4);
        time5 = getResources().getStringArray(R.array.time5);

        String selected_day = TimetableActivity.sharedPreferences.getString(TimetableActivity.SELECT_DAY, null);

        if(selected_day.equalsIgnoreCase("Monday")){
            preferredDay = Monday;
            preferredTime = time1;
        } else if (selected_day.equalsIgnoreCase("Tuesday")) {
            preferredDay = Tuesday;
            preferredTime = time2;
        }else if (selected_day.equalsIgnoreCase("Wednesday")) {
            preferredDay = Wednesday;
            preferredTime = time3;
        }else if (selected_day.equalsIgnoreCase("Thursday")) {
            preferredDay = Thursday;
            preferredTime = time4;
        }else {
            preferredDay = Friday;
            preferredTime = time5;
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, preferredDay, preferredTime);
        listView.setAdapter(simpleAdapter);

    }
    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView unit, time;
        private String[] unitArray;
        private String[] timeArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] unit, String[] time){
            mContext = context;
            unitArray = unit;
            timeArray = time;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return unitArray.length;
        }

        @Override
        public Object getItem(int position) {
            return unitArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.day_detail_single_item, null);
            }
            unit = (TextView)convertView.findViewById(R.id.tv_UnitDay_detail);
            time = (TextView)convertView.findViewById(R.id.tvTime);
            imageView = (ImageView)convertView.findViewById(R.id.iv_day_detail);

            unit.setText(unitArray[position]);
            time.setText(timeArray[position]);

//            if(unitArray[position].equalsIgnoreCase("Monday")){
//                imageView.setImageResource(R.drawable.monday);
//            } else if(titleArray[position].equalsIgnoreCase("Tuesday")) {
//                imageView.setImageResource(R.drawable.tuesday);
//            } else if (titleArray[position].equalsIgnoreCase("Wednesday")) {
//                imageView.setImageResource(R.drawable.wednesday);
//            } else if(titleArray[position].equalsIgnoreCase("Thursday")) {
//                imageView.setImageResource(R.drawable.thursday);
//            }else{
//                imageView.setImageResource(R.drawable.friday);
//            }

            return convertView;
        }
    }
}