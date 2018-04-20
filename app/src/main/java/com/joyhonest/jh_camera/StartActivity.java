package com.joyhonest.jh_camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        EventBus.getDefault().register(this);
    }




}
