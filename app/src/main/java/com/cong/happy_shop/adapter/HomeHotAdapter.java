package com.cong.happy_shop.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Hot;
import com.cong.happy_shop.holder.BaseViewHolder;
import com.cong.happy_shop.utils.Constants;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/26
 * desc   ：
 */

public class HomeHotAdapter extends SimpleAdapter<Hot>{
    private Context mContext;
    public HomeHotAdapter(Context context, List<Hot> datas) {
        super(context, datas, R.layout.item_home_hot);
        mContext = context;
    }

    @Override
    public void convert(BaseViewHolder holder, Hot item) {

        holder.getTextView(R.id.tv_name).setText(item.getName());
        holder.getTextView(R.id.tv_price).setText(item.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + item.getFigure()).into(holder.getImageView(R.id.iv_hot));
    }
}
