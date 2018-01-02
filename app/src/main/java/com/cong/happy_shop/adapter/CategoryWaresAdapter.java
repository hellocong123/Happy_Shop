package com.cong.happy_shop.adapter;

import android.content.Context;
import android.net.Uri;

import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Wares;
import com.cong.happy_shop.holder.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/31.
 */

public class CategoryWaresAdapter extends SimpleAdapter<Wares>{

    public CategoryWaresAdapter(Context context, List<Wares> datas) {
        super(context, datas, R.layout.item_category_wares);
    }

    @Override
    public void convert(BaseViewHolder holder, Wares item) {

        holder.getTextView(R.id.text_title).setText(item.getName());
        holder.getTextView(R.id.text_price).setText("￥"+item.getPrice());
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(item.getImgUrl()));

    }
}
