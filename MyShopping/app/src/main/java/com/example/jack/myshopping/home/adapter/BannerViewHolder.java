package com.example.jack.myshopping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.activity.GoodsInfoActivity;
import com.example.jack.myshopping.home.bean.GoodsBean;
import com.example.jack.myshopping.home.bean.ResultBeanData;
import com.example.jack.myshopping.home.util.GlideImageLoader;
import com.example.jack.myshopping.util.ACache;
import com.example.jack.myshopping.util.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class BannerViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "BannerViewHolder";
    private final Banner banner;
    private Context mContext;
    private ACache mCache;
    private Drawable drawable;
    private InputStream inputStream;

    public BannerViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        mCache = ACache.get(mContext);
        banner = itemView.findViewById(R.id.banner);
    }

    public void setData(final List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
        List<String> imgUrls = new ArrayList<>();
        final List<Bitmap> bitmapArrayList = new ArrayList<>();

        for (int i = 0; i < banner_info.size(); i++) {
            imgUrls.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());

//            HttpUtil.sendOkHttpRequest(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage(), new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//                    inputStream = response.body().byteStream();
//
//                }
//            });
//            Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
//            mCache.put("banner"+i, bitmap);
//            bitmapArrayList.add(bitmap);

        }


        banner.setImages(imgUrls)
                .setImageLoader(new GlideImageLoader())
//                .setOnBannerListener(this)
                .start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                if (position < banner_info.size()) {
                    int option = banner_info.get(position).getOption();
                    String product_id = "";
                    String name = "";
                    String cover_price = "";
                    if (position == 0) {
                        product_id = "627";
                        cover_price = "32.00";
                        name = "剑三T恤批发";
                    } else if (position == 1) {
                        product_id = "21";
                        cover_price = "8.00";
                        name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                    } else {
                        product_id = "1341";
                        cover_price = "50.00";
                        name = "【蓝诺】《天下吾双》 剑网3同人本";
                    }
                    String image = banner_info.get(position).getImage();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, image, product_id);

                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra("goods_bean", goodsBean);
                    mContext.startActivity(intent);
                }


            }
        });


    }

}

