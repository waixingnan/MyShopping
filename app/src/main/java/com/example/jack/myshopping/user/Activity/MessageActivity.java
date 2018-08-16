package com.example.jack.myshopping.user.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.base.BaseActivity;
import com.example.jack.myshopping.user.adapter.MesAdapter;
import com.example.jack.myshopping.user.bean.MessageBean;
import com.example.jack.myshopping.user.util.MesProvider;
import com.example.jack.myshopping.user.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MessageActivity";
    private ListView listView;
    private ImageButton toolbarLeftIb;
    private List<MessageBean> messageBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageBeans = new ArrayList<MessageBean>();
        messageBeans = (List<MessageBean>) SharedPreferenceUtil.getInstence().get("Mes", "MesData");

        if (messageBeans != null && messageBeans.size() >= 0) {
            setContentView(R.layout.activity_message);
            initView();
        } else {
            setContentView(R.layout.activity_empty);
        }
        initbar();
    }

    private void initbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        initToolBar(this, toolbar, R.drawable.ic_backup_arrow_left_white_24dp, "消息中心", null,
                getResources().getColor(R.color.white), R.color.colorPrimary);
        toolbarLeftIb = findViewById(R.id.toolbar_left_ib);
        toolbarLeftIb.setOnClickListener(this);
    }

    private void initView() {

//        if (messageBeans != null) {
//            for (int i=0;i<messageBeans.size();i++){
//
//            }
//
//        } else {
//            Log.i(TAG, "onClick: ------null--->>>");
//        }

        listView = findViewById(R.id.mes_listview);
        MesAdapter mesAdapter = new MesAdapter(this, messageBeans);
        listView.setAdapter(mesAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_left_ib:
                onBackPressed();
                break;
        }
    }
}
