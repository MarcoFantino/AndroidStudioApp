package com.example.progettolab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Booking extends AppCompatActivity {

    private RecyclerView lessonsList;
    private Spinner spinnerCourse;
    private Spinner spinnerDays;
    private Button btnFind;
    int coursePos;
    int datePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        lessonsList = findViewById(R.id.lessonsList);
        spinnerCourse = findViewById(R.id.spinnerCourse);
        spinnerDays = findViewById(R.id.spinnerDays);
        btnFind = findViewById(R.id.btnFind);


        ArrayList<String> date = new ArrayList<>();
        date.add("21/10/2021");
        date.add("22/10/2021");
        date.add("23/10/2021");
        date.add("24/10/2021");
        date.add("25/10/2021");

        //get Courses from db
        Model.setCoursesList(Model.getDataBaseHelper().getCourses());

        //get Teachers from db
        Model.setTeachersList(Model.getDataBaseHelper().getTeachers());

        //adapter for courses
        ArrayAdapter<String> spinnerArrayAdapterCourse = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, Model.getCoursesList() );

        //adapter for dates
        ArrayAdapter<String> spinnerArrayAdapterDate = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                date
        );

        //adapter for associations
        LessonsRecViewAdapter adapter = new LessonsRecViewAdapter();

        spinnerCourse.setAdapter(spinnerArrayAdapterCourse);
        spinnerDays.setAdapter(spinnerArrayAdapterDate);
        lessonsList.setLayoutManager(new LinearLayoutManager(this));

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.setAssociationsList(Model.getDataBaseHelper().getAssociations(Model.getCoursesList().get(coursePos)));
                Model.setCalendarList(Model.getDataBaseHelper().getCalendar(Model.getCoursesList().get(coursePos), date.get(datePos)));
                Model.setCurrentDate(date.get(datePos));
                adapter.setAssociations(Model.getAssociationsList());
                lessonsList.setAdapter(adapter);
            }
        });

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coursePos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                coursePos = 0;
            }
        });

        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                datePos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                datePos = 0;
            }
        });
    }
}