package com.example.progettolab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmReservation extends AppCompatActivity {

    private TextView txtDateConf;
    private TextView txtHourConf;
    private TextView txtCourseConf;
    private TextView txtTeacherConf;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);

        initView();

        if(Model.getCurrentCalendar() == null && Model.getTeacher() == null){
            txtDateConf.setText("");
            txtHourConf.setText("");
            txtCourseConf.setText("");
            txtTeacherConf.setText("");
        }else{
            assert Model.getCurrentCalendar() != null;
            assert Model.getTeacher() != null;
            txtDateConf.setText(Model.getCurrentCalendar().getDate());
            txtHourConf.setText(Model.getCurrentCalendar().getHour());
            txtCourseConf.setText(Model.getCurrentCalendar().getCourseName());
            txtTeacherConf.setText(Model.getTeacher().getLastName());
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Model.getDataBaseHelper().addCalendar(Model.getCurrentCalendar());
                    Model.getDataBaseHelper().addReservation(Model.getCurrentCalendar());
                    Toast.makeText(ConfirmReservation.this, "Confirmed" , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ConfirmReservation.this, Homepage.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void initView(){
        txtDateConf =  findViewById(R.id.txtDateConf);
        txtHourConf =  findViewById(R.id.txtHourConf);
        txtCourseConf =  findViewById(R.id.txtCourseConf);
        txtTeacherConf =  findViewById(R.id.txtTeacherConf);
        btnConfirm =  findViewById(R.id.btnDelete);
    }
}