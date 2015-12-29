package com.example.renhaifeng.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by renhaifeng on 2015/12/18.
 */
public class MainActivityNet extends AppCompatActivity {
    //定义Person
    List<Device> Devices ;
    ListAdapter adapter = null;
    ListView listDevice = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_net);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listDevice = (ListView)findViewById(R.id.listdevice);
        //显示ListView
        initListAllPersons();
        showByMyBaseAdapter();
    }
    public void initListAllPersons(){
        Devices = new ArrayList<Device>();
        Devices.add(new Device(R.drawable.socket, "烤箱插座", "连接",Device.DEVICE_SOCKET));
        Devices.add(new Device(R.drawable.socket, "取暖器插座", "连接",Device.DEVICE_SOCKET));
        Devices.add(new Device(R.drawable.socket, "电热毯插座", "连接",Device.DEVICE_SOCKET));
        Devices.add(new Device(R.drawable.closestool, "马桶", "连接",Device.DEVICE_CLOSESTOOL));
        Devices.add(new Device(R.drawable.socket, "热水器插座", "连接",Device.DEVICE_SOCKET));
    }
    public void showByMyBaseAdapter(){
        adapter = new MyBaseAdapter(this, Devices);
        listDevice.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_net, menu);
        return true;
    }
    private void inputIpAddress() {
        final EditText editIpAddress = new EditText(this);
        editIpAddress.setText("192.168.1.2");
        new AlertDialog.Builder(this).setTitle("请输入IP地址")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(editIpAddress)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
//                        new StatusTask(MainActivity.this,"Query Status").execute();
                        Devices.add(new Device(R.drawable.socket, "智能插座", "断开",Device.DEVICE_CLOSESTOOL));
                        listDevice.setAdapter(adapter);
                    }
                }).setNegativeButton("取消", null).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(MainActivityNet.this, "action_settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_add:
                inputIpAddress();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
