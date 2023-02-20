package com.example.progettolab.Obj;

public class Association{



    private int teachersId;
    private String courseName;
    private String teacherFirst;
    private String teacherLast;

    public Association(int teachersId, String courseName, String First, String Last) {
        this.teachersId = teachersId;
        this.courseName = courseName;
        this.teacherFirst = First;
        this.teacherLast = Last;
    }

    public int getTeachersId() {
        return teachersId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherFirst() {
        return teacherFirst;
    }

    public String getTeacherLast() {
        return teacherLast;
    }
}
