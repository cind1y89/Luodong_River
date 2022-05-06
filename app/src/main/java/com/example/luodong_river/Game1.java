package com.example.luodong_river;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Game1 extends GlobalCode {

    Button button_go;
    View layout;
    TextView textView_score,textView_timer,textView_text;
    ImageView imageView_ch,imageView_d1,imageView_d2,imageView_d3,imageView_d4,imageView_d5,imageView_menu;
    GlobalVariable globalVariable_mode;
    int i=0;
    int screenWidth=0;
    float screen_x=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        button_go=findViewById(R.id.button_G1go);
        layout=findViewById(R.id.layout_G2);
        textView_score=findViewById(R.id.textView_G1score);
        textView_timer=findViewById(R.id.textView_G1timer);
        textView_text=findViewById(R.id.textView_G1text);
        imageView_ch=findViewById(R.id.imageView_ch);
        imageView_d1=findViewById(R.id.imageView_d1);
        imageView_d2=findViewById(R.id.imageView_d2);
        imageView_d3=findViewById(R.id.imageView_d3);
        imageView_d4=findViewById(R.id.imageView_d4);
        imageView_d5=findViewById(R.id.imageView_d5);

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
                Intent intent =new Intent(Game1.this,DeveloperMode.class);
                startActivity(intent);
                finish();
            }
        });


        //screen monitor
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        Log.d("screen","screenWidth"+screenWidth);

        //button
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                   i++;
                   layout.setBackgroundResource(R.drawable.game1b2);
                   button_go.setBackgroundResource(R.drawable.button_start);

                }else if(i==1){
                    i++;
                    // TODO: 2022/5/3 遊戲背景
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    button_go.setVisibility(View.GONE);

                    // 3sec預備倒數
                    textView_text.setVisibility(View.VISIBLE);
                    new CountDownTimer(4000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            if((millisUntilFinished / 1000)==0){
                                textView_text.setText("Start");
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
                    Intent intent = new Intent(Game1.this,Game2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    int r = new Random().nextInt(3);
    private void game() {
        textView_score.setVisibility(View.VISIBLE);
        textView_timer.setVisibility(View.VISIBLE);
        imageView_ch.setVisibility(View.VISIBLE);

        if(r==0){
            imageView_d1.setImageResource(R.drawable.game1f1);
            imageView_d2.setImageResource(R.drawable.game1f2);
            imageView_d3.setImageResource(R.drawable.game1f5);
            imageView_d4.setImageResource(R.drawable.game1f5);
            imageView_d5.setImageResource(R.drawable.game1f4);
        }else if(r==1){
            imageView_d1.setImageResource(R.drawable.game1f5);
            imageView_d2.setImageResource(R.drawable.game1f2);
            imageView_d3.setImageResource(R.drawable.game1f5);
            imageView_d4.setImageResource(R.drawable.game1f3);
            imageView_d5.setImageResource(R.drawable.game1f1);
        }else{
            imageView_d1.setImageResource(R.drawable.game1f2);
            imageView_d2.setImageResource(R.drawable.game1f5);
            imageView_d3.setImageResource(R.drawable.game1f4);
            imageView_d4.setImageResource(R.drawable.game1f3);
            imageView_d5.setImageResource(R.drawable.game1f5);
        }


        // 30秒倒數 與 掉落物
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView_timer.setText(""+millisUntilFinished / 1000);

                if((millisUntilFinished / 1000)==29){
                    image_drop(imageView_d2);
                }
                image_pos(imageView_d2);
                if((millisUntilFinished / 1000)==24){
                    image_drop(imageView_d1);
                }
                image_pos(imageView_d1);
                if((millisUntilFinished / 1000)==19){
                    image_drop(imageView_d4);
                }
                image_pos(imageView_d4);
                if((millisUntilFinished / 1000)==14){
                    image_drop(imageView_d3);
                }
                image_pos(imageView_d3);
                if((millisUntilFinished / 1000)==9){
                    image_drop(imageView_d5);
                }
                image_pos(imageView_d5);

            }
            public void onFinish() {
                textView_timer.setText("0");
                textView_text.setVisibility(View.VISIBLE);
                textView_text.setText("GAMEOVER");
                // TODO: 2022/5/3 下一關按鈕背景
                button_go.setVisibility(View.VISIBLE);
            }
        }.start();



        // 觸控偵測
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    screen_x = motionEvent.getX();
                    //Log.d("screen","x position:"+screen_x);

                    if(screen_x>screenWidth/2){
                        ConstraintLayout.LayoutParams mParams = (ConstraintLayout.LayoutParams) imageView_ch.getLayoutParams();
                        mParams.leftMargin += 150;
                        //Log.d("screen","L:"+mParams.leftMargin);
                    }else{
                        ConstraintLayout.LayoutParams mParams = (ConstraintLayout.LayoutParams) imageView_ch.getLayoutParams();
                        mParams.leftMargin -= 150;
                        //Log.d("screen","R:"+mParams.leftMargin);
                    }
                }
                return false;
            }
        });
    }

    private void image_drop(ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
        ObjectAnimator animation = ObjectAnimator.ofFloat(imageView, "translationY", 2000f);
        animation.setDuration(8000);
        animation.start();
    }

    private void image_pos(ImageView imageView) {

        int[] image_location,image_location_ch;
        int image_x=0,image_y=0,image_chx=0,image_chy=0;
        boolean y=false,x=false;

        // 物圖片位置
        image_location= new int[2];
        imageView.getLocationOnScreen(image_location);
        image_x = image_location[0];
        image_y = image_location[1];
        Log.d("screen","image x:"+image_x);
        Log.d("screen","image y:"+image_y);

        // 人圖片位置
        image_location_ch=new int[2];
        imageView_ch.getLocationOnScreen(image_location_ch);
        image_chx = image_location_ch[0];
        image_chy = image_location_ch[1];
        Log.d("screen","image chx:"+image_chx);
        Log.d("screen","image chy:"+image_chy);


        // TODO: 2022/5/3 錯誤倒扣
        //位置比較
        if(image_y<=image_chy+125 && image_y>=image_chy-125){
            Log.d("screen","y same");
            y=true;
        }else{
            y=false;
        }

        if(image_chx<0){
            image_chx=-image_chx;
        }

        if(image_x<=image_chx+125 && image_x>=image_chx-125){
            Log.d("screen","x same");
            x=true;
        }else{
            x=false;
        }

        if(r==0 ){
            //3、4
            if(imageView==imageView_d3 || imageView==imageView_d4){
                if(x && y){
                    int s = Integer.parseInt(textView_score.getText().toString());
                    textView_score.setText(""+(s+3));
                    imageView.setVisibility(View.GONE);
                }
            }else{
                if(x && y){
                    int s = Integer.parseInt(textView_score.getText().toString());
                    if(s>=2){
                        textView_score.setText(""+(s-2));
                    }else{
                        textView_score.setText("0");
                    }

                    imageView.setVisibility(View.GONE);
                }
            }
        }else if(r==1){
            //1、3
            if(imageView==imageView_d1 || imageView==imageView_d3){
                if(x && y){
                    int s = Integer.parseInt(textView_score.getText().toString());
                    textView_score.setText(""+(s+3));
                    imageView.setVisibility(View.GONE);
                }
            }else{
                if(x && y){
                    int s = Integer.parseInt(textView_score.getText().toString());
                    if(s>=3){
                        textView_score.setText(""+(s-3));
                    }else{
                        textView_score.setText("0");
                    }

                    imageView.setVisibility(View.GONE);
                }
            }
        }else {
            //2、5
            if(imageView==imageView_d2 || imageView==imageView_d5){
                if(x && y){
                    int s = Integer.parseInt(textView_score.getText().toString());
                    textView_score.setText(""+(s+3));
                    imageView.setVisibility(View.GONE);
                }
            }else{
                if(x && y){
                    int s = Integer.parseInt(textView_score.getText().toString());
                    if(s>=3){
                        textView_score.setText(""+(s-3));
                    }else{
                        textView_score.setText("0");
                    }

                    imageView.setVisibility(View.GONE);
                }
            }
        }


    }

}