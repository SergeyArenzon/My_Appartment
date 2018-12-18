package com.myappartment.my_appartment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email,pass;
    ProgressDialog dialog;

   public void logIn( EditText email, EditText pass){
       dialog.setMessage("Please wait...");
       dialog.show();
       mAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               dialog.hide();

               if(task.isSuccessful()){

                   Intent afterLgn = new Intent(MainActivity.this,requestsList.class);
                   startActivity(afterLgn);
               }else{
                 //  dialog.hide();
                   Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
               }

           }
       });


   }

    //Intent goToRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // goToRequests = new Intent(MainActivity.this,requestsList.class);
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        email = (EditText)findViewById(R.id.userLgn);
        pass = (EditText)findViewById(R.id.passLgn);

        ///LOGIN BUTTON///


        Button lgnBtn = (Button) findViewById(R.id.loginBtn);
        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                v.startAnimation(buttonClick);
            logIn(email,pass);






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
