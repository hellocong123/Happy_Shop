package com.cong.happy_shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cong.happy_shop.R;
import com.cong.happy_shop.bean.Home;
import com.cong.happy_shop.bean.ListBean;
import com.cong.happy_shop.bean.Seckill;
import com.cong.happy_shop.holder.ActViewHolder;
import com.cong.happy_shop.holder.BannerViewHolder;
import com.cong.happy_shop.holder.ChannelViewHolder;
import com.cong.happy_shop.holder.HotViewHolder;
import com.cong.happy_shop.holder.RecommendViewHolder;
import com.cong.happy_shop.holder.SeckillViewHolder;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HomeAdapter1";
    private final Home.Result mHomeData;
    private final Context mContext;
    private final LayoutInflater mInflater;

    public static final int BANNER = 0;//横幅广告
    public static final int CHANNEL = 1;//频道
    public static final int ACT = 2;//活动
    public static final int SECKILL = 3;//秒杀
    public static final int HOT = 4;//热卖
    public static final int RECOMMEND = 5;//推荐
    public int currentType = BANNER;//当前类型

    public HomeAdapter(Context context, Home.Result result) {
        mHomeData = result;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case BANNER:
                return new BannerViewHolder(mInflater.inflate(R.layout.item_banner, parent, false));
            case CHANNEL:
                return new ChannelViewHolder(mInflater.inflate(R.layout.item_channel, parent, false));
            case ACT:
                return new ActViewHolder(mInflater.inflate(R.layout.item_act, parent, false));
            case SECKILL:
                return new SeckillViewHolder(mInflater.inflate(R.layout.item_seckill, parent, false));
            case HOT:
                return new HotViewHolder(mInflater.inflate(R.layout.item_hot, parent, false));
            case RECOMMEND:
                return new RecommendViewHolder(mInflater.inflate(R.layout.item_recommend, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);

        switch (itemViewType) {
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(mContext, mHomeData.banner_info);
                break;

            case CHANNEL:
                ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
                channelViewHolder.setData(mContext, mHomeData.channel_info);
                break;

            case ACT:
                ActViewHolder actViewHolder = (ActViewHolder) holder;
                actViewHolder.setData(mContext, mHomeData.act_info);
                break;

            case SECKILL:
                SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
                Seckill seckill_info = mHomeData.seckill_info;
                List<ListBean> seckill_infoList = seckill_info.getList();
                seckillViewHolder.setData(mContext, mHomeData.seckill_info);
                break;

            case HOT:
                HotViewHolder hotViewHolder = (HotViewHolder) holder;
                hotViewHolder.setData(mContext, mHomeData.hot_info);
                break;
            case RECOMMEND:
                RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
                recommendViewHolder.setData(mContext, mHomeData.recommend_info);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case HOT:
                currentType = HOT;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
        }
        return currentType;

    }


}



