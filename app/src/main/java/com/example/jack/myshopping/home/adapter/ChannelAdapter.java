package com.example.jack.myshopping.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jack.myshopping.R;
import com.example.jack.myshopping.home.bean.ResultBeanData;
import com.example.jack.myshopping.util.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChannelAdapter extends BaseAdapter {

    private Context mContext;
    List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info;

    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvChannel.setText(channel_info.get(position).getChannel_name());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + channel_info.get(position).getImage()).into(viewHolder.ivChannel);
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.iv_channel)
        ImageView ivChannel;
        @Bind(R.id.tv_channel)
        TextView tvChannel;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }


}
