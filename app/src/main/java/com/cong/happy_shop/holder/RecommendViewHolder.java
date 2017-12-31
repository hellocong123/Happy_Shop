package com.cong.happy_shop.holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.BaseAdapter;
import com.cong.happy_shop.adapter.HomeRecommendAdapter;
import com.cong.happy_shop.adapter.SimpleAdapter;
import com.cong.happy_shop.bean.Recommend;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class RecommendViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "RecommendViewHolder";
    private final RecyclerView mRecyclerView;
    private SimpleAdapter<Recommend> mAdapter;

    public RecommendViewHolder(View itemView) {
        super(itemView);
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_recommend);
    }

    public void setData(final Context context, List<Recommend> data) {

        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        HomeRecommendAdapter adapter = new HomeRecommendAdapter(context, data);
        mRecyclerView.setAdapter(adapter);

        adapter.addClickListener(new BaseAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, "推荐" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
