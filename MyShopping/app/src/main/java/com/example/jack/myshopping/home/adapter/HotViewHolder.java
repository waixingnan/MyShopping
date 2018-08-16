package com.example.jack.myshopping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.activity.GoodsInfoActivity;
import com.example.jack.myshopping.home.bean.GoodsBean;
import com.example.jack.myshopping.home.bean.ResultBeanData;

import java.util.List;

class HotViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_more_hot;
    private GridView gv_hot;
    private Context mContext;
    public static final String GOODS_BEAN = "goods_bean";

    public HotViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
        tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);

    }

    public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> data) {
        HotGridViewAdapter adapter = new HotGridViewAdapter(mContext, data);
        gv_hot.setAdapter(adapter);

        //点击事件
        gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cover_price = data.get(position).getCover_price();
                String name = data.get(position).getName();
                String figure = data.get(position).getFigure();
                String product_id = data.get(position).getProduct_id();
                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra(GOODS_BEAN, goodsBean);
                mContext.startActivity(intent);
            }
        });
    }
}
