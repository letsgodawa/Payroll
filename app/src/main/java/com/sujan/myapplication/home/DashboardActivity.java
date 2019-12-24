package com.sujan.myapplication.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sujan.myapplication.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new UserDashboardFragment(),"").addToBackStack("").commit();
    }
}
