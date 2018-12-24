package com.myappartment.my_appartment.Requests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myappartment.my_appartment.Objects.User;
import com.myappartment.my_appartment.Objects.reqnode;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Status;
import com.myappartment.my_appartment.payments.Monthpayment;
import com.myappartment.my_appartment.payments.Showpayments;

import java.util.ArrayList;
import java.util.HashMap;

public class requestsList extends AppCompatActivity {

    LinearLayout parent;
    Button manageradd;
    Button monthbutton;

    int counter;
    public  ArrayList<reqnode> requests=new ArrayList<reqnode>();
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);
        //------setbutton---------
        manageradd=(Button)findViewById(R.id.addreqmanager);
        manageradd.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if(Status.manager)
                {
                    Intent goToReq = new Intent(requestsList.this,createRequest.class);
                    startActivity(goToReq);
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"ONLY MANGER",Toast.LENGTH_LONG).show();
                }

            }


        });
        monthbutton=(Button)findViewById(R.id.month);
        monthbutton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                    Intent gomonth = new Intent(requestsList.this,Monthpayment.class);
                    startActivity(gomonth);

            }
        });


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Req/"+Status.dira);

        requests=new ArrayList<>();



        mDatabase.addValueEventListener(new ValueEventListener()
        {
            @Override
            public  void onDataChange(DataSnapshot dataSnapshot) {
                //int size = (int)dataSnapshot.getChildrenCount();
                  counter=0;
                parent = (LinearLayout)findViewById(R.id.ll_parent);
                Button btn;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(0, 20, 0, 20);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        reqnode n=snapshot.getValue(reqnode.class);



                    requests.add(n);

                    btn = new Button(requestsList.this);
                    btn.setBackgroundResource(R.drawable.mybtn);

                    btn.setClickable(true);
                    btn.setId(counter);

                    btn.setText("Request:"+n.getTitle()+", Cost:"+n.getPrice());
                    btn.setTag(View.ROTATION_X.toString()+View.ROTATION_Y.toString());
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Status.title=requests.get(v.getId()).getTitle();
                            Status.price=requests.get(v.getId()).getPrice();

                            Intent goToReq = new Intent(requestsList.this,Showpayments.class);
                            startActivity(goToReq);

                        }
                    });
                    counter++;
                    parent.addView(btn,params);


                }
                counter=0;
                requestsList.CreateReqlist();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Status.manager=false;
            }
        });




       create_map();
      getallpayments();




    }



    public requestsList()
    {}
    public  static void CreateReqlist()
   {


       DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Req/4");


       mDatabase.addListenerForSingleValueEvent(new ValueEventListener()
               {
                   @Override
                   public  void onDataChange(DataSnapshot dataSnapshot) {
                       //int size = (int)dataSnapshot.getChildrenCount();

                       for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                            reqnode r=snapshot.getValue(reqnode.class);

                              //Status.requests2.add(r);
                         //  System.out.println(r.getTitle());

                       }


                   }
                   @Override
                   public void onCancelled(DatabaseError databaseError) {
                       // Status.manager=false;
                   }
               });
   }

   public static void create_map()
   {
       DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
       mDatabase
               .addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public  void onDataChange(DataSnapshot dataSnapshot) {
                       ///----reset object
                       Monthpayment.monthpeople = new HashMap<String, Integer>();
                       Monthpayment.global_amount=0;
                       //-------------------
                       Log.d("which now:","start Update");
                       for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                           User usr=new User();
                           usr=snapshot.getValue(User.class);

                           if( usr.getDira().equals(Status.dira))
                           {
                               //Log.d("which now:","size:"+Monthpayment.monthpeople);
                               Monthpayment.monthpeople.put(usr.getName(),0);


                           }

                       }
                       Log.d("which now:","mapsize:"+Monthpayment.monthpeople.size());



                   }
                   @Override
                   public void onCancelled(DatabaseError databaseError) {
                       // Status.manager=false;
                   }
               });

   }

   public static void getallpayments()
   {


       DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("Payment/").child(Status.dira);
       mDatabase2
               .addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public  void onDataChange(DataSnapshot dataSnapshot) {
                       ///-----------

                       ///----------

                         Monthpayment.subjects=new ArrayList<>();
                       for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                           String req="";
                           req=snapshot.getKey();
                           Log.d("which now:","start get all payments:"+req);
                           Monthpayment.subjects.add(req);


                       }



                   }
                   @Override
                   public void onCancelled(DatabaseError databaseError) {
                       // Status.manager=false;
                   }
               });
   }
}
