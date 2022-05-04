package com.example.luodong_river;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class Game7 extends GlobalCode {

    Button button_go;
    ImageView imageView_ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game7);

        button_go=findViewById(R.id.button_G7go);
        imageView_ch=findViewById(R.id.imageView_G7ch);

        // TODO: 2022/5/4 圖片?計分? 
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView_ch.setRotation(0f); //歸正
                imageView_ch.animate().rotation(180f).setDuration(2000).start(); //轉
            }
        });


    }
}