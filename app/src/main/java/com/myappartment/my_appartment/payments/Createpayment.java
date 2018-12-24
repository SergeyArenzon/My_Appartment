package com.myappartment.my_appartment.payments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myappartment.my_appartment.MainActivity;
import com.myappartment.my_appartment.Objects.Payment;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Requests.requestsList;
import com.myappartment.my_appartment.Status;

public class Createpayment extends AppCompatActivity {
    EditText payment;
    Button create;
    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpayment);
        payment = (EditText) findViewById(R.id.editText2);

       create = (Button) findViewById(R.id.reqCreate);


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
