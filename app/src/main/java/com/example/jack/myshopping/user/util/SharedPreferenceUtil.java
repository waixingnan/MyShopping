package com.example.jack.myshopping.user.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.user.bean.MessageBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceUtil {

    private static SharedPreferenceUtil sharedPreferenceUtil;
    private MessageBean messageBean;
    private final Context context;
    private List<MessageBean> messageBeanList;

    public SharedPreferenceUtil(Context context) {
        this.context = context;
        messageBean = new MessageBean();
        messageBeanList = new ArrayList();

    }

    public static SharedPreferenceUtil getInstence() {
        if (sharedPreferenceUtil == null) {
            sharedPreferenceUtil = new SharedPreferenceUtil(MyApplication.getContext());
        }
        return sharedPreferenceUtil;
    }

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param fileName   储存文件的name
     * @param keyName    储存对象的key
//     * @param saveObject 储存的对象
     */
    public void save(String title, String url, String data, String mes, String fileName, String keyName) {

        if (TextUtils.isEmpty(title)) {
            messageBean.setTitle("Title isEmpty");
        } else {
            messageBean.setTitle(title);
        }
        if (TextUtils.isEmpty(url)) {
            messageBean.setUrl("Url isEmpty");
        } else {
            messageBean.setUrl(url);
        }
        if (TextUtils.isEmpty(data)) {
            messageBean.setDate("Date isEmpty");
        } else {
            messageBean.setDate(data);
        }
        if (TextUtils.isEmpty(url)) {
            messageBean.setMes("Mes isEmpty");
        } else {
            messageBean.setMes(mes);
        }
        messageBeanList.add(messageBean);
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String string = Object2String(messageBeanList);
        editor.putString(keyName, string);
        editor.commit();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param fileName 储存文件的key
     * @param keyName  储存对象的key
     * @return object 返回根据key得到的对象
     */
    public Object get(String fileName, String keyName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        String string = sharedPreferences.getString(keyName, null);
        if (string != null) {
            Object object = String2Object(string);
            return object;
        } else {
            return null;
        }
    }


}
