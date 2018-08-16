package com.example.jack.myshopping.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.jack.myshopping.R;
import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.base.BaseFragment;
import com.example.jack.myshopping.home.adapter.HomeRecycleAdapter;
import com.example.jack.myshopping.home.bean.ResultBeanData;
import com.example.jack.myshopping.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {


    RecyclerView recyclerView;
    private static final String TAG = "HomeFragment";
    View view;
    private ResultBeanData.ResultBean resultBean;
    private HomeRecycleAdapter adapter;
    private MaterialRefreshLayout materialRefreshLayout;
    int mDistance = 0;
    int BannerHeight = 350;//当距离在[0,255]变化时，透明度在[0,255之间变化]
    private Toolbar toolbar;
    private TextView toolbar_title_tv;

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initViewLayout() {
        if (view == null) {
            view = View.inflate(mContext, R.layout.fragment_home, null);
        }
        initView(view);


        initData();
        return view;
    }


    //滑动监听事件
    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        //dy:每一次竖直滑动增量 向下为正 向上为负
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //滑动的距离
            mDistance += dy;

            if (mDistance <= 0) {  //在顶部时完全透明
                toolbar_title_tv.setTextColor(Color.argb((int) 0, 0, 0, 0));
                toolbar.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));
            } else if (mDistance > 0 && mDistance <= BannerHeight) {  //在滑动高度中时，设置透明度百分比（当前高度/总高度）
                double d = (double) mDistance / BannerHeight;
                double alpha = (d * 255);
                toolbar_title_tv.setTextColor(Color.argb((int) alpha, 253, 253, 254));
                toolbar.setBackgroundColor(Color.argb((int) alpha, 33, 149, 242));
            } else { //滑出总高度 完全不透明
                toolbar.setBackgroundColor(Color.argb((int) 255, 33, 149, 242));
                toolbar_title_tv.setTextColor(Color.argb((int) 255, 253, 253, 254));
            }

        }
    };


    private void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar_title_tv = view.findViewById(R.id.toolbar_title_tv);
        materialRefreshLayout = view.findViewById(R.id.refresh);
        recyclerView = view.findViewById(R.id.home_RecyclerView);
        recyclerView.addOnScrollListener(mOnScrollListener);
        initToolBar(getActivity(), toolbar, 0, "首页", null, getResources().getColor(R.color.transparent), R.color.transparent);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onfinish() {
                super.onfinish();
                MyApplication.ShowMes(mContext, "刷新完成");
            }
        });
    }

    public void initData() {
        String url = "http://192.168.1.103:8080/atguigu/json/HOME_URL.json";
        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: HomeFragment" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            ResultBeanData resultBeanData = JSON.parseObject(response, ResultBeanData.class);
                            resultBean = resultBeanData.getResult();

                            adapter = new HomeRecycleAdapter(mContext, resultBean);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
                        }
                    }
                });


    }

}

