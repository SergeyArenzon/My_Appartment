package com.myappartment.my_appartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class renterReg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_reg);

        Button cnclBtn = (Button) findViewById(R.id.renterRegCancle);
        Button cfmBtn = (Button) findViewById(R.id.renterRegConfirm);


                    ///CANCLE BUTTON///
        cnclBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                Intent goBack = new Intent(renterReg.this,MainActivity.class);
                startActivity(goBack);
            }
        });

                ///////////////////////////


                    ///CONFIRM BUTTON///
        cfmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                ///(if user registration veryfied)///

                Intent goRequests = new Intent(renterReg.this,requestsList.class);
                startActivity(goRequests);


            }
        });







    }
}
