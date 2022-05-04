package com.example.luodong_river;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Title extends GlobalCode {

    Button button_go,button_setting;
    GlobalVariable globalVariable_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        button_go=findViewById(R.id.button_Tgo);
        button_setting=findViewById(R.id.button_setting);

        globalVariable_mode= (GlobalVariable) getApplicationContext();
        Log.d("mode_getApplication_T",globalVariable_mode.getMode());

        button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalVariable_mode.setMode("developer");
                String mode=globalVariable_mode.getMode();

                Intent intent;
                if(mode.equals("developer")){
                    intent = new Intent(Title.this, DeveloperMode.class);
                }else{
                    intent = new Intent(Title.this, GameRules.class);
                }
                startActivity(intent);
                finish();
            }
        });


        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalVariable_mode.setMode("");
                Log.d("mode_button_go_T",globalVariable_mode.getMode());

                Intent intent=new Intent(Title.this,GameRules.class);
                startActivity(intent);
                finish();


            }
        });



    }


}