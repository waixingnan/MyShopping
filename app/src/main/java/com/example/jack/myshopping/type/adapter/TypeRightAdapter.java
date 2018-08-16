package com.example.jack.myshopping.type.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.type.bean.TypeBean;

import java.util.List;

public class TypeRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    /*** 热卖*/
    public static final int HOT = 0;
    /*** 普通的*/
    public static final int ORDINARY = 1;
    private final List<TypeBean.ResultBean> result;
    /*** 当前的类型*/
    public int currentType;
    private final LayoutInflater layoutInflater;
    private List<TypeBean.ResultBean.HotProductListBean> hot_product_list;

    public TypeRightAdapter(Context context, List<TypeBean.ResultBean> result) {
        this.mContext = context;
        this.result = result;
        layoutInflater = LayoutInflater.from(context);
        if (result != null && result.size() > 0) {
            hot_product_list = result.get(0).getHot_product_list();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            return new HotViewHolder(layoutInflater.inflate(R.layout.item_hot_right, parent,false), mContext);
        } else {
            return new OrdinaryViewHolder(layoutInflater.inflate(R.layout.item_ordinary_grid_right, parent,false), mContext);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(hot_product_list);
        } else if(getItemViewType(position)==ORDINARY){
            OrdinaryViewHolder ordinaryViewHolder = (OrdinaryViewHolder) holder;
            ordinaryViewHolder.setData(result);
        }
    }



    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HOT) {
            currentType = HOT;
        } else {
            currentType = ORDINARY;
        }
        return currentType;
    }


}
