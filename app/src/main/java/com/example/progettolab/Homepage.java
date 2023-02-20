package com.example.progettolab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.progettolab.fragments.recentReservations;
import com.example.progettolab.fragments.reservationsList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Homepage extends AppCompatActivity {

    private BottomNavigationView bottom_nav;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //get Teachers from db
        Model.setTeachersList(Model.getDataBaseHelper().getTeachers());

        bottom_nav = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper , new recentReservations()).commit();

        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.ic_booking){
                    Intent intent = new Intent(Homepage.this , Booking.class);
                    startActivity(intent);
                }else{
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.ic_home:
                            selectedFragment = new recentReservations();
                            break;
                        case R.id.ic_reservation:
                            selectedFragment = new reservationsList();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper , selectedFragment).commit();
                }
                return true;
            }
        });

    }
}