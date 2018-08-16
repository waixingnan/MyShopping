package com.example.jack.myshopping.type.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.type.bean.TypeBean;

import java.util.List;

class OrdinaryViewHolder extends RecyclerView.ViewHolder {

    private final GridView gridView;
    private final Context mContext;

    public OrdinaryViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        gridView = itemView.findViewById(R.id.grid_ordinary_right);
    }

    public void setData(List<TypeBean.ResultBean> result) {
        GridOrdinaryAdapter gridOrdinaryAdapter = new GridOrdinaryAdapter(mContext, result);
        gridView.setAdapter(gridOrdinaryAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApplication.ShowMes(mContext, "" + position);
            }
        });

    }
}