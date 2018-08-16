package com.example.jack.myshopping.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.home.bean.ResultBeanData;

public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /*** 横幅广告*/
    public static final int BANNER = 0;
    /*** 频道*/
    public static final int CHANNEL = 1;
    /*** 活动*/
    public static final int ACT = 2;
    /*** 热卖*/
    public static final int HOT = 5;
    /*** 推荐*/
    public static final int RECOMMEND = 4;
    /*** 秒杀*/
    public static final int SECKILL = 3;
    /*** 当前类型*/
    public int currentType = BANNER;
    public Context mContext;
    public ResultBeanData.ResultBean resultBean;
    private LayoutInflater layoutInflater;

    public HomeRecycleAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(layoutInflater.inflate(R.layout.banner_viewpager, null), mContext);
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(layoutInflater.inflate(R.layout.channel_layout, null), mContext);
        } else if (viewType == ACT) {
            return new ActViewHolder(layoutInflater.inflate(R.layout.act_layout, null), mContext);
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(layoutInflater.inflate(R.layout.seckill_layout, null), mContext);
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(layoutInflater.inflate(R.layout.recommend_item, null), mContext);
        }else if (viewType == HOT) {
            return new HotViewHolder(layoutInflater.inflate(R.layout.hot_layout, null), mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }


}
