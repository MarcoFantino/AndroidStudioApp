package com.example.progettolab;

import android.app.Application;
import android.content.Context;

import com.example.progettolab.Obj.Association;
import com.example.progettolab.Obj.Calendar;
import com.example.progettolab.Obj.Reservation;
import com.example.progettolab.Obj.Teacher;
import com.example.progettolab.Obj.User;

import java.util.ArrayList;

public  class Model extends Application{

    private static ArrayList<Calendar> calendarList;
    private static Teacher teacher;
    private static DataBaseHelper dataBaseHelper;
    private static User user;
    private static ArrayList<String> coursesList;
    private static ArrayList<Teacher> teachersList;
    private static ArrayList<Association> associationsList;
    private static Calendar currentCalendar;
    private static String currentDate;
    private static Reservation currentReservation;

    public static void setDataBaseHelper(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }

    public static DataBaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }

    public static Teacher getTeacher() {
        return teacher;
    }

    public static void setTeacher(Teacher t) {
       teacher = t;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Model.user = user;
    }

    public static ArrayList<String> getCoursesList() {
        return coursesList;
    }

    public static void setCoursesList(ArrayList<String> coursesList) {
        Model.coursesList = coursesList;
    }

    public static ArrayList<Teacher> getTeachersList() {
        return teachersList;
    }

    public static String findTeacher(int id, ArrayList<Teacher> t){
        String name = "notFound";
        if(t != null){
            for(int i=0; i<teachersList.size(); i++){
                if(teachersList.get(i).getId() == id){
                    name = teachersList.get(i).getLastName();
                    break;
                }
            }
        }
        return name;
    }

    public static void setTeachersList(ArrayList<Teacher> teachersList) {
        Model.teachersList = teachersList;
    }

    public static ArrayList<Association> getAssociationsList() {
        return associationsList;
    }

    public static void setAssociationsList(ArrayList<Association> associationsList) {
        Model.associationsList = associationsList;
    }

    public static ArrayList<Calendar> getCalendarList() {
        return calendarList;
    }

    public static void setCalendarList(ArrayList<Calendar> calendarList) {
        Model.calendarList = calendarList;
    }

    public static Calendar getCurrentCalendar() {
        return currentCalendar;
    }

    public static void setCurrentCalendar(Calendar currentCalendar) {
        Model.currentCalendar = currentCalendar;
    }

    public static String getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(String currentDate) {
        Model.currentDate = currentDate;
    }

    public static Reservation getCurrentReservation() {
        return currentReservation;
    }

    public static void setCurrentReservation(Reservation currentReservation) {
        Model.currentReservation = currentReservation;
    }
}
