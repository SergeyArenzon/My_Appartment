package com.myappartment.my_appartment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myappartment.my_appartment.maincode.Pages;
import com.myappartment.my_appartment.maincode.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    //Intent goToRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {





        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // goToRequests = new Intent(MainActivity.this,requestsList.class);
         final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


            ///LOGIN BUTTON///


        Button lgnBtn = (Button)findViewById(R.id.loginBtn);
        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                v.startAnimation(buttonClick);
                User user = new User("sergey","124","aren",true);


                DatabaseReference myRef = database.getReference();
                myRef.setValue(user);


         }



        });


        ///REGISTER BUTTON///
        Button regBtn = (Button)findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);









                //AFTER "REGISTER" BTN CLICK//
                //CHOOSE RENTING/RENTER///
                String[] option = {"Renter", "Renting"};
                AlertDialog.Builder builder = new AlertDialog.Builder(com.myappartment.my_appartment.MainActivity.this);
                builder.setTitle("Who are you?");
                
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on "who are you?"

                        if (which == 0) {




                            Intent renterReg = new Intent(com.myappartment.my_appartment.MainActivity.this, renterReg.class);
                            startActivity(renterReg);
                        } else {



                           Intent rentingReg = new Intent(com.myappartment.my_appartment.MainActivity.this, rentingReg.class);
                           startActivity(rentingReg);

                        }

                    }
                });
                builder.show();


            }
        });


    }
}
