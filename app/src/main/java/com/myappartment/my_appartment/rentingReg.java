package com.myappartment.my_appartment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;

public class rentingReg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renting_reg);
    }
}
