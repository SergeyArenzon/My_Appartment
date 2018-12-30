package com.myappartment.my_appartment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myappartment.my_appartment.Objects.User;
import com.myappartment.my_appartment.Requests.requestsList;
import com.myappartment.my_appartment.SignUp.renterReg;

public class MainActivity extends AppCompatActivity {
    
    public static boolean manager ;
    private FirebaseAuth mAuth;
    EditText email,pass;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
    int check=1;


    public void checkIfManager(){

        mDatabase
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public  void onDataChange(DataSnapshot dataSnapshot) {
                        //int size = (int)dataSnapshot.getChildrenCount();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                             User usr=new User();
                             usr=snapshot.getValue(User.class);

                            if( usr.getEmail().equals(email.getText().toString()))
                            {

                                    String s =String.valueOf(usr.isManager()) ;
                               // Log.d("emmmmmmail:"+s,"");
                                Status.email=usr.getEmail();
                                Status.name=usr.getName();
                                Status.dira=usr.getDira();
                                 Status.manager=usr.isManager();

                                  break;

                            }

                        }


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                       // Status.manager=false;
                    }
                });




    }
  public void movepage()
  {

      check=0;


      if(Status.manager)
      {
          System.out.println("THIS IS MANGER");
         // Toast.makeText(getApplicationContext(),"YOU ARE MANAGER",Toast.LENGTH_SHORT).show();

                       Intent goManager = new Intent(MainActivity.this,requestsList.class);
                       startActivity(goManager);

      }
      else{
         // Toast.makeText(getApplicationContext(),"YOU ARE NOT MANAGER",Toast.LENGTH_SHORT).show();
          // Log.d("status:",String.valueOf(Status.manager));
          Intent goRentin = new Intent(MainActivity.this,requestsList.class);
          startActivity(goRentin);

      }
  }

   public void logIn( EditText email, EditText pass){


       checkIfManager();

         mAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {



               if(task.isSuccessful())
               {
                   movepage();
               }

               else
                   {
                       Toast.makeText(getApplicationContext(),"ERROR LOGIN",Toast.LENGTH_SHORT).show();


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
                String[] option = {"Manager", "Regular"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Who are you?");

                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on "who are you?"

                        if (which == 0) {

                            manager =true;
                            Intent renterReg = new Intent(MainActivity.this, renterReg.class);
                            startActivity(renterReg);
                        } else {

                            manager =false;
                            Intent renterReg = new Intent(MainActivity.this, renterReg.class);
                            startActivity(renterReg);

                        }

                    }
                });


                builder.show();


            }
        });


    }
}
