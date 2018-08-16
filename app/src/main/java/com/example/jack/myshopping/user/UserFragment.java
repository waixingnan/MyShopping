package com.example.jack.myshopping.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.example.jack.myshopping.R;
import com.example.jack.myshopping.activity.LoginActivity;
import com.example.jack.myshopping.base.BaseFragment;
import com.example.jack.myshopping.type.TypeFragment;
import com.example.jack.myshopping.user.Activity.MessageActivity;
import com.example.jack.myshopping.user.bean.MessageBean;
import com.example.jack.myshopping.user.util.MesProvider;
import com.example.jack.myshopping.user.util.MyScrollView;
import com.example.jack.myshopping.user.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends BaseFragment implements View.OnClickListener, MyScrollView.OnScrollChangedListener {
    private static final String TAG = "UserFragment";
    View view;
    private Toolbar toolbar;
    private TextView toolbar_title_tv;

    private float headerHeight = 500;//顶部高度
    private float ToolbarHeight = 50;//顶部最低高度，即Bar的高度

    @Override
    public View initViewLayout() {
        if (view == null) {
            view = View.inflate(mContext, R.layout.fragment_user, null);
        }
        findView(view);
        return view;
    }


    private void findView(View view) {

        toolbar = view.findViewById(R.id.toolbar);
        initToolBar(getActivity(), toolbar, 0, "个人中心", null,
                getResources().getColor(R.color.transparent), R.color.transparent);
        toolbar_title_tv = view.findViewById(R.id.toolbar_title_tv);

        CircleImageView profile_image = view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        SuperTextView mes_centre = view.findViewById(R.id.mes_centre);
        mes_centre.setOnClickListener(this);
        MyScrollView ScrollView = view.findViewById(R.id.user_ScrollView);
        ScrollView.setOnScrollChangedListener((MyScrollView.OnScrollChangedListener) this);

    }

    public static UserFragment newInstance(String param1) {
        UserFragment userFragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        userFragment.setArguments(args);
        return userFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mes_centre:
                startActivity(new Intent(mContext, MessageActivity.class));
//                List<MessageBean> messageBeans = new ArrayList<MessageBean>();
//                messageBeans = (List<MessageBean>) SharedPreferenceUtil.getInstence().get("Mes", "MesData");
//                if (messageBeans != null ) {
//                    Log.i(TAG, "onClick: -----getTitle---->>>" + messageBeans.get(0).getTitle());
//                    Log.i(TAG, "onClick: -----getMes---->>>" + messageBeans.get(0).getMes());
//                    Log.i(TAG, "onClick: -----getDate---->>>" + messageBeans.get(0).getDate());
//                } else {
//                    Log.i(TAG, "onClick: ------null--->>>");
//                }
                break;
        }
    }

    @Override
    public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        //        //Y轴偏移量
        float scrollY = who.getScrollY();
        //变化率
        float headerBarOffsetY = headerHeight - ToolbarHeight;//Toolbar与header高度的差值
        float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
        //Toolbar背景色透明度
        if (scrollY <= 0) {
            toolbar_title_tv.setTextColor(Color.argb((int) 0, 0, 0, 0));
            toolbar.setBackgroundColor(Color.argb((int) 0, 0, 0, 0));
        } else if (scrollY > 0 && scrollY <= headerHeight) {
            toolbar.setBackgroundColor(Color.argb((int) (offset * 255), 33, 149, 242));
            toolbar_title_tv.setTextColor(Color.argb((int) (offset * 255), 253, 253, 254));
        } else {
            toolbar.setBackgroundColor(Color.argb((int) 255, 33, 149, 242));
            toolbar_title_tv.setTextColor(Color.argb((int) 255, 253, 253, 254));
        }
    }

}
