package com.example.progettolab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.progettolab.Obj.Association;
import com.example.progettolab.Obj.Calendar;
import com.example.progettolab.Obj.Reservation;
import com.example.progettolab.Obj.Teacher;
import com.example.progettolab.Obj.User;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper{

    //user_table_constants
    public static final String COLUMN_USER_ID = "UserId";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_USER_NAME = "UserName";
    public static final String USERS_TABLE = "USERS_TABLE";

    public static final String COURSE = "COURSE";
    //Courses_table_constants
    public static final String COURSES_TABLE = COURSE + "S_TABLE";
    public static final String COURSE_NAME = COURSE + "_NAME";

    public static final String TEACHER = "TEACHER";
    //Teachers_table_constants
    public static final String TEACHERS_TABLE = TEACHER + "S_TABLE";
    public static final String TEACHER_ID = TEACHER + "_ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";

    //Associations_table_constants
    public static final String ASSOCIATIONS_TABLE = "ASSOCIATIONS_TABLE";
    public static final String PK_ASSOCIATIONS = "PK_ASSOCIATIONS";
    public static final String FK_ASSOCIATIONS_T = "FK_ASSOCIATIONS_T";
    public static final String FK_ASSOCIATIONS_C = "FK_ASSOCIATIONS_C";
    public static final String T_FIRST = "T_FIRST";
    public static final String T_LAST = "T_LAST";
    public static final String FK_ASSOCIATIONS_TF = "FK_ASSOCIATIONS_TF";
    public static final String FK_ASSOCIATIONS_TS = "FK_ASSOCIATIONS_TS";

    //Reservations_table_constants
    public static final String RESERVATIONS_TABLE = "RESERVATIONS_TABLE";
    public static final String RESERV_ID = "RESERV_ID";
    public static final String STATUS = "STATUS";

    //Calendar_table_constants
    public static final String CALENDAR_TABLE = "CALENDAR_TABLE";
    public static final String CALENDAR_ID = "CALENDAR_ID";
    public static final String DATE = "DATE";
    public static final String HOUR = "HOUR";



    public DataBaseHelper(@Nullable Context context) {
        super(context, "ripeBooing.db" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String users = "CREATE TABLE " + USERS_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_EMAIL + " Text UNIQUE NOT NULL, " + COLUMN_PASSWORD + " Text NOT NULL, " + COLUMN_USER_NAME + " Text UNIQUE NOT NULL)";

        String courses = "CREATE TABLE " + COURSES_TABLE + " (" + COURSE_NAME + " TEXT PRIMARY KEY NOT NULL)";

        String teachers = "CREATE TABLE " + TEACHERS_TABLE + " (" + TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRST_NAME + " TEXT NOT NULL, " + LAST_NAME + " TEXT NOT NULL)";

        String associations = "CREATE TABLE " + ASSOCIATIONS_TABLE + " (" + TEACHER + " INTEGER, " + COURSE + " TEXT, " + T_FIRST + " TEXT, " + T_LAST + " TEXT, " +
                "CONSTRAINT " + PK_ASSOCIATIONS + " PRIMARY KEY (TEACHER , COURSE), " +
                "CONSTRAINT " + FK_ASSOCIATIONS_T + " FOREIGN KEY(" + TEACHER + ") REFERENCES TEACHERS_TABLE(TEACHER_ID) ON UP" + DATE + " CASCADE ON DELETE CASCADE, " +
                "CONSTRAINT " + FK_ASSOCIATIONS_C + " FOREIGN KEY(" + COURSE + ") REFERENCES COURSES_TABLE(COURSE_NAME) ON UP" + DATE + " CASCADE ON DELETE CASCADE, " +
                "CONSTRAINT " + FK_ASSOCIATIONS_TF + " FOREIGN KEY(" + T_FIRST + ") REFERENCES TEACHERS_TABLE(FIRST_NAME) ON UP" + DATE + " CASCADE, " +
                "CONSTRAINT " + FK_ASSOCIATIONS_TS + " FOREIGN KEY(" + T_LAST + ") REFERENCES TEACHERS_TABLE(LAST_NAME) ON UP" + DATE + " CASCADE)";

        String reservations = "CREATE TABLE " + RESERVATIONS_TABLE + " (" + RESERV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, USER INTEGER, TEACHER INTEGER, COURSE TEXT, " + DATE + "  TEXT NOT NULL, " + HOUR + " TEXT NOT NULL, " + STATUS + " TEXT NOT NULL," +
                            "FOREIGN KEY(USER) REFERENCES USER_TABLE(COLUMN_USER_ID) ON UPDATE CASCADE ON DELETE CASCADE," +
                            "FOREIGN KEY(TEACHER) REFERENCES ASSOCIATIONS_TABLE(TEACHER) ON UPDATE CASCADE ON DELETE CASCADE," +
                            "FOREIGN KEY(COURSE) REFERENCES ASSOCIASIONS_TABLE(COURSE) ON UPDATE CASCADE ON DELETE CASCADE)";

        String calendar = "CREATE TABLE " + CALENDAR_TABLE + " (" + CALENDAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, USER INTEGER, TEACHER INTEGER, COURSE TEXT, " + DATE + " TEXT NOT NULL, " + HOUR + " TEXT NOT NULL," +
                "FOREIGN KEY(TEACHER) REFERENCES ASSOCIATIONS_TABLE(TEACHER) ON UP" + DATE + " CASCADE ON DELETE CASCADE," +
                "FOREIGN KEY(COURSE) REFERENCES ASSOCIATIONS_TABLE(COURSE) ON UP" + DATE + " CASCADE ON DELETE CASCADE," +
                "FOREIGN KEY(USER) REFERENCES USERS_TABLE(COLUMN_USER_ID) ON UPDATE CASCADE ON DELETE CASCADE,"+
                "CONSTRAINT UN_CONSTRAINT UNIQUE(TEACHER,COURSE," + DATE + "," + HOUR + "))";

        String addTeacher = "INSERT INTO TEACHERS_TABLE(FIRST_NAME , LAST_NAME) VALUES ('Mario', 'Rossi'), ('Luca' , 'Verdi'), ('Giacomo' , 'Bruni')";
        String addCourse = "INSERT INTO COURSES_TABLE(COURSE_NAME) VALUES ('Algoritmi'), ('Logica'), ('Fisica')";
        String addAssociation = "INSERT INTO ASSOCIATIONS_TABLE(TEACHER , COURSE, T_FIRST, T_LAST) VALUES (1 , 'Algoritmi' , 'Mario' , 'Rossi'), (2 , 'Algoritmi' , 'Luca' , 'Verdi') , (3 , 'Fisica' , 'Giacomo' , 'Bruni')";

        db.execSQL(users);
        db.execSQL(courses);
        db.execSQL(teachers);
        db.execSQL(associations);
        db.execSQL(reservations);
        db.execSQL(calendar);
        db.execSQL(addTeacher);
        db.execSQL(addCourse);
        db.execSQL(addAssociation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insert new User in the database
    public boolean registerUser(String UserName, String Email, String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(COLUMN_EMAIL, Email);
        cv.put(COLUMN_USER_NAME, UserName);
        cv.put(COLUMN_PASSWORD, Password);

        long insert = db.insert(USERS_TABLE, null,cv);

        db.close();

        if(insert == -1)
            return false;
        else
            return true;
    }

    //check the Login credentials for an user
    public User logIn(String UserName, String Password){
        User user;

        String queryString = "SELECT " + COLUMN_USER_ID + " , " + COLUMN_EMAIL + " , " + COLUMN_USER_NAME + " FROM " + USERS_TABLE + " WHERE " + COLUMN_EMAIL + " = " + "'" + UserName + "'" + " AND " + COLUMN_PASSWORD + " = " + "'" + Password + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString , null);

        if(cursor.moveToFirst()){
            int userId = cursor.getInt(0);
            String userEmail = cursor.getString(1);
            String userName = cursor.getString(2);

            user = new User(userId , userName , userEmail);

        }else{
            user = null;
        }

        cursor.close();
        db.close();

        return user;
    }

    //add a course to the list
    public void addCourse(String course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(COURSE_NAME, course);

        db.insert(COURSES_TABLE, null,cv);

        db.close();
    }

    //get the courses
    public ArrayList<String> getCourses(){
        ArrayList<String> courses = new ArrayList<>();

        String queryString = "SELECT " + COURSE_NAME + " FROM " + COURSES_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString , null);

        if(cursor.moveToFirst()){
            do{
                String courseName = cursor.getString(0);
                courses.add(courseName);
            }while(cursor.moveToNext());
        }else{
            //do nothing
        }

        cursor.close();
        db.close();
        return courses;
    }

    public void addTeacher(String first_name, String last_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(FIRST_NAME, first_name);
        cv.put(LAST_NAME, last_name);

        db.insert(TEACHERS_TABLE, null,cv);

        db.close();
    }

    public ArrayList<Teacher> getTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();

        String queryString = "SELECT * FROM " + TEACHERS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString , null);

        if(cursor.moveToFirst()){
            do{
                int teacherId = cursor.getInt(0);
                String teacherFirst = cursor.getString(1);
                String teacherLast = cursor.getString(2);
                teachers.add(new Teacher(teacherFirst , teacherLast, teacherId));
            }while(cursor.moveToNext());
        }else{
            //do nothing
        }

        cursor.close();
        db.close();
        return teachers;
    }

    public void addAssociation(int teacherId , String Course, String First, String Last){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(TEACHER, teacherId);
        cv.put(COURSE, Course);
        cv.put(T_FIRST, First);
        cv.put(T_LAST, Last);

        db.insert(ASSOCIATIONS_TABLE, null,cv);

        db.close();
    }

    public ArrayList<Association> getAssociations(String course){
        ArrayList<Association> associations = new ArrayList<>();

        String queryString = "SELECT * FROM " + ASSOCIATIONS_TABLE + " WHERE " + COURSE + " = " + "'" + course + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString , null);

        if(cursor.moveToFirst()){
            do{
                int teacherId = cursor.getInt(0);
                String courseName = cursor.getString(1);
                String teacherFirst = cursor.getString(2);
                String teacherLast = cursor.getString(3);
                associations.add(new Association(teacherId , courseName , teacherFirst,teacherLast));
            }while(cursor.moveToNext());
        }else{
            //do nothing
        }
        return associations;
    }

    public void addReservation(Calendar res){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(TEACHER, res.getTeachersId());
        cv.put(COURSE, res.getCourseName());
        cv.put(DATE, res.getDate());
        cv.put(HOUR, res.getHour());
        cv.put("USER", res.getUserId());
        cv.put(STATUS, "active");

        db.insert(RESERVATIONS_TABLE, null,cv);
        db.close();
    }

    public ArrayList<Reservation> getReservations(Integer user){
        ArrayList<Reservation> reservations = new ArrayList<>();

        String queryString = "SELECT * FROM " + RESERVATIONS_TABLE + " WHERE USER = " + "'" + user + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString , null);

        if(cursor.moveToFirst()){
            do{
                int resId = cursor.getInt(0);
                int userId = cursor.getInt(1);
                int teacherId = cursor.getInt(2);
                String courseName = cursor.getString(3);
                String day = cursor.getString(4);
                String hour = cursor.getString(5);
                String status = cursor.getString(6);
                reservations.add(new Reservation(day, hour, teacherId, courseName, userId,status,resId));
            }while(cursor.moveToNext());
        }else{
            //do nothing
        }
        return reservations;
    }

    public ArrayList<Reservation> getActiveReservations(Integer user){
        ArrayList<Reservation> reservations = new ArrayList<>();

        String queryString = "SELECT * FROM " + RESERVATIONS_TABLE + " WHERE USER = " + "'" + user + "'" + " AND " + STATUS + " = " + "'active'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString , null);

        if(cursor.moveToFirst()){
            do{
                int resId= cursor.getInt(0);
                int userId = cursor.getInt(1);
                int teacherId = cursor.getInt(2);
                String courseName = cursor.getString(3);
                String day = cursor.getString(4);
                String hour = cursor.getString(5);
                String status = cursor.getString(6);
                reservations.add(new Reservation(day, hour, teacherId, courseName, userId,status, resId));
            }while(cursor.moveToNext());
        }else{
            //do nothing
        }
        return reservations;
    }

    //TODO
    public void deleteReservation(Reservation res){
        SQLiteDatabase db = this.getWritableDatabase();

        String setCancel = "UPDATE " + RESERVATIONS_TABLE + " SET " + STATUS + " = " + "'cancelled'" + " WHERE " + RESERV_ID + " = " + "'" + res.getResId() + "'";
        String deleteRow = "DELETE FROM " + CALENDAR_TABLE + " WHERE USER = " + "'" + res.getUserId() + "'" + " AND TEACHER = " + "'" + res.getTeachersId() + "'" + " AND COURSE = " + "'" + res.getCourseName() + "'" + " AND DATE = " + "'" + res.getDate() + "'" + " AND HOUR = " + "'" + res.getHour() + "'";

        db.execSQL(setCancel);
        db.execSQL(deleteRow);
        db.close();
    }

    public void addCalendar(Calendar res){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(TEACHER, res.getTeachersId());
        cv.put(COURSE, res.getCourseName());
        cv.put(DATE, res.getDate());
        cv.put(HOUR, res.getHour());
        cv.put("USER", res.getUserId());

        db.insert(CALENDAR_TABLE, null,cv);
        db.close();
    }

    public ArrayList<Calendar> getCalendar(String course, String date){
        ArrayList<Calendar> reservations = new ArrayList<>();

        String queryString = "SELECT * FROM " + CALENDAR_TABLE + " WHERE " + COURSE + " = " + "'" + course + "'" + " AND " + DATE + " = " + "'" + date + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString , null);

        if(cursor.moveToFirst()){
            do{
                int userId = cursor.getInt(1);
                int teacherId = cursor.getInt(2);
                String courseName = cursor.getString(3);
                String day = cursor.getString(4);
                String hour = cursor.getString(5);
                reservations.add(new Calendar(day, hour, teacherId, courseName, userId));
            }while(cursor.moveToNext());
        }else{
            //do nothing
        }
        return reservations;
    }


}
