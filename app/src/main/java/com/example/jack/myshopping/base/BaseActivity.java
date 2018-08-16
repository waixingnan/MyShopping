package com.example.jack.myshopping.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jack.myshopping.R;

import me.shihao.library.XStatusBarHelper;


/**
 * Created by asus on 2016/8/28.
 */
public class BaseActivity extends AppCompatActivity {
    TextView toolbar_title, toolbar_right;
    ImageButton toolbar_left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置窗口无title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    /***
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






//    /***
//     * @param toolbar           标题
//     * @param leftDrawableId    左侧 菜单图片
//     * @param TitleTextColor        标题颜色
//     * @param TitleText             标题内容
//     * @param RightTextColor    右侧菜单颜色
//     * @param RightText         右侧菜单内容
//     */
//    public void initToolBar(Toolbar toolbar, int leftDrawableId, String TitleText, int TitleTextColor, String RightText, int RightTextColor, int ToolbarbackgroundColor) {
//        if (toolbar == null) {
//            Log.e("petter", "you have not provide a toolbar as action to Setint  ignore it!");
//            return;
//        }
//        Toolbar mToolBar = toolbar;
//        if (toolbar_left == null) {
//            toolbar_left = (ImageButton) toolbar.findViewById(R.id.toolbar_left_ib);
//            if(leftDrawableId==0){
//                toolbar_left.getBackground().setAlpha(100);
//            }else {
//                toolbar_left.setImageResource(leftDrawableId);
//            }
//        }
//        if (toolbar_title == null) {
//            toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title_tv);
//            toolbar_title.setText(TitleText == null ? "" : TitleText);
//            toolbar_title.setTextColor(TitleTextColor);
//        }
//        if (toolbar_right == null) {
//            toolbar_right = (TextView) toolbar.findViewById(R.id.toolbar_right_tv);
//            toolbar_right.setText(RightText == null ? "" : RightText);
//            toolbar_right.setTextColor(RightTextColor);
//        }
//
//        toolbar.setBackgroundResource(ToolbarbackgroundColor);
//
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//    }

    /***
     * 带返回按钮 的标题 没有其它菜单
     *
     * @param toolbar
     * @param color
     * @param titleText
     */
    protected void initToolBarAsCommonActionBarWithBackIcon(Toolbar toolbar, int color, String titleText, int bgRes) {
        if (color == Color.WHITE) {
//            initToolBar(toolbar, R.drawable.loginback, null, -1, color, titleText, 0, -1, null, bgRes);
        } else if (color == Color.BLACK) {
//            initToolBar(toolbar, R.drawable.background_back, null, -1, color, titleText, 0, -1, null, bgRes);
        }
    }

    /**
     * 初始化一个指定背景色的actionBar  没有内容
     *
     * @param toolbar
     */
    protected void initToolBarWithNoContent(Toolbar toolbar, int bgRes) {
//        initToolBar(toolbar, 0, null, -1, -1, null, 0, -1, null, bgRes > 0 ? bgRes : R.color.transparent);

    }


    /**
     * 初始一个菜单都为图片的Actionbar
     *
     * @param toolbar
     * @param leftDrawableid
     * @param color
     * @param titleText
     * @param rightDrawableId
     * @param bgRes
     */
    protected void initToolBarWithBothImgIconActionBar(Toolbar toolbar, int leftDrawableid, int color, String titleText, int rightDrawableId, int bgRes) {

//        initToolBar(toolbar, leftDrawableid, null, -1, color, titleText, rightDrawableId, -1, null, bgRes);
    }


    /**
     * 初始化一个左侧为图片，右侧为文字的ActionBar
     *
     * @param toolbar
     * @param leftDrawableid
     * @param titleColor
     * @param titleText
     * @param rightTextColor
     * @param rightText
     * @param bgRes
     */
    protected void intToolBarWithLeftIconAndRightTextMenuActionBar(Toolbar toolbar, int leftDrawableid, int titleColor, String titleText, int rightTextColor, String rightText, int bgRes) {

//        initToolBar(toolbar, leftDrawableid, null, -1, titleColor, titleText, 0, rightTextColor, rightText, bgRes);
    }


}
