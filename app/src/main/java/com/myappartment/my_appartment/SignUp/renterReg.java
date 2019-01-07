package com.myappartment.my_appartment.SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myappartment.my_appartment.MainActivity;
import com.myappartment.my_appartment.Objects.User;
import com.myappartment.my_appartment.R;

public class renterReg extends AppCompatActivity {
    ProgressDialog dialog;
    private FirebaseAuth mAuth;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    EditText email,pass,name,appName;


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
        appName = (EditText)findViewById(R.id.appName);
        dialog = new ProgressDialog(renterReg.this);

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


        ///CONFIRM BUTTON//
        cfmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean d=MainActivity.manager;

               final User user = new User(email,pass,name,d,appName);
                v.startAnimation(buttonClick);
                /////////////////REGISTRATION EMPTY FIELDS EXCEPTION FIX////////////////
                if(email.getText().toString().matches("")||pass.getText().toString().matches("")||name.getText().toString().matches("")
                ||appName.getText().toString().matches("")){
                    email =(EditText)findViewById(R.id.renterRegUser);
                    pass =(EditText)findViewById(R.id.renterRegPass);
                    name =(EditText)findViewById(R.id.renterRegName);
                    appName = (EditText)findViewById(R.id.appName);
                   Toast.makeText(renterReg.this,"ONE OR MORE FIELDS IS EMPTY",Toast.LENGTH_SHORT).show();
                }else{
                    dialog.setMessage("Please wait...");
                    dialog.show();
                ////////////////////////////////////////////////////////////////////
                mAuth.createUserWithEmailAndPassword(user.getEmail(), pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                ///CREATED NEW USER SUCCESSFULY///

                                if (task.isSuccessful()) {



                                    db.child("Users").child(user.getEmail().replace(".", "|")).setValue(user);
                                    dialog.hide();
                                    Toast.makeText(renterReg.this, "Registered sucsessfull", Toast.LENGTH_LONG).show();

                                    Intent login = new Intent(renterReg.this, MainActivity.class);
                                    startActivity(login);

                                }
                                //End-if isSuccessful


                                ///EMAIL ALREADY EXIST///
                                else {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthUserCollisionException existEmail) {
                                        dialog.hide();
                                        Log.d(user.getEmail(), "Email already exist");
                                        Toast.makeText(renterReg.this, "Email already exist", Toast.LENGTH_LONG).show();
                                    } catch (Exception e) {
                                        Log.d(user.getEmail(), "ssss" + e.getMessage());

                                    }
                                } //End else
                            }
                        });}

            }
        });







    }
}