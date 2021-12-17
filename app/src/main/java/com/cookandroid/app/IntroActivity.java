package com.cookandroid.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;



public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);
        setTitle("오늘 뭐 먹지?");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000); // Handler 객체의 postDelayerd메소드를 이용한 인트로화면 구성 로직
                           // 2초 후 인텐트를 통해 현재 액티비티 종료후 MainActivity로 넘어감



    }
}