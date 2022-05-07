package com.example.luodong_river;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game7 extends GlobalCode {

    Button button_go;
    ImageView imageView_ch;
    TextView textView_point;
    ImageView imageView_menu;
    GlobalVariable globalVariable;
    int p=0;
    int times=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game7);

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
                globalVariable.setTotal(p);
                Intent intent =new Intent(Game7.this,DeveloperMode.class);
                startActivity(intent);
                finish();
            }
        });

        button_go=findViewById(R.id.button_G7go);
        imageView_ch=findViewById(R.id.imageView_G7ch);

        // 分數
        textView_point=findViewById(R.id.textView_point);
        p=globalVariable.getTotal();
        textView_point.setText("累積分數: "+p);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(p/10>0){
                    Toast.makeText(Game7.this, "超過10分請洽服務中心兌換", Toast.LENGTH_SHORT).show();
                }
                if(p%10>0 && times<4){
                    imageView_ch.setRotation(0f); //歸正
                    int r = new Random().nextInt(8)+1; //隨機度數
                    float f = 365+45*r;
                    imageView_ch.animate().rotation(f).setDuration(2000).start(); //轉
                    Log.d("Test",""+r);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 2 seconds
                            if(r==2||r==3||r==5||r==7){
                                Toast.makeText(Game7.this, "恭喜獲得5元折價券", Toast.LENGTH_SHORT).show();
                            }else if(r==1||r==4||r==6){
                                Toast.makeText(Game7.this, "恭喜獲得10元折價券", Toast.LENGTH_SHORT).show();
                            }else if(r==8){
                                Toast.makeText(Game7.this, "恭喜獲得15元折價券", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000);
                    p--;
                    textView_point.setText("累積分數: "+p);
                }else{
                    if(times>=4){
                        Toast.makeText(Game7.this, "次數已達上限", Toast.LENGTH_SHORT).show();
                    }
                    if(p%10<=0){
                        Toast.makeText(Game7.this, "個位數積分不足抽獎", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });


    }
}