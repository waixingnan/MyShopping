package com.example.jack.myshopping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.activity.GoodsInfoActivity;
import com.example.jack.myshopping.home.bean.GoodsBean;
import com.example.jack.myshopping.home.bean.ResultBeanData;

import java.text.SimpleDateFormat;
import java.util.Date;


class SeckillViewHolder extends RecyclerView.ViewHolder {
    private TextView tvMore;
    private RecyclerView recyclerView;
    public Context mContext;
    private TextView tvTime;
    private boolean isFirst = true;
    private int dt;
    public static final String GOODS_BEAN = "goods_bean";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                dt = dt - 1000;
                SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                tvTime.setText(sd.format(new Date(dt)));

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt == 0) {
                    handler.removeMessages(0);
                }
            }

        }
    };


    public SeckillViewHolder(View itemView, Context mContext) {
        super(itemView);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time_seckill);
        tvMore = (TextView) itemView.findViewById(R.id.tv_more_seckill);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        this.mContext = mContext;
    }

    public void setData(final ResultBeanData.ResultBean.SeckillInfoBean data) {
        //设置时间
        if (isFirst) {
            dt = (int) (Integer.parseInt(data.getEnd_time()) - (Integer.parseInt(data.getStart_time())));
            isFirst = false;
        }

        //设置RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, data);
        recyclerView.setAdapter(adapter);

        //倒计时
        handler.sendEmptyMessageDelayed(0, 1000);

        //点击事件
        adapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
            @Override
            public void onClick(int position) {
                ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = data.getList().get(position);
                String name = listBean.getName();
                String cover_price = listBean.getCover_price();
                String figure = listBean.getFigure();
                String product_id = listBean.getProduct_id();
                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra(GOODS_BEAN, goodsBean);
                mContext.startActivity(intent);
            }
        });
    }
}