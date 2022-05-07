package com.example.luodong_river;



import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
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

import java.util.Objects;

public class Game3 extends GlobalCode {

    Button button_go;
    View layout;
    TextView textView_text,textView_timer,textView_score;
    ImageView imageView_menu,imageView_ch,imageView_t;
    GlobalVariable globalVariable;
    int i=0;

    SensorManager sensorManager;
    float accel,accelCurrent,accelLast;


    @Override
    protected void onResume() {
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

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
                Intent intent =new Intent(Game3.this,DeveloperMode.class);
                startActivity(intent);
                finish();
            }
        });

        //button
        button_go=findViewById(R.id.button_G3go);
        layout=findViewById(R.id.layout_G3);
        textView_text=findViewById(R.id.textView_G3text);
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    i++;
                    layout.setBackgroundResource(R.drawable.game3b2);
                    button_go.setBackgroundResource(R.drawable.button_start);

                }else if(i==1){
                    i++;
                    // TODO: 2022/5/3 遊戲背景
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    button_go.setVisibility(View.GONE);

//                  3sec預備倒數
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
                    globalVariable.setTotal(t+Integer.parseInt(textView_score.getText().toString()));
                    Intent intent = new Intent(Game3.this,Game4.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        textView_timer=findViewById(R.id.textView_G3timer);
        textView_score=findViewById(R.id.textView_G3score);
        imageView_ch=findViewById(R.id.imageView_G3ch);
        imageView_t=findViewById(R.id.imageView_G3t);

        //sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(sensorManager).registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        accel = 10f;
        accelCurrent = SensorManager.GRAVITY_EARTH;
        accelLast = SensorManager.GRAVITY_EARTH;
    }

    int shake_count=0;
    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            accelLast = accelCurrent;
            accelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = accelCurrent - accelLast;
            accel = accel * 0.9f + delta;
            Log.d("Test","accel "+accel);
            Log.d("Test","index "+index);
            if (accel > 12 && index>=9) {
                shake_count++;
                if(shake_count<=10){
                    Toast toast=Toast.makeText(Game3.this,"加油加油，還有"+(10-shake_count)+"下",Toast.LENGTH_SHORT);
                    toast.show();
                    toast_cancel(toast);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    CountDownTimer countDownTimer,countDownTimer5;
    private void game() {

        //ui init
        textView_text.setVisibility(View.GONE);
        textView_timer.setVisibility(View.VISIBLE);
        textView_score.setVisibility(View.VISIBLE);
        imageView_ch.setVisibility(View.VISIBLE);
        imageView_t.setVisibility(View.VISIBLE);

        //timer
        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {

                textView_timer.setText(""+l/1000);

                //first mission
                if(l/1000==59){
                    timer();
                    Toast toast=Toast.makeText(Game3.this,"拔葉子",Toast.LENGTH_SHORT);
                    toast.show();
                    toast_cancel(toast);
                }

                //last mission
                if(shake_count>=10){
                    cancel();
                    if(l/1000>=30){
                        point=3;
                        score();
                    }else if(l/1000>=15){
                        point=2;
                        score();
                    }else{
                        point=1;
                        score();
                    }
                    sensorManager.unregisterListener(sensorEventListener);
                    textView_timer.setText("0");
                    textView_text.setVisibility(View.VISIBLE);
                    textView_text.setText("Congratulate");
                    button_go.setVisibility(View.VISIBLE);
                    imageView_ch.setVisibility(View.GONE);
                    imageView_t.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFinish() {
                sensorManager.unregisterListener(sensorEventListener);
                layout.setBackgroundResource(R.color.white);
                textView_timer.setText("0");
                textView_text.setVisibility(View.VISIBLE);
                textView_text.setText("GAMEOVER");
                button_go.setVisibility(View.VISIBLE);
                imageView_ch.setVisibility(View.GONE);
                imageView_t.setVisibility(View.GONE);
            }
        }.start();

        //touchlistener
        imageView_t.setOnTouchListener(touchListener);
    }

    //1 sec toast
    private void toast_cancel(Toast toast) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },1000);
    }

    int back=0;
    int point=0;
    private void timer() {

        back=0;
        layout.setBackgroundResource(R.color.white);

        countDownTimer5 = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long l) {
                //background
                if(l/1000==5){
                    back=5;
                    layout.setBackgroundResource(R.drawable.game3n5);
                    point=3;
                }else if(l/1000==4){
                    back=4;
                    layout.setBackgroundResource(R.drawable.game3n4);
                    point=3;
                }else if(l/1000==3){
                    back=3;
                    layout.setBackgroundResource(R.drawable.game3n3);
                    point=2;
                }else if(l/1000==2){
                    back=2;
                    layout.setBackgroundResource(R.drawable.game3n2);
                    point=2;
                }else if(l/1000==1){
                    back=1;
                    layout.setBackgroundResource(R.drawable.game3n1);
                    point=1;
                }else{
                    back=0;
                    layout.setBackgroundResource(R.color.white);
                    point=0;
                }
            }
            @Override
            public void onFinish() {
                back=0;
                point=0;
                countDownTimer.cancel();
                layout.setBackgroundResource(R.color.white);
                textView_timer.setText("0");
                textView_text.setVisibility(View.VISIBLE);
                textView_text.setText("GAMEOVER");
                button_go.setVisibility(View.VISIBLE);
                imageView_ch.setVisibility(View.GONE);
                imageView_t.setVisibility(View.GONE);
            }
        }.start();
    }

    float initX=0,initY=0;
    Boolean m=true;
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
                    image_pos(imageView_ch);            //題目位置
                    if(initX==0&&initY==0){
                        initX = view.getX();
                        initY = view.getY();
//                        Log.d("Test","initX "+initX);
//                        Log.d("Test","initY "+initY);
                    }
                    break;
                }
                case MotionEvent.ACTION_UP:{
                    view.setX(initX);
                    view.setY(initY);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    mx = x-view.getX();
                    my = y-view.getY();
                    view.setX(event.getX()-mx);
                    view.setY(event.getY()-my);
//                    Log.d("Test","view.setX "+(event.getX()-mx));
//                    Log.d("Test","view.setY "+(event.getY()-my));
//                    Log.d("Test","posX "+((right-left)/3+left-100)+" "+(event.getX()-mx)+" "+((right-left)/3+left+100)+"");
//                    Log.d("Test","posY "+((button-top)/4+top-100)+" "+(event.getY()-my)+" "+((button-top)/4+top+100)+"");

                    // check pos
                    if(((right-left)/3+left-100)<=(event.getX()-mx) && (event.getX()-mx)<=((right-left)/3+left+100)
                     &&((button-top)/4+top-100)<=(event.getY()-my) && (event.getY()-my)<=((button-top)/4+top+100)){

                        if(back>0&&m){
                            m=false;
                            countDownTimer5.cancel();
                            imageView_t.setVisibility(View.GONE);
                            image_index();
                            break;
                        }
                    }
                    break;
                }
            }
            return true;
        }
    };

    int index=0;
    //題號
    private void image_index() {
        score();
        index++;
        if(index==1){
            Toast toast=Toast.makeText(Game3.this,"洗淨淨",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            imageView_ch.setImageResource(R.drawable.game3ch2);
            imageView_t.setImageResource(R.drawable.game3t2);
        }else if(index==2){
            Toast toast=Toast.makeText(Game3.this,"戳洞洞",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            imageView_ch.setImageResource(R.drawable.game3ch3);
            imageView_t.setImageResource(R.drawable.game3t3);
        }else if(index==3){
            Toast toast=Toast.makeText(Game3.this,"煮淨淨",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            imageView_ch.setImageResource(R.drawable.game3ch4);
            imageView_t.setImageResource(R.drawable.game3t4);
        }else if(index==4){
            Toast toast=Toast.makeText(Game3.this,"裝罐罐",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            imageView_ch.setImageResource(R.drawable.game3ch5);
            imageView_t.setImageResource(R.drawable.game3t5);
        }else if(index==5){
            Toast toast=Toast.makeText(Game3.this,"裝罐罐",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            imageView_ch.setImageResource(R.drawable.game3ch6);
            imageView_t.setImageResource(R.drawable.game3t6);
        }else if(index==6){
            Toast toast=Toast.makeText(Game3.this,"裝罐罐",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            imageView_ch.setImageResource(R.drawable.game3ch7);
            imageView_t.setImageResource(R.drawable.game3t7);
        }else if(index==7){
            Toast toast=Toast.makeText(Game3.this,"裝罐罐",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            imageView_ch.setImageResource(R.drawable.game3ch8);
            imageView_t.setImageResource(R.drawable.game3t8);
        }else if(index==8){
            layout.setBackgroundResource(R.color.white);
            imageView_ch.setImageResource(R.drawable.game3ch9);
            imageView_t.setVisibility(View.GONE);
            Toast toast=Toast.makeText(Game3.this,"用力搖",Toast.LENGTH_SHORT);
            toast.show();
            toast_cancel(toast);
            index++;
        }
        if(index<8){
            timer();
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView_t.setVisibility(View.VISIBLE);
                    m=true;
                }
            },500);
        }


    }

    //score
    private void score() {
        int s = Integer.parseInt(textView_score.getText().toString());
        textView_score.setText(""+(s+3));
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