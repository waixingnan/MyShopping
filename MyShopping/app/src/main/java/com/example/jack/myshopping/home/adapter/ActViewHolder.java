package com.example.jack.myshopping.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jack.myshopping.R;
import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.home.bean.ResultBeanData;
import com.example.jack.myshopping.util.AlphaPageTransformer;
import com.example.jack.myshopping.util.Constants;
import com.example.jack.myshopping.util.ScaleInTransformer;

import java.util.List;

class ActViewHolder extends RecyclerView.ViewHolder {

    private final ViewPager viewPager;
    private final Context mContext;

    public ActViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        viewPager = itemView.findViewById(R.id.act_viewpager);
    }

    public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true, new AlphaPageTransformer(new ScaleInTransformer()));
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return act_info.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(mContext);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                //绑定数据
                Glide.with(mContext)
                        .load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url())
                        .into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        //点击事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                MyApplication.ShowMes(mContext, "position:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

}