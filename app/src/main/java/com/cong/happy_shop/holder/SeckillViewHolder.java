package com.cong.happy_shop.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.BaseAdapter;
import com.cong.happy_shop.adapter.HomeSeckillAdapter;
import com.cong.happy_shop.bean.Seckill;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class SeckillViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerView mRecyclerView;

    public SeckillViewHolder(View itemView) {
        super(itemView);
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
    }

    public void setData(final Context context, Seckill data) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        HomeSeckillAdapter adapter = new HomeSeckillAdapter(context, data.getList());
        mRecyclerView.setAdapter(adapter);

        adapter.addClickListener(new BaseAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, "秒杀专场" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
