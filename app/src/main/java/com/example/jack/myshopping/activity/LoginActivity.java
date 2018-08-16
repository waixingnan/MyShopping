package com.example.jack.myshopping.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText usernameInputEdit;
    private TextInputEditText passworldInputEdit;
    private ImageView first, second, last;
    private TextView loginTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {
        usernameInputEdit = findViewById(R.id.username_input_edit);
        passworldInputEdit = findViewById(R.id.passworld_input_edit);
        loginTv = findViewById(R.id.login_tv);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        last = findViewById(R.id.last);

        loginTv.setOnClickListener(this);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        last.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_tv:
                break;
            case R.id.first:
                break;
            case R.id.second:
                break;
            case R.id.last:
                break;
        }
    }
}
