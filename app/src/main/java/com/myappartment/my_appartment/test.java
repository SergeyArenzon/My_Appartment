package com.myappartment.my_appartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Connection con = null;

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","sergey91");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            Statement st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
