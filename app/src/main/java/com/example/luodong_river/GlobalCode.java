package com.example.luodong_river;

import android.app.Activity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GlobalCode extends Activity {



    private static boolean mBackKeyPressed = false;
    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, R.string.toast_exit, Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }
}
