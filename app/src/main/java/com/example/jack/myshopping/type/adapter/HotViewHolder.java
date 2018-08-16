package com.example.jack.myshopping.type.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.type.bean.TypeBean;

import java.util.List;

class HotViewHolder extends RecyclerView.ViewHolder {
    private final Context mContext;
    private final RecyclerView recyclerView_right;

    public HotViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext=mContext;
        recyclerView_right = itemView.findViewById(R.id.recyclerView_right);
    }

    public void setData(List<TypeBean.ResultBean.HotProductListBean> hot_product_list) {
        HotAdapter hotAdapter=new HotAdapter(mContext,hot_product_list);
        recyclerView_right.setAdapter(hotAdapter);
        recyclerView_right.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
    }
}
