package com.myappartment.my_appartment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


        Button lgnBtn = (Button) findViewById(R.id.loginBtn);
        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                v.startAnimation(buttonClick);



                DatabaseReference myRef = database.getReference();
               // myRef.setValue(user);


            }


        });


        ///REGISTER BUTTON///
        Button regBtn = (Button) findViewById(R.id.regBtn);
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
