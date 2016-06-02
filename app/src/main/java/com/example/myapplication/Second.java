package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Second extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        findview();
    }

    public void findview(){
        Button btn_start = (Button)findViewById(R.id.btn_second_start);
        final TextView cal_second = (TextView)findViewById(R.id.cal_second);
        final TextView tv_time =  (TextView)findViewById(R.id.tv_time_second);

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
                        cal_second.setText("~~~~~~~ cal");
                    }
                }.start();

            }
        });

    }
}
