package com.example.jack.myshopping.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.example.jack.myshopping.R;

import butterknife.ButterKnife;
import me.shihao.library.XStatusBarHelper;


/**
 * Created by asus on 2016/8/27.
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;
    TextView toolbar_title, toolbar_right;
    ImageButton toolbar_left;
    private View view;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = initViewLayout();
        ButterKnife.bind(this, view);

        return view;
    }

    public abstract View initViewLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    /***
     * @param toolbar           标题
     * @param leftDrawableId    左侧 菜单图片
     * @param TextColor        标题颜色
     * @param TitleText             标题内容
     * @param TextColor    右侧菜单颜色
     * @param RightText         右侧菜单内容
     */
    public void initToolBar(Activity activity,Toolbar toolbar, int leftDrawableId, String TitleText, String RightText,  int TextColor,int ToolbarbackgroundColor) {
        if (toolbar == null) {
            Log.e("petter", "you have not provide a toolbar as action to Setint  ignore it!");
            return;
        }
        Toolbar mToolBar = toolbar;
        if (toolbar_left == null) {
            toolbar_left = (ImageButton) toolbar.findViewById(R.id.toolbar_left_ib);
            if(leftDrawableId==0){
                toolbar_left.getBackground().setAlpha(100);
            }else {
                toolbar_left.setImageResource(leftDrawableId);
            }
        }
        if (toolbar_title == null) {
            toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title_tv);
            toolbar_title.setText(TitleText == null ? "" : TitleText);
            toolbar_title.setTextColor(TextColor);
        }
        if (toolbar_right == null) {
            toolbar_right = (TextView) toolbar.findViewById(R.id.toolbar_right_tv);
            toolbar_right.setText(RightText == null ? "" : RightText);
            toolbar_right.setTextColor(TextColor);
        }

        toolbar.setBackgroundResource(ToolbarbackgroundColor);
        initStatusBar(activity, toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
    }

    public void initStatusBar(Activity activity, Toolbar toolbar) {
        XStatusBarHelper.forceFitsSystemWindows(activity);
        XStatusBarHelper.immersiveStatusBar(activity);
        XStatusBarHelper.setHeightAndPadding(activity, toolbar);
    }
}
