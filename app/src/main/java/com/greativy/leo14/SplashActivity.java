package com.greativy.leo14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ShowSplash();


    }
    private final int splashTime = 1000;
    public void ShowSplash() {
        Handler h = new Handler();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent Intent_StartMain = new Intent();
                Intent_StartMain.setClass(SplashActivity.this, MainListActivity.class);
                startActivity(Intent_StartMain);
                SplashActivity.this.finish();

            }
        };

        h.postDelayed(r,splashTime);
    }


}
