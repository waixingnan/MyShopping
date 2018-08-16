package com.example.jack.myshopping.shoppingcart.activity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.home.bean.GoodsBean;
import com.example.jack.myshopping.shoppingcart.adapter.ShopCartAdapter;
import com.example.jack.myshopping.shoppingcart.utils.CartProvider;
import com.example.jack.myshopping.util.ToolbarUtil;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener{
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
    private TextView toolbar_left_tv;
    private TextView toolbar_title_tv;

    @SuppressLint("NewApi")
    private void findViews( ) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_backup_arrow_left_white_24dp);
        setSupportActionBar(toolbar);


        toolbar_title_tv = findViewById(R.id.toolbar_title_tv);
        toolbar_title_tv.setText("购物车");

        toolbar_right_tv = findViewById(R.id.toolbar_right_tv);
        toolbar_right_tv.setText("编辑");

        toolbar_right_tv.setTag(ACTION_EDIT);
        toolbar_right_tv.setOnClickListener(this);

        ToolbarUtil.getInstance().initStatusBar(this,toolbar);
//        ToolbarUtil.getInstance().initToolbar(getActivity(), toolbar, null, "购物车",
//                "编辑", R.color.white, R.color.blue);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
    }

    public void initData() {
        CartProvider cartProvider = CartProvider.getInstance();
        List<GoodsBean> datas = cartProvider.getDataFromLocal();
        if (datas != null && datas.size() > 0) {
            adapter = new ShopCartAdapter(this, datas, tvShopcartTotal, cartProvider, checkboxAll, cbAll);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            recyclerview.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shoppingcart);
        findViews();
        initData();
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
