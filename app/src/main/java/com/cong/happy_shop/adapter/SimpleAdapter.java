package com.cong.happy_shop.adapter;

import android.content.Context;

import com.cong.happy_shop.holder.BaseViewHolder;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/26
 * desc   ：
 */

public abstract class SimpleAdapter<T> extends BaseAdapter<T, BaseViewHolder> {
    public SimpleAdapter(Context context, List<T> datas, int LayoutResId) {
        super(context, datas, LayoutResId);
    }
}
