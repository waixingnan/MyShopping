package com.example.jack.myshopping.app;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.mob.MobSDK;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    private static Toast toast;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        //极光推送sdk初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        MobSDK.init(this);//分享sdk初始化

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }


    // 获取全局上下文
    public static Context getContext() {
        return mContext;
    }


    public static void ShowMes(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

}