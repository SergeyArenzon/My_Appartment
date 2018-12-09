package com.myappartment.my_appartment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static boolean permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            ///LOGIN BUTTON///


        Button lgnBtn = (Button)findViewById(R.id.loginBtn);
        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ///(IF USER IS RENTER)///
                Intent goToRequests = new Intent(MainActivity.this,requestsList.class);
                startActivity(goToRequests);

                ///(IF USER IS RENTING)///







            }
        });


        ///REGISTER BUTTON///
        Button regBtn = (Button)findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
