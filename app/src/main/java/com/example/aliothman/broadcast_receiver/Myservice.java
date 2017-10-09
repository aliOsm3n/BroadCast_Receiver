package com.example.aliothman.broadcast_receiver;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by AliOthman on 9/7/2017.
 */

public class Myservice extends IntentService {

    public  static  boolean Isrunning = false;

    public Myservice() {
        super("Myservice");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        while (Isrunning){

            Intent intent1 = new Intent();
            intent1.setAction("com.example.Broadcast");
            intent1.putExtra("service","The Service is Open");
            sendBroadcast(intent1);

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
