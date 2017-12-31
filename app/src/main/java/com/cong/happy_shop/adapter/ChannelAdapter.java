package com.cong.happy_shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Channel;
import com.cong.happy_shop.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {

    private List<Channel> channelData;
    private Context context;
    private final LayoutInflater mInflater;


    public ChannelAdapter(Context context, List<Channel> channelData) {
        this.channelData = channelData;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mInflater.inflate(R.layout.item_channel_home, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Channel channel = channelData.get(position);
        holder.mTvChannel.setText(channel.getChannel_name());
        Picasso.with(context).load(Constants.BASE_URl_IMAGE+channel.getImage()).into(holder.mIvChannel);
    }

    @Override
    public int getItemCount() {
        return channelData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvChannel;
        private final ImageView mIvChannel;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvChannel = (TextView) itemView.findViewById(R.id.tv_channel);
            mIvChannel = (ImageView) itemView.findViewById(R.id.iv_channel);
        }
    }
}
