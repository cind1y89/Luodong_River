package com.example.luodong_river;



import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game3 extends GlobalCode {

    Button button_go;
    View layout;
    TextView textView_text;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

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
//                    button_go.setVisibility(View.GONE);

                    // 3sec預備倒數
//                    textView_text.setVisibility(View.VISIBLE);
//                    new CountDownTimer(3000, 1000) {
//                        public void onTick(long millisUntilFinished) {
//                            if((millisUntilFinished / 1000)==0){
//                                textView_text.setText("start");
//                            }else{
//                                textView_text.setText(""+millisUntilFinished / 1000);
//                            }
//                        }
//                        public void onFinish() {
//                            game();
//                            textView_text.setVisibility(View.GONE);
//                        }
//                    }.start();
                }else{
                    Intent intent = new Intent(Game3.this,Game4.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    // TODO: 2022/5/4 圖片?
    private void game() {
    }
}