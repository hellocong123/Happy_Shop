package com.cong.happy_shop.holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.BaseAdapter;
import com.cong.happy_shop.adapter.HomeHotAdapter;
import com.cong.happy_shop.adapter.SimpleAdapter;
import com.cong.happy_shop.bean.Hot;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class HotViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerView mRecyclerView;
    private SimpleAdapter<Hot> mTAG;

    public HotViewHolder(View itemView) {
        super(itemView);
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_home_hot);
    }

    public void setData(final Context context, final List<Hot> datas) {

        //布局管理
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));

        //Adapter
        HomeHotAdapter adapter = new HomeHotAdapter(context,datas);

        //设置Adapter
        mRecyclerView.setAdapter(adapter);

        //条目点击事件
        adapter.addClickListener(new BaseAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, "热门", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
