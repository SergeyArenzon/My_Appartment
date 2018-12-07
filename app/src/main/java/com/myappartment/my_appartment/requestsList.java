package com.myappartment.my_appartment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class requestsList extends AppCompatActivity {

    LinearLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);
        ///CREATE DYNAMIC BUTTONS///
        Button btn;
        String [] btns ={"btn1","btn2","btn3"};
        int requestSize=3;
        parent = (LinearLayout)findViewById(R.id.ll_parent);
        for(int i=0; i<requestSize;i++){
            btn = new Button(requestsList.this);
            btn.setId(i+1);
            btn.setText(btns[i]);
            btn.setTag(i);
            parent.addView(btn);

        }







    }
    public void main(String[] args){



    }
}
