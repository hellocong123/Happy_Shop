package com.cong.happy_shop.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.HomeAdapter;
import com.cong.happy_shop.base.BaseFragment;
import com.cong.happy_shop.bean.Act;
import com.cong.happy_shop.bean.BannerBean;
import com.cong.happy_shop.bean.Channel;
import com.cong.happy_shop.bean.Home;
import com.cong.happy_shop.bean.Hot;
import com.cong.happy_shop.http.BaseCallback;
import com.cong.happy_shop.http.OkHttpHelper;
import com.cong.happy_shop.utils.Constants;
import com.cong.happy_shop.views.CustomToolbar;

import java.util.List;

import butterknife.BindView;
import okhttp3.Response;

/**
 * Author ：Cong
 * Time   : 2017/12/19
 * desc   ：
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.custom_toolbar)
    CustomToolbar mCustomToolbar;
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        mCustomToolbar.setShowSearchView(true);

        OkHttpHelper.getInstance().get(Constants.HOME_URL, null, new BaseCallback<Home>() {

            @Override
            public void onSuccess(Response response, Home home) {
                Log.i("TAG", "----------");
                showData(home);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    private void showData(Home home) {

        Home.Result result = home.result;
        List<BannerBean> bannerBeanData = result.banner_info;
        List<Channel> channelData = result.channel_info;
        List<Hot> hotData = result.hot_info;
        List<Act> actData = result.act_info;

//        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
//        HomeAdapter1 adapter1 = new HomeAdapter1(getActivity(),result);
        HomeAdapter adapter = new HomeAdapter(getActivity(), result);
        mRecycleView.setAdapter(adapter);

    }
}
