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
    TextView textView_text,textView_timer,textView_q,textView_s;
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
        textView_s=findViewById(R.id.textView_G2score);

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

    String[] q={
            "請問小豬豬吃甚麼?",
            "請問小羊羊吃甚麼?",
            "請問天竺鼠吃甚麼?",
            "請問兔兔子吃甚麼?",
            "請問大山羊吃甚麼?",
            "請問大雞雞吃甚麼?",
            "請問大鴨鴨吃甚麼?",
            "請問大魚魚吃甚麼?"
    };
    int[] a={0,0,1,1,2,3,3,3};
    int index=0;

    private void game() {
        textView_text.setVisibility(View.GONE);
        textView_timer.setVisibility(View.VISIBLE);
        textView_s.setVisibility(View.VISIBLE);

        // 30秒倒數
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView_timer.setText(""+millisUntilFinished / 1000);
                if(index == 9){
                    textView_q.setVisibility(View.GONE);
                    imageView_q.setVisibility(View.GONE);
                    imageView_a1.setVisibility(View.GONE);
                    imageView_a2.setVisibility(View.GONE);
                    imageView_a3.setVisibility(View.GONE);
                    cancel();

                    textView_timer.setText("0");
                    textView_text.setVisibility(View.VISIBLE);
                    textView_text.setText("Congratulate");
                    // TODO: 2022/5/3 下一關按鈕背景
                    button_go.setVisibility(View.VISIBLE);
                }
            }
            public void onFinish() {
                textView_q.setVisibility(View.GONE);
                imageView_q.setVisibility(View.GONE);
                imageView_a1.setVisibility(View.GONE);
                imageView_a2.setVisibility(View.GONE);
                imageView_a3.setVisibility(View.GONE);
                textView_timer.setText("0");
                textView_text.setVisibility(View.VISIBLE);
                textView_text.setText("GAMEOVER");
                // TODO: 2022/5/3 下一關按鈕背景
                button_go.setVisibility(View.VISIBLE);
            }
        }.start();


        textView_q.setVisibility(View.VISIBLE);
        textView_q.setText(q[index]);

        imageView_q.setVisibility(View.VISIBLE);
        imageView_a1.setVisibility(View.VISIBLE);
        imageView_a2.setVisibility(View.VISIBLE);
        imageView_a3.setVisibility(View.VISIBLE);

        imageView_a1.setOnTouchListener(touchListener);
        imageView_a2.setOnTouchListener(touchListener);
        imageView_a3.setOnTouchListener(touchListener);

        question();

    }

    float initX1,initY1,initX2,initY2,initX3,initY3=0;
    Boolean t=true;
    //圖片移動
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        private float x, y;  // 原本圖片存在的 X,Y軸位置
        private float mx, my;  // 圖片被拖曳的 X,Y軸距離長度

        @Override public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    Log.d("Test","index "+index);
                    x = event.getX();                  //觸控的X軸位置
                    y = event.getY();                  //觸控的Y軸位置
                    image_pos(imageView_q);            //題目位置
                    if(initX1==0&&initY1==0&&view==imageView_a1){
                        initX1 = view.getX();
                        initY1 = view.getY();
//                        Log.d("Test","initX "+initX1);
//                        Log.d("Test","initY "+initY1);
                    }else if(initX2==0&&initY2==0&&view==imageView_a2){
                        initX2 = view.getX();
                        initY2 = view.getY();
                    }else if(initX3==0&&initY3==0&&view==imageView_a3){
                        initX3 = view.getX();
                        initY3 = view.getY();
                    }
                    break;
                }
                case MotionEvent.ACTION_UP:{
                    if(view==imageView_a1){
                        view.setX(initX1);
                        view.setY(initY1);
                    }else if(view==imageView_a2){
                        view.setX(initX2);
                        view.setY(initY2);
                    }else if(view==imageView_a3){
                        view.setX(initX3);
                        view.setY(initY3);
                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    mx = x-view.getX();
                    my = y-view.getY();
                    view.setX(event.getX()-mx);
                    view.setY(event.getY()-my);
//                    Log.d("Test","view.setX "+(event.getX()-mx));
//                    Log.d("Test","view.setY "+(event.getY()-my));
//                    Log.d("Test","posX "+((right-left)/2+left-100)+" "+(event.getX()-mx)+" "+((right-left)/2+left+100)+"");
//                    Log.d("Test","posY "+((button-top)/2+top-100)+" "+(event.getY()-my)+" "+((button-top)/2+top-100)+"");
                    if(((right-left)/3+left-100)<=(event.getX()-mx) && (event.getX()-mx)<=((right-left)/3+left+100)){
                        if(((button-top)/3+top-100)<=(event.getY()-my) && (event.getY()-my)<=((button-top)/3+top+100)){


                            if(index==1||index==2||index==5){
                                if(view==imageView_a2&&t){
                                    view.setVisibility(View.GONE);
                                    question_right();
                                }else{
                                    question_wrong();
                                }
                            }else if(index==3||index==4){
                                if(view==imageView_a1&&t){
                                    view.setVisibility(View.GONE);
                                    question_right();
                                }else{
                                    question_wrong();
                                }
                            }else if(index==6||index==7||index==8){
                                if(view==imageView_a3&&t){
                                    view.setVisibility(View.GONE);
                                    question_right();
                                }else{
                                    question_wrong();
                                }
                            }
                        }
                    }
                    break;
                }
            }
            return true;
        }
    };

    private void question_wrong() {
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

    private void question_right() {
        t=false;
        imageView_a1.setVisibility(View.GONE);
        imageView_a2.setVisibility(View.GONE);
        imageView_a3.setVisibility(View.GONE);
        Toast toast =Toast.makeText(Game2.this,"恭喜，你答對了",Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(index<8){
                    question();
                }else{
                    index++;
                }
                toast.cancel();
            }
        }, 500);
    }

    // index
    private void question() {
        t=true;
        imageView_a1.setVisibility(View.VISIBLE);
        imageView_a2.setVisibility(View.VISIBLE);
        imageView_a3.setVisibility(View.VISIBLE);

        if(a[index]==0){
            textView_q.setText(q[index]);
            if(index==0){
                imageView_q.setImageResource(R.mipmap.game4q5);
                index++;
            }else if(index ==1){
                imageView_q.setImageResource(R.mipmap.game2q1);
                index++;
            }
            imageView_a1.setImageResource(R.mipmap.game2a1);
            imageView_a2.setImageResource(R.mipmap.game2a2);
            imageView_a3.setImageResource(R.mipmap.game2a3);
        }else if(a[index]==1){
            textView_q.setText(q[index]);
            if(index==2){
                imageView_q.setImageResource(R.mipmap.game2q3);
                index++;
            }else if(index ==3){
                imageView_q.setImageResource(R.mipmap.game2q4);
                index++;
            }
            imageView_a1.setImageResource(R.mipmap.game2a1);
            imageView_a2.setImageResource(R.mipmap.game2a2);
            imageView_a3.setImageResource(R.drawable.game2q1a1);
        }else if(a[index]==2){
            textView_q.setText(q[index]);
            if(index==4){
                imageView_q.setImageResource(R.mipmap.game2q1);
                index++;
            }
            imageView_a1.setImageResource(R.mipmap.game2a1);
            imageView_a2.setImageResource(R.drawable.game2a8);
            imageView_a3.setImageResource(R.drawable.game2q1a1);
        }else if(a[index]==3){
            textView_q.setText(q[index]);
            if(index==5){
                imageView_q.setImageResource(R.drawable.game1ch);
                index++;
            }else if(index ==6){
                imageView_q.setImageResource(R.drawable.game1ch);
                index++;
            }else if(index ==7){
                imageView_q.setImageResource(R.drawable.game1ch);
                index++;
            }
            imageView_a1.setImageResource(R.mipmap.game2a1);
            imageView_a2.setImageResource(R.drawable.game2q1a1);
            imageView_a3.setImageResource(R.mipmap.game2a3);
        }

    }

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
