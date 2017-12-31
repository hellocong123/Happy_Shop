package com.cong.happy_shop.holder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Act;
import com.cong.happy_shop.utils.Constants;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class ActViewHolder extends RecyclerView.ViewHolder {

    private final ViewPager mViewPager;

    public ActViewHolder(View itemView) {
        super(itemView);

        mViewPager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
    }

    public void setData(final Context context, final List<Act> data) {


        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Act act = data.get(position);
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(Constants.BASE_URl_IMAGE + act.getIcon_url()).into(imageView);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }
}
