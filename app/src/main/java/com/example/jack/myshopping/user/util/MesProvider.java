package com.example.jack.myshopping.user.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.user.bean.MessageBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MesProvider {
    private static MesProvider mesProvider;
    private final Context context;
    private final MessageBean messageBean;
    private final SharedPreferences.Editor editor;
    private List<MessageBean> PutList;
    private List<MessageBean> GetList;
    private final SharedPreferences sp;

    public MesProvider(Context context) {
        this.context = context;
        messageBean = new MessageBean();
        //创建sp对象
        sp = context.getSharedPreferences("MyMessage", context.MODE_PRIVATE);
        editor = sp.edit();
        //创建List集合对象
        PutList = new ArrayList<MessageBean>();
    }

    public static MesProvider getInstence() {
        if (mesProvider == null) {
            mesProvider = new MesProvider(MyApplication.getContext());
        }
        return mesProvider;
    }

    public void add(String title, String url,String data, String mes) {
        if(TextUtils.isEmpty(title)){
            messageBean.setTitle("Title isEmpty");
        }else {
            messageBean.setTitle(title);
        }
        if(TextUtils.isEmpty(url)){
            messageBean.setUrl("Url isEmpty");
        }else {
            messageBean.setUrl(url);
        }
        if(TextUtils.isEmpty(data)){
            messageBean.setDate("Date isEmpty");
        }else {
            messageBean.setDate(data);
        }
        if(TextUtils.isEmpty(url)){
            messageBean.setMes("Mes isEmpty");
        }else {
            messageBean.setMes(mes);
        }
        PutList.add(messageBean);
//
//        Gson gson = new Gson();
//        String JsonStr = gson.toJson(mesList);
//        editor.putString("MesData", JsonStr);
//        editor.commit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(PutList);
            String base64Student = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            editor.putString("student", base64Student);
            editor.apply();

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<MessageBean> get() {
        String ListJson = sp.getString("MesData", "");
        byte[] base64Student = Base64.decode(ListJson, Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Student);
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return  (List<MessageBean>) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        if (ListJson != null) {
//            Gson gson = new Gson();
//            mesList = gson.fromJson(ListJson, new TypeToken<List<MessageBean>>() {}.getType()); //将json字符串转换成List集合
//        }
    }


}
