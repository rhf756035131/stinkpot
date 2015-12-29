package com.example.renhaifeng.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 15-12-28.
 */
public class Closestool extends AppCompatActivity {
    List<times> Times ;
    ListAdapter adapter = null;
    ListView listDevice = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closestool);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listDevice = (ListView)findViewById(R.id.closestool_list_item);
        //显示ListView
        initListAlltimes();
        showtimesAdapter();
    }
    public void initListAlltimes(){
        Times = new ArrayList<times>();
        Times.add(new times( "2015-12-26    23:11", "严重警报：检测到癌变可能",android.R.drawable.ic_notification_overlay,456789));
        Times.add(new times( "2015-12-27    08:10", "健康",android.R.drawable.presence_online,5659262));
        Times.add(new times("2015-12-27     10:31", "警告：PH值偏高",android.R.drawable.ic_notification_overlay,1651616));
        Times.add(new times( "2015-12-27    15:42", "健康",android.R.drawable.presence_online,25616165));
        Times.add(new times("2015-12-28    06:11", "警告：葡萄糖含量超标",android.R.drawable.ic_notification_overlay,1651616));
    }
    public void showtimesAdapter(){
        adapter = new TimesAdapter(this, Times);
        listDevice.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            finish();
            return true;
        }
        if(id== android.R.id.home)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
