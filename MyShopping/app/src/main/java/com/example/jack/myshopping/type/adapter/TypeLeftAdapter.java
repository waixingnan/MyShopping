package com.example.jack.myshopping.type.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.myshopping.R;

public class TypeLeftAdapter extends BaseAdapter {
    private final Context mContext;
    private int mSelect = 0;//选中项
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区"};

    public TypeLeftAdapter(Context mContext) {
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if(convertView==null){

            convertView=View.inflate(mContext, R.layout.item_type,null);
            viewholder=new ViewHolder();
            viewholder.tv_name = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewholder);
        }else {
            viewholder= (ViewHolder) convertView.getTag();
        }
        viewholder.tv_name.setText(titles[position]);

        if(mSelect==position){
            viewholder.tv_name.setBackgroundResource(R.color.white);
            viewholder.tv_name.setTextColor(Color.parseColor("#fd5353"));
        }else {
            viewholder.tv_name.setBackgroundResource(R.color.layoutbg);
            viewholder.tv_name.setTextColor(Color.parseColor("#3C3F41"));
        }
        return convertView;
    }

    class ViewHolder {
        public TextView tv_name;
    }
    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

}
