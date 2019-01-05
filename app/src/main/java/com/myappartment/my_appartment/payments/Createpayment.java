package com.myappartment.my_appartment.payments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myappartment.my_appartment.MainActivity;
import com.myappartment.my_appartment.Objects.Payment;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Requests.requestsList;
import com.myappartment.my_appartment.Status;
import com.tomer.fadingtextview.FadingTextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class Createpayment extends AppCompatActivity {
    EditText payment;
    Button create;
    Animation anim;
    TextView text5;
    private FadingTextView fade;
private static int WELCOME_TIMEOUT = 4000;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goShowPay = new Intent(Createpayment.this,Showpayments.class);
        startActivity(goShowPay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
  //  paidAnim = (TextView)findViewById(R.id.sergey);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpayment);
        payment = (EditText) findViewById(R.id.editText2);
       // fade =findViewById(R.id.ser);
       create = (Button) findViewById(R.id.reqCreate);
//text5=(TextView)findViewById(R.id.textView5);
        anim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.text);



        create.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {
                    Integer.parseInt( payment.getText().toString() );
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("Payment");
                    //---------here you need to check if payment contain numbers only
                    Payment p=new Payment(Integer.valueOf(payment.getText().toString()),Status.name);


                    db.child(Status.dira).child(Status.title).child(Status.name).setValue(p);







                    Intent gopayments = new Intent(Createpayment.this,Showpayments.class);
                    startActivity(gopayments);
                }
                catch( Exception e ) {
                    Toast.makeText(Createpayment.this
                    ,"PRICE INPUT MUST BE NUMERIC",Toast.LENGTH_SHORT).show();
                }


            }


        });




    }





}
