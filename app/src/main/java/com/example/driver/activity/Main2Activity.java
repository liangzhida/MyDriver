package com.example.driver.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driver.MainActivity;
import com.example.driver.R;

public class Main2Activity extends AppCompatActivity {


    private ImageView imageView;
    private TextView tvdaojishi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);

                Main2Activity.this.finish();
            }
        }, 1000 * 3);
        CountDownTimer timer=new CountDownTimer(4*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            tvdaojishi.setText("  "+millisUntilFinished/1000+"|倒计时  ");
            tvdaojishi.setBackground(getResources().getDrawable(R.drawable.bgstart));
            }

            @Override
            public void onFinish() {

            }
        }.start();
        }


    private void initView() {

        imageView = (ImageView) findViewById(R.id.imageView);

        tvdaojishi = (TextView) findViewById(R.id.tvdaojishi);

    }
}
