package com.example.aliothman.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by AliOthman on 9/7/2017.
 */

public class Myrecriver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (intent.getAction().equalsIgnoreCase("com.example.Broadcast")) {
            String name = (String) bundle.get("service");
            Toast.makeText(context, name, Toast.LENGTH_LONG).show();

            NewMessageNotification newMessageNotification = new NewMessageNotification();
            newMessageNotification.notify(context,"Ali Othman",2);
        }
        if (intent.getAction().equalsIgnoreCase("android.intent.action.CAMERA_BUTTON")) {
            Toast.makeText(context, "Broad cast Press Button Telephone", Toast.LENGTH_LONG).show();
        } if (intent.getAction().equalsIgnoreCase("android.intent.action.ACTION_POWER_CONNECTED")) {
            Toast.makeText(context, "Broad cast Press ACTION_POWER_CONNECTED", Toast.LENGTH_LONG).show();
        } if (intent.getAction().equalsIgnoreCase("android.intent.action.AIRPLANE_MODE")) {
            Toast.makeText(context, "Broad cast Press AIRPLANE_MODE", Toast.LENGTH_LONG).show();
        }


        if (intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_RECEIVED")) {
            //sms received
            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdusObj.length];
                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }
                    // SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String senderNum = messages[i].getOriginatingAddress();
                    String message = messages[i].getMessageBody();//
                    Toast.makeText(context, senderNum + " :" + message, Toast.LENGTH_LONG).show();

                }
            }
        }

    }}
