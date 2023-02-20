package com.example.progettolab.Obj;

import org.json.JSONException;
import org.json.JSONObject;

public class Teacher {

    private String firstName;

    private String lastName;

    private int Id;

    public Teacher(String name , String last, int id) {
        firstName = name;
        lastName = last;
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }



    public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("First Name", getFirstName());
            jsonObject.put("Last Name", getLastName());
            jsonObject.put("TeacherId", getId());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}
