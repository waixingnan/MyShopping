package com.example.jack.myshopping.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jack.myshopping.R;
import com.example.jack.myshopping.app.MyApplication;
import com.example.jack.myshopping.base.BaseActivity;
import com.example.jack.myshopping.home.bean.GoodsBean;
import com.example.jack.myshopping.shoppingcart.activity.ShoppingCartActivity;
import com.example.jack.myshopping.shoppingcart.utils.CartProvider;
import com.example.jack.myshopping.shoppingcart.utils.NumberAddSubView;
import com.example.jack.myshopping.util.Constants;

import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class GoodsInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private List<GoodsBean> goodsBeans;
    private GoodsBean goods_bean;
    private CartProvider cartProvider;
    private Dialog dialog;
    private TextView tvGoodInfoshare;
    private Toolbar toolbar;
    private ImageButton toolbar_left;
    private WebView webView;

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_left = findViewById(R.id.toolbar_left_ib);

        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
//        tvGoodInfoStyle = (TextView) findViewById(R.id.tv_good_info_style);

        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoshare = (TextView) findViewById(R.id.tv_good_info_share);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        btnGoodInfoAddcart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoshare.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);
        tvGoodInfoCallcenter.setOnClickListener(this);
        toolbar_left.setOnClickListener(this);

        initToolBar(this, toolbar, R.drawable.ic_backup_arrow_left_white_24dp, "商品详情",
                null, getResources().getColor(R.color.white), R.color.colorPrimary);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        cartProvider = CartProvider.getInstance();
        findViews();
        //取出intent
        Intent intent = getIntent();
        goods_bean = (GoodsBean) intent.getSerializableExtra("goods_bean");
        if (goods_bean != null) {
            //本地获取存储的数据
            setDataFormView(goods_bean);
        }

        initWebView();
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.webview);
//        webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html
        webView.loadUrl("http://www.pclady.com.cn/?ocid=MSNCNLG13-6");//加载url

        //使用webview显示html代码
//        webView.loadDataWithBaseURL(null,"<html><head><title> 欢迎您 </title></head>" +
//                "<body><h2>使用webview显示 html代码</h2></body></html>", "text/html" , "utf-8", null);

        webView.addJavascriptInterface(this, "android");//添加js监听 这样html就能调用客户端
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        //不显示webview缩放按钮
//        webSettings.setDisplayZoomControls(false);

    }

    public void setDataFormView(GoodsBean goodsBean) {
        String name = goodsBean.getName();
        String cover_price = goodsBean.getCover_price();
        String figure = goodsBean.getFigure();
        String product_id = goodsBean.getProduct_id();

        Glide.with(this).load(Constants.BASE_URL_IMAGE + figure).into(ivGoodInfoImage);
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (cover_price != null) {
            tvGoodInfoPrice.setText("￥" + cover_price);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toolbar_left_ib://返回上一级
                onBackPressed();
                break;
            case R.id.tv_good_info_callcenter://收藏
                break;
            case R.id.tv_good_info_share://分享
                showShare();
                break;
            case R.id.tv_good_info_cart://购物车
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_good_info_addcart: //添加购物车
                show();
                break;

            case R.id.bt_goodinfo_confim://确定加入购物车
                if (dialog == null) {
                    return;
                }
                dialog.dismiss();
                //添加购物车
                cartProvider.addData(goods_bean);
                MyApplication.ShowMes(GoodsInfoActivity.this, "添加购物车成功");
                break;
            case R.id.bt_goodinfo_cancel://取消加入购物车
                if (dialog == null) {
                    return;
                }
                dialog.dismiss();
                break;
        }

    }

    private void show() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);

        initView(inflate);
        //将布局设置给Dialog
        dialog.setContentView(inflate);

        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow == null) {
            return;
        }
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置高度和宽度
//        lp.height = (int) (d.getHeight() * 0.35); // 高度设置为屏幕的0.6
        lp.width = (int) (d.getWidth() * 1.0); // 宽度设置为屏幕的0.65
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    private void initView(View inflate) {
        //初始化控件
        inflate.findViewById(R.id.bt_goodinfo_confim).setOnClickListener(this);
        inflate.findViewById(R.id.bt_goodinfo_cancel).setOnClickListener(this);

        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) inflate.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) inflate.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) inflate.findViewById(R.id.tv_goodinfo_price);
        NumberAddSubView nas_goodinfo_num = (NumberAddSubView) inflate.findViewById(R.id.nas_goodinfo_num);
        // 加载图片
        Glide.with(GoodsInfoActivity.this).load(Constants.BASE_URL_IMAGE + goods_bean.getFigure()).into(iv_goodinfo_photo);
        // 名称
        tv_goodinfo_name.setText(goods_bean.getName());
        // 显示价格
        tv_goodinfo_price.setText(goods_bean.getCover_price());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(8);
        nas_goodinfo_num.setValue(1);

        nas_goodinfo_num.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                goods_bean.setNumber(value);
            }

            @Override
            public void subNumner(View view, int value) {
                goods_bean.setNumber(value);
            }
        });

    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("分享文本");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(getResources().getString(R.string.shareText));
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }





    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
//            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen", "拦截url:" + url);
            if (url.equals("http://www.google.com/")) {
//                Toast.makeText(this,"国内不能访问google,拦截该url",Toast.LENGTH_LONG).show();
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };


    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen", "网页标题:" + title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            progressBar.setProgress(newProgress);
        }
    };

    /**
     * JS调用android的方法
     *
     * @param str
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void getClient(String str) {
        Log.i("ansen", "html调用客户端:" + str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //释放资源
        webView.destroy();
        webView = null;
    }



}
