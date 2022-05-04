package com.example.luodong_river;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class Game5 extends GlobalCode {

    Button button_go,button_fish,button_ok;
    View layout;
    TextView textView_text,textView_timer;
    ImageView imageView_ch;
    ProgressBar progressBar;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);

        button_go=findViewById(R.id.button_G5go);
        layout=findViewById(R.id.layout_G5);
        textView_text=findViewById(R.id.textView_G5text);
        button_fish=findViewById(R.id.button_G5fish);
        imageView_ch=findViewById(R.id.imageView_G5ch);
        progressBar=findViewById(R.id.progressBar);
        button_ok=findViewById(R.id.button_G5ok);
        textView_timer=findViewById(R.id.textView_G5timer);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    i++;
                    layout.setBackgroundResource(R.drawable.game5b2);
                    button_go.setBackgroundResource(R.drawable.button_start);

                }else if(i==1){
                    i++;
                    layout.setBackgroundResource(R.drawable.game5b3);
                    button_go.setVisibility(View.GONE);

                    // 3sec預備倒數
                    textView_text.setVisibility(View.VISIBLE);
                    new CountDownTimer(4000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            if((millisUntilFinished / 1000)==0){
                                textView_text.setText("start");
                            }else{
                                textView_text.setText(""+millisUntilFinished / 1000);
                            }
                        }
                        public void onFinish() {
                            game();
                            textView_text.setVisibility(View.GONE);
                        }
                    }.start();
                }else{
                    Intent intent = new Intent(Game5.this,Game6.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }

    private void game() {

        button_fish.setVisibility(View.VISIBLE);
        imageView_ch.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // 30秒倒數
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView_timer.setText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                textView_timer.setText("0");
                textView_text.setVisibility(View.VISIBLE);
                textView_text.setText("GAMEOVER");
                // TODO: 2022/5/3 下一關按鈕背景
                button_go.setVisibility(View.VISIBLE);
                button_fish.setVisibility(View.GONE);
            }
        }.start();

        button_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress=progressBar.getProgress();
                Log.d("progress",""+progress);
                if(progress<100){
                    int r =  new Random().nextInt(11) ;
                    progress+=r;
                    progressBar.setProgress(progress);
                }else{
                    imageView_ch.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    button_fish.setVisibility(View.GONE);
                    int r =  new Random().nextInt(4) ;
                    // TODO: 2022/5/4 分數計算 ?
                    if(r==0){
                       layout.setBackgroundResource(R.drawable.game5f1);
                    }else if(r==1){
                        layout.setBackgroundResource(R.drawable.game5f2);
                    }else if(r==2){
                        layout.setBackgroundResource(R.drawable.game5f3);
                    }else if(r==3){
                        layout.setBackgroundResource(R.drawable.game5f4);
                    }
                    button_ok.setVisibility(View.VISIBLE);
                }

            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_ok.setVisibility(View.GONE);
                progressBar.setProgress(0);
                layout.setBackgroundResource(R.drawable.game5b3);
                button_fish.setVisibility(View.VISIBLE);
                imageView_ch.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });



    }
}