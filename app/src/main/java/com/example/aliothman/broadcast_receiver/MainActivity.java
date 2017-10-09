package com.example.aliothman.broadcast_receiver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckUserPermsions();

        if(!Myservice.Isrunning){
            Myservice.Isrunning = true;
             intentservice = new Intent(this, Myservice.class);
            startService(intentservice);
        }

    }

    Intent intentservice;

    public void btnclick(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.Broadcast");
        intent.putExtra("name","Ali Hasssan Othman");
        sendBroadcast(intent);

        if(Myservice.Isrunning){
            Myservice.Isrunning = false;
            stopService(intentservice);
        }
    }

    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if( (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
                    PackageManager.PERMISSION_GRANTED  )&&
                    (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                    PackageManager.PERMISSION_GRANTED  ) ){
                requestPermissions(new String[]{
                                android.Manifest.permission.RECEIVE_SMS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

    }
    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    // Permission Denied
                    Toast.makeText( this,"your message" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
