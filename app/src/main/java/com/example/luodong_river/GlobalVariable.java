package com.example.luodong_river;

import android.app.Application;

public class GlobalVariable extends Application {
    private String mode="";
    private int total;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }




}
