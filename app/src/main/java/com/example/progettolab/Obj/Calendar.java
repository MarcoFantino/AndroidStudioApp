package com.example.progettolab.Obj;

import com.example.progettolab.Obj.Teacher;

import org.json.JSONException;
import org.json.JSONObject;

public class Calendar {
    private String date;
    private String hour;
    private int teachersId;
    private String courseName;
    private int userId;

    public Calendar(String date, String hour, int teachersId, String courseName, int userId) {
        this.date = date;
        this.hour = hour;
        this.teachersId = teachersId;
        this.courseName=courseName;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public int getTeachersId() {
        return teachersId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getUserId() {
        return userId;
    }

    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("date", getDate());
            jsonObject.put("hour", getHour());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

}
