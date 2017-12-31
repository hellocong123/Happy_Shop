package com.cong.happy_shop.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Recommend;
import com.cong.happy_shop.holder.BaseViewHolder;
import com.cong.happy_shop.utils.Constants;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/26
 * desc   ：
 */

public class HomeRecommendAdapter extends SimpleAdapter<Recommend> {

    private Context mContext;

    public HomeRecommendAdapter(Context context, List<Recommend> datas) {
        super(context, datas, R.layout.item_home_recommend);
        mContext = context;
    }

    @Override
    public void convert(BaseViewHolder holder, Recommend item) {
        holder.getTextView(R.id.tv_name).setText(item.getName());
        holder.getTextView(R.id.tv_price).setText(item.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + item.getFigure()).into(holder.getImageView(R.id.iv_recommend));
    }
}
