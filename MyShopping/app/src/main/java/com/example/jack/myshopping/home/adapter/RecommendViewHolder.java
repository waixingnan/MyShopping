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

class RecommendViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_more_recommend;
    private GridView gv_recommend;
    private Context mContext;
    public static final String GOODS_BEAN = "goods_bean";

    public RecommendViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
        gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);
    }


    public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
        RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(mContext, data);
        gv_recommend.setAdapter(adapter);

        //点击事件
        gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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