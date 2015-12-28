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
 * Created by rahul on 15-12-28.
 */
public class TimesAdapter extends BaseAdapter {
    private List<times> Times;
    Context context;

    public TimesAdapter(Context context,List<times> Times){
        this.Times = Times;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (Times==null)?0:Times.size();
    }

    @Override
    public Object getItem(int position) {
        return Times.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder{
        TextView list_title;
        TextView list_subtilte_info;
        ImageView list_status;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final times times = (times)getItem(position);
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.closestool_list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.list_status = (ImageView)convertView.findViewById(
                    R.id.list_status);
            viewHolder.list_title = (TextView)convertView.findViewById(
                    R.id.list_title);
            viewHolder.list_subtilte_info = (TextView)convertView.findViewById(
                    R.id.list_subtilte_info);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();

        }
        Log.d("TimesAdapter", "TimesAdapter,position="+position+"list_status="+times.status);
        viewHolder.list_status.setImageResource(times.status);
        viewHolder.list_title.setText(times.times_titel);
        viewHolder.list_subtilte_info.setText(times.times_subinfo);


        //对ListView中的每一行信息配置OnClick事件
        convertView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              Intent intent=new Intent();
                intent.setClass(context,Closestool_times_info.class);
                intent.putExtra("timesId", times.id);
                context.startActivity(intent);
            }

        });

        //对ListView中的每一行信息配置OnLongClick事件
        convertView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        return convertView;
    }
}
