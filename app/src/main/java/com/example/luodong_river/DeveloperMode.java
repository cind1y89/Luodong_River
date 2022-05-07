package com.example.luodong_river;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeveloperMode extends GlobalCode {

    Button button_go;
    GlobalVariable globalVariable;
    TextView textView_d1,textView_d2,textView_d3,textView_d4,textView_d5,textView_d6,textView_d7;
    TextView textView_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_mode);

        globalVariable= (GlobalVariable) getApplicationContext();
        Log.d("mode_getApplication_D",globalVariable.getMode());

        button_go=findViewById(R.id.button_Dgo);
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,GameRules.class);
                startActivity(intent);
                finish();
                Log.d("mode_button_rules_D",globalVariable.getMode());
            }
        });

        textView_score=findViewById(R.id.textView_Dscore);
        textView_score.setText("累積分數: "+globalVariable.getTotal());

        textView_d1=findViewById(R.id.textView_d1);
        textView_d2=findViewById(R.id.textView_d2);
        textView_d3=findViewById(R.id.textView_d3);
        textView_d4=findViewById(R.id.textView_d4);
        textView_d5=findViewById(R.id.textView_d5);
        textView_d6=findViewById(R.id.textView_d6);
        textView_d7=findViewById(R.id.textView_d7);

        textView_d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,Game7.class);
                startActivity(intent);
                finish();
            }
        });
        textView_d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,Game1.class);
                startActivity(intent);
                finish();
            }
        });
        textView_d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,Game3.class);
                startActivity(intent);
                finish();
            }
        });
        textView_d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,Game2.class);
                startActivity(intent);
                finish();
            }
        });
        textView_d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,Game4.class);
                startActivity(intent);
                finish();
            }
        });
        textView_d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,Game5.class);
                startActivity(intent);
                finish();
            }
        });
        textView_d7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,Game6.class);
                startActivity(intent);
                finish();
            }
        });

    }
}