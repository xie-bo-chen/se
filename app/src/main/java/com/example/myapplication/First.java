package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class First extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firsy_layout);
        findview();
    }

    public void findview(){
        Button btn_start = (Button)findViewById(R.id.btn_first_start);
        final TextView distance_record = (TextView)findViewById(R.id.distance_first);
        final TextView tv_time =  (TextView)findViewById(R.id.tv_time_first);

        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new CountDownTimer(10000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        tv_time.setText("剩餘時間："+millisUntilFinished/1000+" 秒");
                    }

                    @Override
                    public void onFinish() {
                        tv_time.setText("計時結束。");
                        distance_record.setText("~~~~~~~ km");
                    }
                }.start();

            }
        });

    }
}
