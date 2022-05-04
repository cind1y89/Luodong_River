package com.example.luodong_river;

import android.app.Application;

public class GlobalVariable extends Application {
    private String mode="";

    //setting mode
    public void setMode(String mode){
        this.mode=mode;
    }

    //get mode
    public String getMode(){
        return mode;
    }
}
