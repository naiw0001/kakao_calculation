package com.example.idc06.layout.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by idc06 on 2016-11-24.
 */

public class Splash extends AppCompatActivity {
    private ImageView img;
    private RelativeLayout splash;
    private Animation animation;
    private Handler handler,handlerimg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        splash = (RelativeLayout)findViewById(R.id.splash_layout);
        animation = AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        img = new ImageView(this);
        img.setImageResource(R.drawable.head);
        img.bringToFront();
        img.startAnimation(animation);
        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        img.setLayoutParams(imgParams);

        handlerimg = new Handler();
        handlerimg.postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.addView(img);
            }
        },2000);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);

    }




}
