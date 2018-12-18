package com.myappartment.my_appartment;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AlphaAnimation;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class renterRrequest extends AppCompatActivity {
    Toolbar toolbar;

    SeekBar sBar;
    TextView money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_rrequest);
        ProgressBar pro = (ProgressBar)findViewById(R.id.progressBar);
        pro.setProgress(90);
         final SeekBar sBar =(SeekBar)findViewById(R.id.seekBar);
         final TextView money = (TextView)findViewById(R.id.dyAmount);


        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sBar.setMax(40);
                money.setText("Your Payment: "+progress+"/"+sBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(renterRrequest.this,"Pick Your Amount",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(renterRrequest.this,"Your Payment Is "+sBar.getProgress()+"?",Toast.LENGTH_LONG).show();
            }
        });
}}
