package com.example.jack.myshopping.type;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.base.BaseFragment;
import com.example.jack.myshopping.type.adapter.TypeLeftAdapter;
import com.example.jack.myshopping.type.adapter.TypeRightAdapter;
import com.example.jack.myshopping.type.bean.TypeBean;
import com.example.jack.myshopping.util.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class TypeFragment extends BaseFragment {
    private static final String TAG = "TypeFragment";
    View view;
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    private TypeLeftAdapter leftAdapter;
    private ListView lv_left;
    private RecyclerView rv_right;
    private List<TypeBean.ResultBean> result;
    private boolean isFirst = true;

    public static TypeFragment newInstance(String param1) {
        TypeFragment typeFragment = new TypeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        typeFragment.setArguments(args);
        return typeFragment;
    }


    @Override
    public View initViewLayout() {
        if (view == null) {
            view = View.inflate(mContext, R.layout.fragment_type, null);
        }
        initView(view);
        getDataFromNet(urls[0]);
        return view;
    }

    void initView(View view) {
        lv_left = (ListView) view.findViewById(R.id.lv_left);
        rv_right = (RecyclerView) view.findViewById(R.id.rv_right);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolBar(getActivity(), toolbar, 0, "分类",
                null, getResources().getColor(R.color.white), R.color.colorPrimary);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG, "onResponse: ----" + id);
                //解析数据
                processData(response);
                if (isFirst) {
                    leftAdapter = new TypeLeftAdapter(mContext);
                    lv_left.setAdapter(leftAdapter);
                }
                initListener(leftAdapter);

                //解析右边数据
                TypeRightAdapter rightAdapter = new TypeRightAdapter(mContext, result);
                rv_right.setAdapter(rightAdapter);
                rv_right.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            }

        });
    }

    private void initListener(final TypeLeftAdapter leftAdapter) {
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftAdapter.changeSelected(position);
                if (position != 0) {
                    isFirst = false;
                }
                getDataFromNet(urls[position]);
                leftAdapter.notifyDataSetChanged();
            }
        });
    }


    private void processData(String json) {
        Gson gson = new Gson();
        TypeBean typeBean = gson.fromJson(json, TypeBean.class);
        result = typeBean.getResult();
    }

}

