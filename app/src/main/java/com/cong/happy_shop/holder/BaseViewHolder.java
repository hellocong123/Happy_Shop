package com.cong.happy_shop.holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cong.happy_shop.adapter.BaseAdapter;

/**
 * Author ：Cong
 * Time   : 2017/12/26
 * desc   ：ViewHolder中的View成员变量转而通过View数组来实现，并提供一些常用方法
 */

public class BaseViewHolder extends RecyclerView.ViewHolder  {

    //使用SparseArray来管理item里面的View
    public SparseArray<View> views;
    private BaseAdapter.ClickListener itemClickListener;

    public BaseViewHolder(View itemView, final BaseAdapter.ClickListener itemClickListener) {
        super(itemView);
        views = new SparseArray<>();
        this.itemClickListener = itemClickListener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(v,getAdapterPosition());
                }
            }
        });
    }

    public TextView getTextView(int id) {
        return findView(id);
    }

    public ImageView getImageView(int id) {
        return findView(id);
    }

    public LinearLayout getLinearLayout(int id){
        return findView(id);
    }

    public Button getButton(int id) {
        return findView(id);
    }

    public View getView(int id) {
        return findView(id);
    }

    private <T extends View> T findView(int id) {

        View view = views.get(id);

        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }

        return (T) view;
    }



}
