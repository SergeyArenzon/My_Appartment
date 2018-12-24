package com.myappartment.my_appartment.Objects;

import android.widget.EditText;

public class User {
    private String email, name,dira;
    private boolean manager;

    public User()
    {}
    public User(EditText email, EditText pass, EditText name, boolean manager,EditText dira) {
        this.email = email.getText().toString();
        this.name = name.getText().toString();
        this.dira=dira.getText().toString();
        this.manager = manager;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public String getDira() {
        return dira;
    }

    public void setDira(String dira) {
        this.dira = dira;
    }

}
