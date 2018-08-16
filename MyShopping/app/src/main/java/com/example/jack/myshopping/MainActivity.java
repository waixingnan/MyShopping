package com.example.jack.myshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jack.myshopping.home.HomeFragment;
import com.example.jack.myshopping.shoppingcart.ShoppingCartFragment;
import com.example.jack.myshopping.type.TypeFragment;
import com.example.jack.myshopping.user.UserFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    Fragment homefragment;
    Fragment typefragment;
    Fragment shoppingcartfragment;
    Fragment uerfragment;
    private static final String TAG = "MainActivity";
    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bottomNavigationBar = findViewById(R.id.navigation);
        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor(R.color.blue) //选中颜色
                .setInActiveColor(R.color.gray) //未选中颜色
                .setBarBackgroundColor(R.color.white);//导航栏背景色

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_type_list_white_24dp, "分类"))
                .addItem(new BottomNavigationItem(R.drawable.ic_add_shopping_cart_white_24dp, "购物车"))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_white_24dp, "个人中心"))
                .setFirstSelectedPosition(0)
                .initialise(); //initialise 一定要放在 所有设置的最后一项
        SetFragment(0);
    }

    private void SetFragment(int value) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (value) {
            case 0:
                HidFragment(transaction);
                if (homefragment == null) {
                    homefragment = HomeFragment.newInstance("首页");
                    transaction.add(R.id.framelayout, homefragment);
                } else {
                    transaction.show(homefragment);
                }
                break;
            case 1:
                HidFragment(transaction);
                if (typefragment == null) {
                    typefragment = TypeFragment.newInstance("分类");
                    transaction.add(R.id.framelayout, typefragment);
                } else {
                    transaction.show(typefragment);
                }
                break;
            case 2:
                HidFragment(transaction);
                if (shoppingcartfragment == null) {
                    shoppingcartfragment = ShoppingCartFragment.newInstance("购物车");
                    transaction.add(R.id.framelayout, shoppingcartfragment);
                } else {
                    transaction.show(shoppingcartfragment);
                }
                break;
            case 3:
                HidFragment(transaction);
                if (uerfragment == null) {
                    uerfragment = UserFragment.newInstance("个人中心");
                    transaction.add(R.id.framelayout, uerfragment);
                } else {
                    transaction.show(uerfragment);
                }
                break;
        }
        transaction.commit();
    }

    private void HidFragment(FragmentTransaction transaction) {
        if (homefragment != null) {
            transaction.hide(homefragment);
        }
        if (typefragment != null) {
            transaction.hide(typefragment);
        }
        if (shoppingcartfragment != null) {
            transaction.hide(shoppingcartfragment);
        }
        if (uerfragment != null) {
            transaction.hide(uerfragment);
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                SetFragment(0);
                break;
            case 1:
                SetFragment(1);
                break;
            case 2:
                SetFragment(2);
                break;
            case 3:
                SetFragment(3);
                break;
        }
        Log.i(TAG, "onTabSelected:========= " + position);

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
