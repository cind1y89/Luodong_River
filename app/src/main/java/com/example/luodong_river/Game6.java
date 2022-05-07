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

import java.util.Random;

public class Game6 extends GlobalCode {

    Button button_go;
    View layout;
    TextView textView_text,textView_timer,textView_score;
    ImageView imageView_ch,imageView_t,imageView_ch2,imageView_ch3,imageView_ch4;
    ImageView imageView_menu;
    GlobalVariable globalVariable;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);

        //開發者模式菜單
        imageView_menu=findViewById(R.id.imageView_menu);
        globalVariable= (GlobalVariable) getApplicationContext();
        if(globalVariable.getMode().equals("developer")){
            imageView_menu.setVisibility(View.VISIBLE);
        }else{
            imageView_menu.setVisibility(View.GONE);
        }
        imageView_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t=globalVariable.getTotal();
                globalVariable.setTotal(t+Integer.parseInt(textView_score.getText().toString()));
                Intent intent =new Intent(Game6.this,DeveloperMode.class);
                startActivity(intent);
                finish();
            }
        });

        button_go=findViewById(R.id.button_G6go);
        layout=findViewById(R.id.layout_G6);
        textView_text=findViewById(R.id.textView_G6text);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    i++;
                    layout.setBackgroundResource(R.drawable.game6b2);
                    button_go.setBackgroundResource(R.drawable.button_start);

                }else if(i==1){
                    i++;
                    layout.setBackgroundResource(R.drawable.game6b3);
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
                    int t=globalVariable.getTotal();
                    globalVariable.setTotal(t+Integer.parseInt(textView_score.getText().toString()));                    Intent intent = new Intent(Game6.this,Game7.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        imageView_ch=findViewById(R.id.imageView_G6ch);
        imageView_t=findViewById(R.id.imageView_G6t);
        textView_timer=findViewById(R.id.textView_G6timer);
        textView_score=findViewById(R.id.textView_G6score);
        imageView_ch2=findViewById(R.id.imageView_G6ch2);
        imageView_ch3=findViewById(R.id.imageView_G6ch3);
        imageView_ch4=findViewById(R.id.imageView_G6ch4);
    }


    private void game() {

        imageView_ch.setVisibility(View.VISIBLE);
        imageView_t.setVisibility(View.VISIBLE);
        textView_timer.setVisibility(View.VISIBLE);
        textView_score.setVisibility(View.VISIBLE);

        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView_timer.setText(""+millisUntilFinished / 1000);

                //抓蟲
                if(s==2&&t==0){
                    int r = new Random().nextInt(3);
                    if(r==0){
                        imageView_ch2.setVisibility(View.VISIBLE);
                    }else if(r==1){
                        imageView_ch2.setVisibility(View.VISIBLE);
                        imageView_ch3.setVisibility(View.VISIBLE);
                    }else {
                        imageView_ch2.setVisibility(View.VISIBLE);
                        imageView_ch3.setVisibility(View.VISIBLE);
                        imageView_ch4.setVisibility(View.VISIBLE);
                    }
                    t++;
                }
                if(s==2&&t==1&&
                        imageView_ch2.getVisibility()==View.GONE&&
                        imageView_ch3.getVisibility()==View.GONE&&
                        imageView_ch4.getVisibility()==View.GONE){
                    imageView_ch.setImageResource(R.drawable.game6ch4);
                    imageView_t.setImageResource(R.drawable.game6t4);
                    s=3;
                    t=0;
                }
            }
            public void onFinish() {
                textView_timer.setText("0");
                button_go.setVisibility(View.VISIBLE);
                imageView_t.setOnTouchListener(null);
            }
        }.start();

        imageView_ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView_ch2.setVisibility(View.GONE);
            }
        });
        imageView_ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView_ch3.setVisibility(View.GONE);
            }
        });
        imageView_ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView_ch4.setVisibility(View.GONE);
            }
        });
        imageView_t.setOnTouchListener(touchListener);
    }

    int s=0,t=0;
    float initX,initY=0;


    //圖片移動
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        private float x, y;  // 原本圖片存在的 X,Y軸位置
        private float mx, my;  // 圖片被拖曳的 X,Y軸距離長度

        @Override public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    x = event.getX();                  //觸控的X軸位置
                    y = event.getY();                  //觸控的Y軸位置
                    if(initX==0&&initY==0){
                        initX = view.getX();
                        initY = view.getY();
                        Log.d("Test","initX "+initX);
                        Log.d("Test","initY "+initY);
                    }
                    image_pos(imageView_ch);            //題目位置

                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    mx = x-view.getX();
                    my = y-view.getY();
                    view.setX(event.getX()-mx);
                    view.setY(event.getY()-my);
                    Log.d("Test","view.setX "+(event.getX()-mx));
                    Log.d("Test","view.setY "+(event.getY()-my));

                    if(((right-left)/3+left-100)<=(event.getX()-mx) && (event.getX()-mx)<=((right-left)/3+left+100)&&
                            ((button-top)/3+top-100)<=(event.getY()-my) && (event.getY()-my)<=((button-top)/3+top+100)){
                       if(s==0){
                           imageView_ch.setImageResource(R.drawable.game6ch2);
                           imageView_t.setImageResource(View.INVISIBLE);
                           Toast toast=Toast.makeText(Game6.this, "長大大!", Toast.LENGTH_SHORT);
                           toast.show();
                           Handler handler=new Handler();
                           handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   toast.cancel();
                                   imageView_t.setImageResource(View.VISIBLE);
                                   imageView_t.setImageResource(R.drawable.game6t2);
                                   s=1;
                               }
                           },1500);
                       }
                       if(s==1){
                           imageView_t.setImageResource(View.INVISIBLE);
                           Toast toast=Toast.makeText(Game6.this, "長大大!", Toast.LENGTH_SHORT);
                           toast.show();
                           Handler handler=new Handler();
                           handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   imageView_ch.setImageResource(R.drawable.game6ch3);
                                   toast.cancel();
                                   s=2;
                               }
                           },1500);
                       }
                       if(s==3){
                           imageView_ch.setImageResource(R.drawable.game6ch5);
                           imageView_t.setImageResource(View.INVISIBLE);

                           Toast.makeText(Game6.this, "長大大!", Toast.LENGTH_SHORT).show();
                           Handler handler=new Handler();
                           handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   imageView_ch.setImageResource(R.drawable.game6ch6);
                                   imageView_ch.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           imageView_ch.setImageResource(R.drawable.game6ch1);
                                           imageView_t.setImageResource(R.drawable.game6t1);
                                           int score = Integer.parseInt(textView_score.getText().toString())+3;
                                           textView_score.setText(""+score);
                                           imageView_ch.setOnClickListener(null);
                                       }
                                   });
                               }
                           },1500);
                           s=0;
                       }
                    }
                    break;
                }
                case MotionEvent.ACTION_UP:{
                    view.setX(initX);
                    view.setY(initY);
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