package com.example.renhaifeng.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Rahul on 2015/11/9.
 */
public class MyBaseAdapter extends BaseAdapter {
    private List<Device> Devices;
    Context context;

    public MyBaseAdapter(Context context,List<Device> Devices){
        this.Devices = Devices;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (Devices==null)?0:Devices.size();
    }

    @Override
    public Object getItem(int position) {
        return Devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder{
        ImageView listDeviceItem01;
        TextView listDeviceItem02;
        TextView listDeviceItem03;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Device Device = (Device)getItem(position);
        ViewHolder viewHolder = null;
        if(convertView==null){
            Log.d("MyBaseAdapter", "新建convertView,position=" + position);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.list_device_item, null);

            viewHolder = new ViewHolder();
            viewHolder.listDeviceItem01 = (ImageView)convertView.findViewById(
                    R.id.listDeviceItem01);
            viewHolder.listDeviceItem02 = (TextView)convertView.findViewById(
                    R.id.listDeviceItem02);
            viewHolder.listDeviceItem03 = (TextView)convertView.findViewById(
                    R.id.listDeviceItem03);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            Log.d("MyBaseAdapter", "旧的convertView,position="+position);
        }
        Log.d("MyBaseAdapter", "Device.id="+Device.id+" Device.name="+Device.name);
        viewHolder.listDeviceItem01.setImageResource(Device.id);
        viewHolder.listDeviceItem02.setText(Device.name);
        viewHolder.listDeviceItem03.setText(Device.address);


        //对ListView中的每一行信息配置OnClick事件
        convertView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                if(Device.devicetype==Device.DEVICE_SOCKET)
                {
                    intent.setClass(context,MainActivity.class);
                }else if (Device.devicetype==Device.DEVICE_CLOSESTOOL)
                {
                    intent.setClass(context,Closestool.class);
                }
                context.startActivity(intent);
            }

        });

        //对ListView中的每一行信息配置OnLongClick事件
        convertView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Devices.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context,
                        "[convertView.setOnLongClickListener]点击了"+Device.name,
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return convertView;
    }

}