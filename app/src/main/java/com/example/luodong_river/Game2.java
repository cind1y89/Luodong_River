package com.example.luodong_river;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game2 extends GlobalCode {

    Button button_go;
    View layout;
    TextView textView_text,textView_timer,textView_q;
    ImageView imageView_q,imageView_a1,imageView_a2,imageView_a3,imageView_menu;
    GlobalVariable globalVariable_mode;
    int i=0;
    int time=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        //開發者模式菜單
        imageView_menu=findViewById(R.id.imageView_menu);
        globalVariable_mode= (GlobalVariable) getApplicationContext();
        if(globalVariable_mode.getMode().equals("developer")){
            imageView_menu.setVisibility(View.VISIBLE);
        }else{
            imageView_menu.setVisibility(View.GONE);
        }
        imageView_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Game2.this,DeveloperMode.class);
                startActivity(intent);
                finish();
            }
        });




        button_go=findViewById(R.id.button_G2go);
        layout=findViewById(R.id.layout_G2);
        textView_text=findViewById(R.id.textView_G2text);
        textView_timer=findViewById(R.id.textView_G2timer);
        textView_q=findViewById(R.id.textView_G2q);
        imageView_q=findViewById(R.id.imageView_G2q);
        imageView_a1=findViewById(R.id.imageView_G2a1);
        imageView_a2=findViewById(R.id.imageView_G2a2);
        imageView_a3=findViewById(R.id.imageView_G2a3);

        //button
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    i++;
                    layout.setBackgroundResource(R.drawable.game2b2);
                    button_go.setBackgroundResource(R.drawable.button_start);

                }else if(i==1){
                    i++;
                    // TODO: 2022/5/3 遊戲背景
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    button_go.setVisibility(View.GONE);

                    // 3sec預備倒數
                    textView_text.setVisibility(View.VISIBLE);
                    new CountDownTimer(3000, 1000) {
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
                    Intent intent = new Intent(Game2.this,Game3.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void game() {
        textView_timer.setVisibility(View.VISIBLE);

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
            }
        }.start();

        // TODO: 2022/5/4 題目切換
        textView_q.setVisibility(View.VISIBLE);
        textView_q.setText("請問大山羊吃甚麼?");

        imageView_q.setVisibility(View.VISIBLE);
        imageView_a1.setVisibility(View.VISIBLE);
        imageView_a2.setVisibility(View.VISIBLE);
        imageView_a3.setVisibility(View.VISIBLE);

        imageView_a1.setOnTouchListener(touchListener);
        imageView_a2.setOnTouchListener(touchListener);
        imageView_a3.setOnTouchListener(touchListener);

    }

    //圖片移動
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        private float x, y;  // 原本圖片存在的 X,Y軸位置
        private float mx, my;  // 圖片被拖曳的 X,Y軸距離長度

        @Override public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    x = event.getX();                  //觸控的X軸位置
                    y = event.getY();                  //觸控的Y軸位置
                    image_pos(imageView_q);            //題目位置
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    mx = x-view.getX();
                    my = y-view.getY();
                    view.setX(event.getX()-mx);
                    view.setY(event.getY()-my);
                    Log.d("Test","view.setX "+(event.getX()-mx));
                    Log.d("Test","view.setY "+(event.getY()-my));

                    if(((right-left)/4-100)<=(event.getX()-mx) && (event.getX()-mx)<=((right-left)/4+100)){
                        if(((button-top)/3-100)<=(event.getY()-my) && (event.getY()-my)<=((button-top)/3+100)){
                            // TODO: 2022/5/4 錯誤答案判定
                            if(view==imageView_a1){
                                view.setVisibility(View.GONE);
                                Log.d("Test","yes");

                                Toast toast =Toast.makeText(Game2.this,"恭喜，你答對了",Toast.LENGTH_SHORT);
                                toast.show();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);

                            }else{
                                view.setVisibility(View.GONE);
                                Log.d("Test","no");
                                Toast toast =Toast.makeText(Game2.this,"燈等，再注意看看",Toast.LENGTH_SHORT);
                                toast.show();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);
                            }
                        }
                    }
                    break;
                }
            }
            return true;
        }
    };

    //題目位置
    int top,button,left,right;
    private void image_pos(ImageView imageView) {
        top=imageView.getTop();
        button=imageView.getBottom();
        left=imageView.getLeft();
        right=imageView.getRight();
        Log.d("Test","getLeft "+imageView.getLeft());
        Log.d("Test","getRight "+imageView.getRight());
        Log.d("Test","getTop "+imageView.getTop());
        Log.d("Test","getBottom "+imageView.getBottom());
    }




}
