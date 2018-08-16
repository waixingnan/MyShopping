package com.example.jack.myshopping.type.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jack.myshopping.R;
import com.example.jack.myshopping.activity.GoodsInfoActivity;
import com.example.jack.myshopping.home.bean.GoodsBean;
import com.example.jack.myshopping.type.bean.TypeBean;
import com.example.jack.myshopping.util.Constants;

import java.util.List;

class HotAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<TypeBean.ResultBean.HotProductListBean> hot_product_list;
    private final LayoutInflater inflater;

    public HotAdapter(Context mContext, List<TypeBean.ResultBean.HotProductListBean> hot_product_list) {
        this.mContext = mContext;
        this.hot_product_list = hot_product_list;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotItemHolder(inflater.inflate(R.layout.hot_item, null), mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        HotItemHolder hotItemHolder = (HotItemHolder) holder;
        //请求图片
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE + hot_product_list.get(position).getFigure())
                .into(hotItemHolder.hot_iv_figure);
        hotItemHolder.hot_tv_cover_price.setText("￥" + hot_product_list.get(position).getCover_price());
        hotItemHolder.hot_iv_figure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cover_price = hot_product_list.get(position).getCover_price();
                String name = hot_product_list.get(position).getName();
                String figure = hot_product_list.get(position).getFigure();
                String product_id = hot_product_list.get(position).getProduct_id();
                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);

                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra("goods_bean", goodsBean);
                mContext.startActivity(intent);
//                MyApplication.ShowMes(mContext,""+value);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hot_product_list.size();
    }

    class HotItemHolder extends RecyclerView.ViewHolder {

        private final TextView hot_tv_cover_price;
        private final ImageView hot_iv_figure;

        public HotItemHolder(View itemView, Context mContext) {
            super(itemView);
            hot_iv_figure = itemView.findViewById(R.id.hot_iv_figure);
            hot_tv_cover_price = itemView.findViewById(R.id.hot_tv_cover_price);
        }
    }
}
