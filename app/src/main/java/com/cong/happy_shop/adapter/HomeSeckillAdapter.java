package com.cong.happy_shop.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.ListBean;
import com.cong.happy_shop.holder.BaseViewHolder;
import com.cong.happy_shop.utils.Constants;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/26
 * desc   ：
 */

public class HomeSeckillAdapter extends SimpleAdapter<ListBean> {
    private Context mContext;

    public HomeSeckillAdapter(Context context, List<ListBean> datas) {
        super(context, datas, R.layout.item_home_seckill);
        mContext = context;
    }

    @Override
    public void convert(BaseViewHolder holder, ListBean item) {
        holder.getTextView(R.id.tv_cover_price).setText(item.getCover_price());
        holder.getTextView(R.id.tv_origin_price).setText(item.getOrigin_price());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + item.getFigure()).into(holder.getImageView(R.id.iv_seckill_figure));
    }
}
