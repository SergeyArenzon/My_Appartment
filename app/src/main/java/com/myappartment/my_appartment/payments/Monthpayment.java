package com.myappartment.my_appartment.payments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
<<<<<<< HEAD
=======
import com.myappartment.my_appartment.Home;
>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
import com.myappartment.my_appartment.Objects.Payment;
import com.myappartment.my_appartment.Objects.User;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Requests.requestsList;
import com.myappartment.my_appartment.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class Monthpayment extends AppCompatActivity {
<<<<<<< HEAD
=======

>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Monthpayment.this,requestsList.class);
        startActivity(intent);
    }

    public static HashMap<String, Integer> monthpeople = new HashMap<String, Integer>();
    public static ArrayList<String> subjects=new ArrayList<>();
    public static int global_amount;
    public String current_req;
    public int counter=0;
    public int counterinsdieread=0;
    public static boolean firsttime=true;
    public  boolean buttnotready=true;

    LinearLayout parent;
    LinearLayout.LayoutParams params;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


<<<<<<< HEAD
=======


>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month);
        parent = (LinearLayout) findViewById(R.id.ll_parent);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        ///---------------

        counter=0;
        global_amount=0;
        current_req=subjects.get(0);
        for (String g : subjects)
        {

            Log.d("which now:","start insidepaym:"+g);

            run_inside_payment();
            counter++;
        }



    }
    public Monthpayment()
    {

    }

    public void run_inside_payment()
    {
        current_req = subjects.get(counter);
<<<<<<< HEAD
=======

>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Payment/"+Status.dira+"/"+current_req);
        mDatabase
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public  void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            if(firsttime) {

                                Payment p = snapshot.getValue(Payment.class);
                                String name = p.getName();
                                Log.d("which now:", "start insidepaym:" + name);
                                int currentpayment = p.paid;
                                int untilnow_payment = monthpeople.get(name);
                                currentpayment = currentpayment + untilnow_payment;
                                monthpeople.put(name, currentpayment);

                                global_amount = global_amount + p.paid;
                            }
                            else{
                                Payment p = snapshot.getValue(Payment.class);
<<<<<<< HEAD

                                global_amount = global_amount +p.paid ;
                            }

                        }
=======
                                global_amount = global_amount +p.paid +monthpeople.get(p.name);
                            }

                        }

>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
                       //////-----------------------------
                        counterinsdieread++;
                        Log.d("which now:","subjectsize:"+subjects.size()+"counterinread:"+counterinsdieread);
                        if((counterinsdieread==subjects.size())&buttnotready) {

                            for (String name : Monthpayment.monthpeople.keySet()) {
                                Log.d("which nowgggggggg:", name);
                                double a = Monthpayment.global_amount;
                                double b = Monthpayment.monthpeople.get(name);
                                double pre = 100 * (b / a);

                                btn = new Button(Monthpayment.this);

                                btn.setBackgroundResource(R.drawable.mybtn);
                                String pre2="";
                                if(String.valueOf(pre).length()>4)
                                {
                                    pre2=String.valueOf(pre);
                                    pre2=pre2.substring(0,3);
                                }
                                else
                                {
                                    pre2=String.valueOf(pre);
                                    pre2=pre2.substring(0,2);
                                }

                                btn.setText(name + ",paid:" + b + " ," + pre2+"%");

                                params.setMargins(0, 20, 0, 20);
<<<<<<< HEAD
=======

>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
                                parent.addView(btn, params);

                            }
                            buttnotready=false;
<<<<<<< HEAD
                            firsttime=true;
=======
                            firsttime=false;
>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
                        }
                        return;



<<<<<<< HEAD
=======

>>>>>>> d7a385687d4a86f3ed59d677db2ce72f75e6387c
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
