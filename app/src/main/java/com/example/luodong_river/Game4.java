package com.example.luodong_river;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class Game4 extends GlobalCode {

    Button button_go,button_a1,button_a2,button_a3,button_a4;
    View layout;
    TextView textView_text,textView_q,textView_score;
    ImageView imageView_q;
    ImageView imageView_menu;
    GlobalVariable globalVariable;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);

        //?????????????????????
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
                Intent intent =new Intent(Game4.this,DeveloperMode.class);
                startActivity(intent);
                finish();
            }
        });

        button_go=findViewById(R.id.button_G4go);
        button_a1=findViewById(R.id.button_a1);
        button_a2=findViewById(R.id.button_a2);
        button_a3=findViewById(R.id.button_a3);
        button_a4=findViewById(R.id.button_a4);
        layout=findViewById(R.id.layout_G4);
        imageView_q=findViewById(R.id.imageView_G4q);
        textView_text=findViewById(R.id.textView_G4text);
        textView_q=findViewById(R.id.textView_G4q);
        textView_score=findViewById(R.id.textView_G4score);

        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    i++;
                    layout.setBackgroundResource(R.drawable.game4b2);
                    button_go.setBackgroundResource(R.drawable.button_start);

                }else if(i==1){
                    i++;
                    // TODO: 2022/5/3 ????????????
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    game();
                    button_go.setVisibility(View.GONE);
                }else{
                    int t=globalVariable.getTotal();
                    globalVariable.setTotal(t+Integer.parseInt(textView_score.getText().toString()));                    Intent intent = new Intent(Game4.this,Game5.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    // TODO: 2022/5/4 ??????????????????
    String[] question={
            "??????????????????????????????????",
            "??????????????????????????????????????????????????????????????????????",
            "???????????????????????????????????????????????????????",
            "??????????????????????????????????????????????",
            "??????????????????????????????????????????????",
            "????????????????????????????????????????"};
    int[] answer={4,1,4,3,2,3};
    int index=0;
    private void game() {
        Log.d("index",""+index);
        button_a1.setVisibility(View.VISIBLE);
        button_a2.setVisibility(View.VISIBLE);
        button_a3.setVisibility(View.VISIBLE);
        button_a4.setVisibility(View.VISIBLE);
        imageView_q.setVisibility(View.VISIBLE);
        textView_q.setVisibility(View.VISIBLE);
        textView_score.setVisibility(View.VISIBLE);


        if(index==0){
            Log.d("index","0:"+index);
            textView_q.setText(question[index]);
//          imageView_q.setImageResource(R.drawable.);
            button_a1.setBackgroundResource(game4b3a1);
            button_a2.setBackgroundResource(game4b3a2);
            button_a3.setBackgroundResource(game4b3a3);
            button_a4.setBackgroundResource(game4b3a4);
            answer();
        }else if(index==1){
            Log.d("index","1:"+index);
            imageView_q.setImageResource(R.drawable.game2q1a1);
            textView_q.setText(question[index]);
            button_a1.setBackgroundResource(game4b4a1);
            button_a2.setBackgroundResource(game4b4a2);
            button_a3.setBackgroundResource(game4b4a3);
            button_a4.setBackgroundResource(game4b4a4);
            answer();
        }else if(index==2){
            Log.d("index","2:"+index);
            textView_q.setText(question[index]);
            imageView_q.setImageResource(R.drawable.game4q3);
            button_a1.setBackgroundResource(game4b5a1);
            button_a2.setBackgroundResource(game4b5a2);
            button_a3.setBackgroundResource(game4b5a3);
            button_a4.setBackgroundResource(game4b5a4);
            answer();
        }else if(index==3){
            Log.d("index","3:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageResource(R.drawable.);
            button_a1.setBackgroundResource(game4b6a1);
            button_a2.setBackgroundResource(game4b6a2);
            button_a3.setBackgroundResource(game4b6a3);
            button_a4.setBackgroundResource(game4b6a4);
            answer();
        }else if(index==4){
            Log.d("index","4:"+index);
            textView_q.setText(question[index]);
            imageView_q.setImageResource(R.mipmap.game4q5);
            button_a1.setBackgroundResource(game4b7a1);
            button_a2.setBackgroundResource(game4b7a2);
            button_a3.setBackgroundResource(game4b7a3);
            button_a4.setBackgroundResource(game4b7a4);
            answer();
        }else if(index==5){
            Log.d("index","5:"+index);
            textView_q.setText(question[index]);
//            imageView_q.setImageResource(R.drawable.);
            button_a1.setBackgroundResource(game4b8a1);
            button_a2.setBackgroundResource(game4b8a2);
            button_a3.setBackgroundResource(game4b8a3);
            button_a4.setBackgroundResource(game4b8a4);
            answer();
        }
    }

    // ??????????????????
    private void answer() {
        Log.d("index","a:"+index);
        if(answer[index]==1){
            button_a1.setOnClickListener(view -> {
                right();
            });
            button_a2.setOnClickListener(view -> {
                wrong();
            });
            button_a3.setOnClickListener(view -> {
                wrong();
            });button_a4.setOnClickListener(view -> {
                wrong();
            });
        }else if(answer[index]==2){
            button_a1.setOnClickListener(view -> {
                wrong();
            });
            button_a2.setOnClickListener(view -> {
               right();
            });
            button_a3.setOnClickListener(view -> {
                wrong();
            });button_a4.setOnClickListener(view -> {
                wrong();
            });
        }else if(answer[index]==3){
            button_a1.setOnClickListener(view -> {
                wrong();
            });
            button_a2.setOnClickListener(view -> {
                wrong();
            });
            button_a3.setOnClickListener(view -> {
                right();
            });button_a4.setOnClickListener(view -> {
                wrong();
            });
        }else if(answer[index]==4){
            button_a1.setOnClickListener(view -> {
                wrong();
            });
            button_a2.setOnClickListener(view -> {
                wrong();
            });
            button_a3.setOnClickListener(view -> {
                wrong();
            });button_a4.setOnClickListener(view -> {
                right();
            });
        }


    }

    private void wrong() {
        Toast toast =Toast.makeText(Game4.this, "??????????????????", Toast.LENGTH_SHORT);
        toast.show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },1500);

        int s = Integer.parseInt(textView_score.getText().toString());
        if(s>=2){
            textView_score.setText(""+(s-2));
        }else{
            textView_score.setText("0");
        }
        indexCheck();
    }

    private void right() {
        Toast toast =Toast.makeText(Game4.this, "?????????????????????", Toast.LENGTH_SHORT);
        toast.show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },1500);
        int s = Integer.parseInt(textView_score.getText().toString());
        textView_score.setText(""+(s+3));
        indexCheck();
    }

    //????????????
    private void indexCheck() {
        if(index<5){
            index++;
            game();
        }else{
            button_go.setVisibility(View.VISIBLE);
            textView_text.setVisibility(View.VISIBLE);
            textView_text.setText("GAMEOVER");
            textView_q.setVisibility(View.GONE);
            imageView_q.setVisibility(View.GONE);
            button_a1.setVisibility(View.GONE);
            button_a2.setVisibility(View.GONE);
            button_a3.setVisibility(View.GONE);
            button_a4.setVisibility(View.GONE);
        }
    }
}
