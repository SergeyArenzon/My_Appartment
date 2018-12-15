package com.myappartment.my_appartment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class rentingList extends AppCompatActivity {
    LinearLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renting_list);


        ///CREATING DYNAMIC BUTTONS///
        Button btn;

        String[] btns = {"btn1", "btn2", "btn3"};
        final int requestSize = 20;
        parent = (LinearLayout) findViewById(R.id.reqList);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, 5, 0, 0);

        for (int i = 0; i < requestSize; i++) {

            btn = new Button(rentingList.this);
            btn.setBackgroundResource(R.drawable.mybtn);
            btn.setClickable(true);
            btn.setId(i + 1);

            btn.setText("btns" + i);
            btn.setTag(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToReq = new Intent(rentingList.this, renterRrequest.class);
                    startActivity(goToReq);
                }
            });
            parent.addView(btn, params);


        }


    }
}
