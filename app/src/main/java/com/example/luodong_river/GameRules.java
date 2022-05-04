package com.example.luodong_river;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GameRules extends GlobalCode {

    Button button_start;
    GlobalVariable globalVariable_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);

        globalVariable_mode= (GlobalVariable) getApplicationContext();
        Log.d("mode_getApplication_R",globalVariable_mode.getMode());

        button_start=findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalVariable_mode= (GlobalVariable) getApplicationContext();
                Log.d("mode_getApplication_R",globalVariable_mode.getMode());

                Intent intent;
                intent=new Intent(GameRules.this,Game1.class);
                startActivity(intent);

            }
        });
    }
}