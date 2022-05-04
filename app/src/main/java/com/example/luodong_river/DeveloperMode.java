package com.example.luodong_river;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DeveloperMode extends GlobalCode {

    Button button_go;
    GlobalVariable globalVariable_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_mode);

        globalVariable_mode= (GlobalVariable) getApplicationContext();
        Log.d("mode_getApplication_D",globalVariable_mode.getMode());

        button_go=findViewById(R.id.button_Dgo);
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeveloperMode.this,GameRules.class);
                startActivity(intent);
                finish();
                Log.d("mode_button_rules_D",globalVariable_mode.getMode());
            }
        });
    }
}