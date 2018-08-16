package com.example.jack.myshopping.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.home.bean.ResultBeanData;

import java.util.List;

public class ChannelViewHolder extends RecyclerView.ViewHolder {

    private final Context mContext;
    private final GridView gridView;

    public ChannelViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        gridView = itemView.findViewById(R.id.gv_channel);
    }

    public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        ChannelAdapter channelAdapter = new ChannelAdapter(this.mContext, channel_info);
        gridView.setAdapter(channelAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApplication.ShowMes(mContext, "第" + position + "个");
            }
        });

    }
}