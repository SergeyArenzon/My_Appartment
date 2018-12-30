package com.myappartment.my_appartment.Requests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myappartment.my_appartment.Objects.Payment;
import com.myappartment.my_appartment.Objects.reqnode;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Status;
import com.myappartment.my_appartment.payments.Createpayment;
import com.myappartment.my_appartment.payments.Showpayments;

import java.util.concurrent.TimeUnit;

public class createRequest extends AppCompatActivity {
    EditText title, price;
    Button create, cancel;

    public void onBackPressed() {
        super.onBackPressed();
        Intent goMain = new Intent(createRequest.this,requestsList.class);
        startActivity(goMain);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        title = (EditText) findViewById(R.id.editText);
        price = (EditText) findViewById(R.id.editText2);
         create=(Button)findViewById(R.id.reqCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                createRequest cr = new createRequest();
                cr.createreq(title.getText().toString(), price.getText().toString());
                Intent gorequests = new Intent(createRequest.this,requestsList.class);
                startActivity(gorequests);

            }


        });

        cancel = (Button)findViewById(R.id.reqCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goList = new Intent(createRequest.this,requestsList.class);
                startActivity(goList);

            }
        });




    }


    public createRequest()
    {}
    public  void createreq(String title,String price) {
        //---------here you need to check if price contain numbers only

        reqnode req = new reqnode(title, Integer.valueOf(price), Status.dira);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Req");
        db.child(Status.dira).child(title).setValue(req);

    }


}


