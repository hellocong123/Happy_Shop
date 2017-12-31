package com.cong.happy_shop.holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.ChannelAdapter;
import com.cong.happy_shop.bean.Channel;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class ChannelViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView gridView;

    public ChannelViewHolder(View itemView) {
        super(itemView);

        gridView = (RecyclerView) itemView.findViewById(R.id.gv_channel);

    }

    public void setData(Context context, List<Channel> channelData) {

        gridView.setLayoutManager(new GridLayoutManager(context, 5));
        ChannelAdapter adapter = new ChannelAdapter(context,channelData);
        gridView.setAdapter(adapter);


    }
}
