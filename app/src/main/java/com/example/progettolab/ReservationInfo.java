package com.example.progettolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ReservationInfo extends AppCompatActivity {

    private TextView txtDateInfo;
    private TextView txtHourInfo;
    private TextView txtCourseInfo;
    private TextView txtTeacherInfo;
    private Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_info);
        initView();

        txtDateInfo.setText(Model.getCurrentReservation().getDate());
        txtHourInfo.setText(Model.getCurrentReservation().getHour());
        txtCourseInfo.setText(Model.getCurrentReservation().getCourseName());
        txtTeacherInfo.setText(Model.findTeacher(Model.getCurrentReservation().getTeachersId() , Model.getTeachersList()));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getDataBaseHelper().deleteReservation(Model.getCurrentReservation());
                Intent intent = new Intent(ReservationInfo.this, Homepage.class);
                Model.setCurrentReservation(null);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        txtDateInfo =  findViewById(R.id.txtDateConf);
        txtHourInfo =  findViewById(R.id.txtHourConf);
        txtCourseInfo =  findViewById(R.id.txtCourseConf);
        txtTeacherInfo =  findViewById(R.id.txtTeacherConf);
        btnDelete = findViewById(R.id.btnDelete);
    }
}