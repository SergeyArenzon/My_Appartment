package com.myappartment.my_appartment.payments;

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
import com.myappartment.my_appartment.Objects.Payment;
import com.myappartment.my_appartment.Objects.User;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class Monthpayment extends AppCompatActivity {

    public static HashMap<String, Integer> monthpeople = new HashMap<String, Integer>();
    public static ArrayList<String> subjects=new ArrayList<>();
    public static int global_amount;
    public String current_req;
    public int counter=0;
    public static boolean firsttime=true;

    LinearLayout parent;
    LinearLayout.LayoutParams params;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


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

        }



    }
    public Monthpayment()
    {

    }

    public void run_inside_payment()
    {

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Payment/"+Status.dira+"/"+current_req);
        mDatabase
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public  void onDataChange(DataSnapshot dataSnapshot) {

                        current_req = subjects.get(counter++);
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            if(firsttime) {

                                Payment p = snapshot.getValue(Payment.class);
                                String name = p.getName();
                                Log.d("which now:", "start insidepaym:" + name);
                                int currentpayment = p.paid;
                                int untilnow_payment = monthpeople.get(name);
                                currentpayment = currentpayment + untilnow_payment;
                                monthpeople.put(name, currentpayment);

                                global_amount = global_amount + currentpayment;
                            }
                            else{
                                Payment p = snapshot.getValue(Payment.class);
                                global_amount = global_amount +p.paid +monthpeople.get(p.name);
                            }

                        }
                       //////-----------------------------
                        Log.d("which now:","current:"+current_req+"sublast:"+subjects.get(subjects.size()-1));
                        if(current_req.equals(subjects.get(subjects.size()-1))) {

                            for (String name : Monthpayment.monthpeople.keySet()) {
                                Log.d("which nowgggggggg:", name);
                                double a = Monthpayment.global_amount;
                                double b = Monthpayment.monthpeople.get(name);
                                double pre = 100 * (b / a);

                                btn = new Button(Monthpayment.this);

                                btn.setBackgroundResource(R.drawable.background_btn2);
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
                                parent.addView(btn, params);

                            }
                            firsttime=false;
                        }
                        return;



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
