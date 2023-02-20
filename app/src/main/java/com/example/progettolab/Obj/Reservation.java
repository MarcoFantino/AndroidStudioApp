package com.example.progettolab.Obj;

public class Reservation extends Calendar{
    private String status;
    private int resId;

    public Reservation(String date, String hour, int teachersId, String courseName, int userId, String status, int resId) {
        super(date, hour, teachersId, courseName, userId);
        this.status = status;
        this.resId = resId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusCancel() {
        this.status = "cancelled";
    }

    public void setStatusComplete(){
        this.status = "completed";
    }

    public int getResId() {
        return resId;
    }


}
