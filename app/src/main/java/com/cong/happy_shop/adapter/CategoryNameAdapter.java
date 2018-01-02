package com.cong.happy_shop.adapter;

import android.content.Context;

import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Category;
import com.cong.happy_shop.holder.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/12/31.
 */

public class CategoryNameAdapter extends SimpleAdapter<Category> {

    public CategoryNameAdapter(Context context, List<Category> datas) {
        super(context, datas, R.layout.item_category_name);
    }

    @Override
    public void convert(BaseViewHolder holder, Category item) {

        holder.getTextView(R.id.tv_category_name).setText(item.getName());
    }
}
