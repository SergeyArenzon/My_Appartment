package com.myappartment.my_appartment;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myappartment.my_appartment.Objects.User;
import com.myappartment.my_appartment.Requests.requestsList;
import com.myappartment.my_appartment.payments.Monthpayment;

public class Home extends Application {

    public static boolean flag=false;

    @Override
    public void onCreate() {


        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        super.onCreate();




        if(user!=null){
            mDatabase
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public  void onDataChange(DataSnapshot dataSnapshot) {


                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                User dbuser=new User();
                                dbuser=snapshot.getValue(User.class);

                                if( dbuser.getEmail().equals(user.getEmail()))
                                {

                                    String s =String.valueOf(dbuser.isManager()) ;
                                    Status.email=dbuser.getEmail();
                                    Status.name=dbuser.getName();
                                    Status.dira=dbuser.getDira();
                                    Status.manager=dbuser.isManager();







                                }
                                Intent goRentin = new Intent(Home.this,requestsList.class);
                                goRentin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                System.out.println("++++++++++++++++++"+Status.getEmail()+"++++++++++++++++++");
                                startActivity(goRentin);

                            }


                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Status.manager=false;
                        }
                    });






            {





            }






            }
    }
}
