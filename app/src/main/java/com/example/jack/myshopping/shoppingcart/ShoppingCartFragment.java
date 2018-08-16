package com.example.jack.myshopping.shoppingcart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.base.BaseFragment;
import com.example.jack.myshopping.home.bean.GoodsBean;
import com.example.jack.myshopping.shoppingcart.adapter.ShopCartAdapter;
import com.example.jack.myshopping.shoppingcart.utils.CartProvider;
import com.example.jack.myshopping.util.ToolbarUtil;

import java.util.List;

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {
    View view;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 0;
    /**
     * 完成状态
     */
    private static final int ACTION_COMPLETE = 1;

    private ShopCartAdapter adapter;
    private TextView toolbar_right_tv;

    private void findViews(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        TextView toolbar_title_tv = view.findViewById(R.id.toolbar_title_tv);
//        toolbar_title_tv.setText("购物车");
        toolbar_right_tv = view.findViewById(R.id.toolbar_right_tv);
//        toolbar_right_tv.setText("编辑");
        toolbar_right_tv.setTag(ACTION_EDIT);
        toolbar_right_tv.setOnClickListener(this);
       initToolBar(getActivity(),toolbar,0,"购物车","编辑",getResources().getColor(R.color.white),R.color.colorPrimary);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
    }


    @Override
    public View initViewLayout() {
        if (view == null) {
            view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);
        }
        findViews(view);
        initData();
        return view;
    }

    public void initData() {
        CartProvider cartProvider = CartProvider.getInstance();
        List<GoodsBean> datas = cartProvider.getDataFromLocal();
        if (datas != null && datas.size() > 0) {
            adapter = new ShopCartAdapter(mContext, datas, tvShopcartTotal, cartProvider, checkboxAll, cbAll);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }




    public static ShoppingCartFragment newInstance(String param1) {
        ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        shoppingCartFragment.setArguments(args);
        return shoppingCartFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                adapter.deleteData();
                adapter.showTotalPrice();
                //显示空空如也
                adapter.checkAll();
                break;
            case R.id.toolbar_right_tv:
                if(((int)toolbar_right_tv.getTag())==ACTION_EDIT){
                    llCheckAll.setVisibility(View.GONE);
                    llDelete.setVisibility(View.VISIBLE);
                    toolbar_right_tv.setTag(ACTION_COMPLETE);
                    toolbar_right_tv.setText("完成");
                }else if(((int)toolbar_right_tv.getTag())==ACTION_COMPLETE){
                    llCheckAll.setVisibility(View.VISIBLE);
                    llDelete.setVisibility(View.GONE);
                    toolbar_right_tv.setTag(ACTION_EDIT);
                    toolbar_right_tv.setText("编辑");
                }
                break;

        }
    }
}
