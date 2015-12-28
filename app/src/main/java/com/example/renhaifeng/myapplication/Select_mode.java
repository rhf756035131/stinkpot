package com.example.renhaifeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by renhaifeng on 2015/12/18.
 */
public class Select_mode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button BT_network_mode =(Button)findViewById(R.id.BT_network_mode);
        Button BT_direct_mode =(Button)findViewById(R.id.BT_direct_mode);
        BT_direct_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Select_mode.this,Closestool.class);
                startActivity(intent);
                finish();
            }
        });
        BT_network_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Select_mode.this,MainActivityNet.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
