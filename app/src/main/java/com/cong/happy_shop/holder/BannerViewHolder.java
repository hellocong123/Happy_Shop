package com.cong.happy_shop.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.BannerBean;
import com.cong.happy_shop.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class BannerViewHolder extends RecyclerView.ViewHolder {


    private static final String TAG ="BannerViewHolder" ;
    public Banner banner;
    public BannerViewHolder(View itemView) {
        super(itemView);
        banner = (Banner) itemView.findViewById(R.id.banner);
    }

    public void setData(Context context, List<BannerBean> bannerData) {


        Log.i(TAG, "setData: "+bannerData);

        List<String> imageList = new ArrayList<>();

        for (int i = 0; i < bannerData.size(); i++) {
            String image = bannerData.get(i).getImage();
            imageList.add(Constants.BASE_URl_IMAGE + image);
        }


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageList);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

//          tv.setText(Constants.BASE_URl_IMAGE+bannerData.get(1).getImage());

    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Picasso 加载图片简单用法
            //     Picasso.with(context).load((String) path).networkPolicy(NetworkPolicy.NO_CACHE).into(imageView);

            Glide.with(context).load((String) path).into(imageView);


        }
    }
}
