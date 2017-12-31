package com.cong.happy_shop.adapter;

import android.content.Context;

import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Wares;
import com.cong.happy_shop.holder.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/26
 * desc   ：
 */

public class HotAdapter extends SimpleAdapter<Wares>{

    public HotAdapter(Context context, List<Wares> datas) {
        super(context, datas, R.layout.item_hot_wares);


    }


    @Override
    public void convert(BaseViewHolder holder, Wares item) {

        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) holder.getView(R.id.drawee_view);
        simpleDraweeView.setImageURI(item.getImgUrl());

        holder.getTextView(R.id.text_title).setText(item.getName());

    }
}
