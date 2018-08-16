package com.example.jack.myshopping.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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

    private void initView(View view) {
        materialRefreshLayout = view.findViewById(R.id.refresh);
        recyclerView = view.findViewById(R.id.home_RecyclerView);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolBar(getActivity(), toolbar, 0, "首页",
                null, getResources().getColor(R.color.white), R.color.colorPrimary);
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
                        Log.i(TAG, "onResponse: HomeFragment ==" + response);
                        if (response != null) {
                            processData(response);
                            adapter = new HomeRecycleAdapter(mContext, resultBean);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
                        }
                    }
                });

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

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
//        if (!TextUtils.isEmpty(json)) {
//            JSONObject jsonObject = JSON.parseObject(json);
//            //得到状态码
//            String code = jsonObject.getString("code");
//            String msg = jsonObject.getString("msg");
//            String result = jsonObject.getString("result");
//
//
//            //得到resultBean的数据
//            JSONObject ResultBean = JSON.parseObject(result, ResultBeanData.class);
//            String banner_info = ResultBean.getString("banner_info");
//            String act_info = ResultBean.getString("act_info");
//            String channel_info = ResultBean.getString("channel_info");
//            String hot_info = ResultBean.getString("hot_info");
//            String recommend_info = ResultBean.getString("recommend_info");
//            String seckill_info = ResultBean.getString("seckill_info");
//
//
//            resultBean = new ResultBeanData.ResultBean();
//
//            //设置BannerInfoBean数据
//            List<ResultBeanData.ResultBean.BannerInfoBean> bannerInfoBeans = JSON.parseArray(banner_info, ResultBeanData.ResultBean.BannerInfoBean.class);
//            resultBean.setBanner_info(bannerInfoBeans);
//            String value = jsonObject.getString("value");
//            ResultBeanData.ResultBean.BannerInfoBean.ValueBean valueBean = JSON.parseObject(value, ResultBeanData.ResultBean.BannerInfoBean.ValueBean.class);
//
//
//            //设置actInfoBeans数据
//            List<ResultBeanData.ResultBean.ActInfoBean> actInfoBeans = JSON.parseArray(act_info, ResultBeanData.ResultBean.ActInfoBean.class);
//            resultBean.setAct_info(actInfoBeans);
//
//            //设置channelInfoBeans的数据
//            List<ResultBeanData.ResultBean.ChannelInfoBean> channelInfoBeans = JSON.parseArray(channel_info, ResultBeanData.ResultBean.ChannelInfoBean.class);
//            resultBean.setChannel_info(channelInfoBeans);
//
//            //设置hotInfoBeans的数据
//            List<ResultBeanData.ResultBean.HotInfoBean> hotInfoBeans = JSON.parseArray(hot_info, ResultBeanData.ResultBean.HotInfoBean.class);
//            resultBean.setHot_info(hotInfoBeans);
//
//            //设置recommendInfoBeans的数据
//            List<ResultBeanData.ResultBean.RecommendInfoBean> recommendInfoBeans = JSON.parseArray(recommend_info, ResultBeanData.ResultBean.RecommendInfoBean.class);
//            resultBean.setRecommend_info(recommendInfoBeans);
//
//            //设置seckillInfoBean的数据
//            ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBean = JSON.parseObject(seckill_info, ResultBeanData.ResultBean.SeckillInfoBean.class);
//            resultBean.setSeckill_info(seckillInfoBean);
//
//        }
    }


}

