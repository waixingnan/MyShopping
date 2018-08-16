package com.example.jack.myshopping.user;

import android.os.Bundle;
import android.view.View;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.base.BaseFragment;
import com.example.jack.myshopping.type.TypeFragment;

public class UserFragment extends BaseFragment {
    View view;
    @Override
    public View initViewLayout() {
        if (view == null) {
            view = View.inflate(mContext, R.layout.fragment_user,null);
        }
        return view;
    }

    public static UserFragment newInstance(String param1) {
        UserFragment userFragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        userFragment.setArguments(args);
        return userFragment;
    }

}
