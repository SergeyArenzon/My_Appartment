package com.myappartment.my_appartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class renterReg extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText email,pass,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_reg);
        Button cnclBtn = (Button) findViewById(R.id.renterRegCancle);
        Button cfmBtn = (Button) findViewById(R.id.renterRegConfirm);
        email =(EditText)findViewById(R.id.renterRegUser);
        pass =(EditText)findViewById(R.id.renterRegPass);
        name =(EditText)findViewById(R.id.renterRegName);

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
                //User user = new User(email.getText().toString(),pass.getText().toString(),name.getText().toString(),false);
                FirebaseUser User = mAuth.getCurrentUser();
               // mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPass());

                Intent goRequests = new Intent(renterReg.this,requestsList.class);
                startActivity(goRequests);


            }
        });







    }
}
