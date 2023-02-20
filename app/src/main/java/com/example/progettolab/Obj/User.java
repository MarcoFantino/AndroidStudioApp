package com.example.progettolab.Obj;

public class User{
    private String UserName;
    private String Email;
    private int Id;

    public User(int id, String userName, String email) {
        Id = id;
        UserName = userName;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return Email;
    }

}
