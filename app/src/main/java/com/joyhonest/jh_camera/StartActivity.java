package com.joyhonest.jh_camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.joyhonest.wifination.wifination;


//import com.joyhonest.wifination.wifination;

//import org.simple.eventbus.EventBus;


public class StartActivity extends AppCompatActivity {
    private  String TAG = "Wifi_Camera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        wifination.naInit("");
     //   EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wifination.naStop();
    }


    //    @Subscriber(tag="Key_Pressed")
//    private  void key_Press(Integer nKeyA)
//    {
//        int nKye = nKeyA.intValue();
//        Log.e(TAG,"Key = "+nKye);
//    }
//
//    @Subscriber(tag = "SDStatus_Changed")
//    private void  _OnStatusChanged(int nStatus)
//    {
//        Log.e(TAG,"Status = "+nStatus);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
}
