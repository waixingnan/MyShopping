package com.example.jack.myshopping.util;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jack.myshopping.R;

import me.shihao.library.XStatusBarHelper;

public class ToolbarUtil {
    TextView toolbar_title, toolbar_right;
    ImageButton toolbar_left;
    public android.support.v7.widget.Toolbar mToolbar;

    private static ToolbarUtil instance;

    private ToolbarUtil() {
    }

    public static ToolbarUtil getInstance() {
        if (instance == null) {
            instance = new ToolbarUtil();
        }
        return instance;
    }



    /***
     * @param activity               Activity 实例
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
