package com.myappartment.my_appartment.payments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myappartment.my_appartment.MainActivity;
import com.myappartment.my_appartment.Objects.Payment;
import com.myappartment.my_appartment.Objects.reqnode;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Requests.requestsList;
import com.myappartment.my_appartment.SignUp.renterReg;
import com.myappartment.my_appartment.Status;

import java.util.ArrayList;

public class Showpayments extends AppCompatActivity {

    public void onBackPressed() {
        super.onBackPressed();
        Intent goMain = new Intent(Showpayments.this,requestsList.class);
        startActivity(goMain);
    }

    LinearLayout parent;
    TextView bigtit;
    Button paybut;
    int counter;
    public ArrayList<Payment> pays = new ArrayList<Payment>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_request);

        bigtit= (TextView) findViewById(R.id.bigtitle);
        bigtit.setText(Status.title+","+Status.price);

        paybut=(Button)findViewById(R.id.buttonpayment);
        paybut.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent gotopay = new Intent(Showpayments.this,Createpayment.class);
                startActivity(gotopay);

            }


        });


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Payment/"+Status.dira+"/"+Status.title);

        pays = new ArrayList<>();



        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //int size = (int)dataSnapshot.getChildrenCount();
                counter = 0;
                parent = (LinearLayout) findViewById(R.id.ll_parent);
                Button btn;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(0, 20, 0, 20);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Payment n = snapshot.getValue(Payment.class);

                     System.out.println("found!!!!!!!:"+snapshot.getKey());
                    pays.add(n);

                    btn = new Button(Showpayments.this);

                    btn.setBackgroundResource(R.drawable.mybtn);


                    String pre="";
                    if(String.valueOf(n.precent).length()>4)
                    {
                         pre = String.valueOf(n.precent).substring(0, 3);
                    }
                    if(String.valueOf(n.precent).length()<=4)
                    {
                         pre = String.valueOf(n.precent).substring(0, 2);
                    }
                    btn.setText( n.name + " paid:" + n.paid+"$ ,"+pre+"%");

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });
                    counter++;
                    parent.addView(btn, params);


                }
                counter = 0;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Status.manager=false;
            }
        });

        //mako.CreateReqlist();
        ///CREATE DYNAMIC BUTTONS///


    }
}
