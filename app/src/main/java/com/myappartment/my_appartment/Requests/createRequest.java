package com.myappartment.my_appartment.Requests;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.myappartment.my_appartment.Objects.Payment;
import com.myappartment.my_appartment.Objects.reqnode;
import com.myappartment.my_appartment.R;
import com.myappartment.my_appartment.Status;
import com.myappartment.my_appartment.payments.Createpayment;
import com.myappartment.my_appartment.payments.Showpayments;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class createRequest extends AppCompatActivity {
    EditText titlex, price;
    Button create, cancel;
    private RequestQueue mReq;
    private String URL ="https://fcm.googleapis.com/fcm/send";




    public void onBackPressed() {
        super.onBackPressed();
        Intent goMain = new Intent(createRequest.this,requestsList.class);
        startActivity(goMain);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        mReq = Volley.newRequestQueue(this);
        titlex = (EditText) findViewById(R.id.editText);
        price = (EditText) findViewById(R.id.editText2);
         create=(Button)findViewById(R.id.reqCreate);
        create.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {

                createRequest cr = new createRequest();
                cr.createreq(titlex.getText().toString(), price.getText().toString());

                Intent gorequests = new Intent(createRequest.this,requestsList.class);

                /////////////////



                /////////////////
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

    private void sendNotification(){

        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("to","/topics/"+"news");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","any title");
            notificationObj.put("body","any body");
            mainObj.put("notification",notificationObj);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, mainObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("////////////////////////////////////////////////////////////////////////////////////");
                }
            }


            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AIzaSyAvL_-cBUZbkF9VEs8laVTFWfu3QT16j-A");
                    return header;

                }
            };
            mReq.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}


