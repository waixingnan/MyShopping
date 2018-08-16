package com.example.jack.myshopping.type.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jack.myshopping.R;
import com.example.jack.myshopping.type.bean.TypeBean;
import com.example.jack.myshopping.util.Constants;

import java.util.List;

class GridOrdinaryAdapter extends BaseAdapter {
    private final Context mContext;
    List<TypeBean.ResultBean.ChildBean> child;

    public GridOrdinaryAdapter(Context mContext, List<TypeBean.ResultBean> result) {
        this.mContext = mContext;
        if (result != null && result.size() > 0) {
            child = result.get(0).getChild();
        }
    }

    @Override
    public int getCount() {
        return child.size();
    }

    @Override
    public Object getItem(int position) {
        return child.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        OrdinaryHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_ordinary_right, null);
            holder = new OrdinaryHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OrdinaryHolder) convertView.getTag();
        }
        //加载图片
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE + child.get(position).getPic())
                .into(holder.iv_ordinary_right);
        //设置名称
        holder.tv_ordinary_right.setText(child.get(position).getName());


        return convertView;
    }

    static class OrdinaryHolder {

        private final ImageView iv_ordinary_right;
        private final TextView tv_ordinary_right;

        public OrdinaryHolder(View convertView) {
            iv_ordinary_right = convertView.findViewById(R.id.iv_ordinary_right);
            tv_ordinary_right = convertView.findViewById(R.id.tv_ordinary_right);
        }
    }
}
