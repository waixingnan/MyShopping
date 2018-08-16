package com.example.jack.myshopping.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
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
    public void initToolBar(Activity activity, Toolbar toolbar, int leftDrawableId, String TitleText, String RightText, int TextColor, int ToolbarbackgroundColor) {
        if (toolbar == null) {
            Log.e("petter", "you have not provide a toolbar as action to Setint  ignore it!");
            return;
        }
        Toolbar mToolBar = toolbar;
        if (toolbar_left == null) {
            toolbar_left = (ImageButton) toolbar.findViewById(R.id.toolbar_left_ib);
            if (leftDrawableId == 0) {
                toolbar_left.getBackground().setAlpha(100);
            } else {
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


    /**
     * 设置标题栏背景透明度
     *
     * @param alpha 透明度
     */
    public void setSystemBarAlpha(Toolbar toolbar, int alpha) {
        if (alpha >= 125) {
            alpha = 125;
        } else {
            //标题栏渐变。a:alpha透明度 r:红 g：绿 b蓝
//        titlebar.setBackgroundColor(Color.rgb(57, 174, 255));//没有透明效果
//        titlebar.setBackgroundColor(Color.argb(alpha, 57, 174, 255));//透明效果是由参数1决定的，透明范围[0,255]
            toolbar.getBackground().setAlpha(alpha);
        }
    }

    /**
     * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     *
     * @param view
     * @param isHeight
     * @return
     */

    public  int getViewHeight(View view, boolean isHeight) {
        int result;
        if (view == null) return 0;
        if (isHeight) {
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(h, 0);
            result = view.getMeasuredHeight();
        } else {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(0, w);
            result = view.getMeasuredWidth();
        }
        return result;
    }


    /**
     * dp转换成px
     */
    public int dp2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     * px转换成dp
     */
    public int px2dp(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
    /**
     * sp转换成px
     */
    public int sp2px(Context context,float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }
    /**
     * px转换成sp
     */
    public int px2sp(Context context,float pxValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }


}
