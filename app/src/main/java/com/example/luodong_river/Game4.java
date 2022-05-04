package com.example.luodong_river;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.luodong_river.R.drawable.game4b3a1;
import static com.example.luodong_river.R.drawable.game4b3a2;
import static com.example.luodong_river.R.drawable.game4b3a3;
import static com.example.luodong_river.R.drawable.game4b3a4;
import static com.example.luodong_river.R.drawable.game4b4a1;
import static com.example.luodong_river.R.drawable.game4b4a2;
import static com.example.luodong_river.R.drawable.game4b4a3;
import static com.example.luodong_river.R.drawable.game4b4a4;
import static com.example.luodong_river.R.drawable.game4b5a1;
import static com.example.luodong_river.R.drawable.game4b5a2;
import static com.example.luodong_river.R.drawable.game4b5a3;
import static com.example.luodong_river.R.drawable.game4b5a4;
import static com.example.luodong_river.R.drawable.game4b6a1;
import static com.example.luodong_river.R.drawable.game4b6a2;
import static com.example.luodong_river.R.drawable.game4b6a3;
import static com.example.luodong_river.R.drawable.game4b6a4;
import static com.example.luodong_river.R.drawable.game4b7a1;
import static com.example.luodong_river.R.drawable.game4b7a2;
import static com.example.luodong_river.R.drawable.game4b7a3;
import static com.example.luodong_river.R.drawable.game4b7a4;
import static com.example.luodong_river.R.drawable.game4b8a1;
import static com.example.luodong_river.R.drawable.game4b8a2;
import static com.example.luodong_river.R.drawable.game4b8a3;
import static com.example.luodong_river.R.drawable.game4b8a4;

public class Game4 extends AppCompatActivity {

    Button button_go,button_a1,button_a2,button_a3,button_a4;
    View layout;
    TextView textView_text,textView_q;
    ImageView imageView_q;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);

        button_go=findViewById(R.id.button_G4go);
        button_a1=findViewById(R.id.button_a1);
        button_a2=findViewById(R.id.button_a2);
        button_a3=findViewById(R.id.button_a3);
        button_a4=findViewById(R.id.button_a4);
        layout=findViewById(R.id.layout_G4);
        imageView_q=findViewById(R.id.imageView_G4q);
        textView_text=findViewById(R.id.textView_G4text);
        textView_q=findViewById(R.id.textView_G4q);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    i++;
                    layout.setBackgroundResource(R.drawable.game4b2);
                    button_go.setBackgroundResource(R.drawable.button_start);

                }else if(i==1){
                    i++;
                    // TODO: 2022/5/3 遊戲背景
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    game();
                    button_go.setVisibility(View.GONE);
                }else{
                    Intent intent = new Intent(Game4.this,Game5.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    // TODO: 2022/5/4 分數、計時器
    String[] question={
            "鴨子從小開始飼養要幾周?",
            "稻米收成後的稻草，除了做肥料外，還有甚麼用途呢?",
            "廣興農場的前身是種甚麼動物的繁殖場呢?",
            "鴨母寮裡面住的是男生還是女生呢?",
            "豬哥窟裡面住的是男生還是女生呢?",
            "稻草搭成的簡易房子叫甚麼呢?"};
    // TODO: 2022/5/4 題目答案?
    int[] answer={1,2,3,4,1,1};
    int index=0;
    // TODO: 2022/5/4 題目圖片?
    private void game() {
        Log.d("index",""+index);
        button_a1.setVisibility(View.VISIBLE);
        button_a2.setVisibility(View.VISIBLE);
        button_a3.setVisibility(View.VISIBLE);
        button_a4.setVisibility(View.VISIBLE);
        imageView_q.setVisibility(View.VISIBLE);
        textView_q.setVisibility(View.VISIBLE);

        if(index==0){
            Log.d("index","0:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageDrawable();
            button_a1.setBackgroundResource(game4b3a1);
            button_a2.setBackgroundResource(game4b3a2);
            button_a3.setBackgroundResource(game4b3a3);
            button_a4.setBackgroundResource(game4b3a4);
            answer();
        }else if(index==1){
            Log.d("index","1:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageDrawable();
            button_a1.setBackgroundResource(game4b4a1);
            button_a2.setBackgroundResource(game4b4a2);
            button_a3.setBackgroundResource(game4b4a3);
            button_a4.setBackgroundResource(game4b4a4);
            answer();
        }else if(index==2){
            Log.d("index","2:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageDrawable();
            button_a1.setBackgroundResource(game4b5a1);
            button_a2.setBackgroundResource(game4b5a2);
            button_a3.setBackgroundResource(game4b5a3);
            button_a4.setBackgroundResource(game4b5a4);
            answer();
        }else if(index==3){
            Log.d("index","3:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageDrawable();
            button_a1.setBackgroundResource(game4b6a1);
            button_a2.setBackgroundResource(game4b6a2);
            button_a3.setBackgroundResource(game4b6a3);
            button_a4.setBackgroundResource(game4b6a4);
            answer();
        }else if(index==4){
            Log.d("index","4:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageDrawable();
            button_a1.setBackgroundResource(game4b7a1);
            button_a2.setBackgroundResource(game4b7a2);
            button_a3.setBackgroundResource(game4b7a3);
            button_a4.setBackgroundResource(game4b7a4);
            answer();
        }else if(index==5){
            Log.d("index","5:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageDrawable();
            button_a1.setBackgroundResource(game4b8a1);
            button_a2.setBackgroundResource(game4b8a2);
            button_a3.setBackgroundResource(game4b8a3);
            button_a4.setBackgroundResource(game4b8a4);
            answer();
        }
    }

    // 題目答案檢測
    private void answer() {
        Log.d("index","a:"+index);
        if(answer[index]==1){
            button_a1.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "恭喜，你答對了", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a2.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a3.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });button_a4.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
        }else if(answer[index]==2){
            button_a1.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a2.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "恭喜，你答對了", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a3.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });button_a4.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
        }else if(answer[index]==3){
            button_a1.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a2.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a3.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "恭喜，你答對了", Toast.LENGTH_SHORT).show();
                indexCheck();
            });button_a4.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
        }else if(answer[index]==4){
            button_a1.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a2.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
            button_a3.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "燈等，不對喔", Toast.LENGTH_SHORT).show();
                indexCheck();
            });button_a4.setOnClickListener(view -> {
                Toast.makeText(Game4.this, "恭喜，你答對了", Toast.LENGTH_SHORT).show();
                indexCheck();
            });
        }


    }

    //題號檢測
    private void indexCheck() {
        if(index<5){
            index++;
            game();
        }else{
            button_go.setVisibility(View.VISIBLE);
            button_a1.setVisibility(View.GONE);
            button_a2.setVisibility(View.GONE);
            button_a3.setVisibility(View.GONE);
            button_a4.setVisibility(View.GONE);
        }
    }
}
