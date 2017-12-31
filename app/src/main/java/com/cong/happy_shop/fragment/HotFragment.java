package com.cong.happy_shop.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.HotAdapter;
import com.cong.happy_shop.base.BaseFragment;
import com.cong.happy_shop.bean.Page;
import com.cong.happy_shop.bean.Wares;
import com.cong.happy_shop.http.BaseCallback;
import com.cong.happy_shop.http.Contants;
import com.cong.happy_shop.http.OkHttpHelper;
import com.cong.happy_shop.views.CustomToolbar;

import butterknife.BindView;
import okhttp3.Response;

/**
 * Author ：Cong
 * Time   : 2017/12/19
 * desc   ：
 */

public class HotFragment extends BaseFragment {

    @BindView(R.id.custom_toolbar)
    CustomToolbar mCustomToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecycleView;

    private int currPager = 1;
    private int pageSize = 10;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void init() {

        mCustomToolbar.setToolTitle("热门");


        getData();
    }

    public void getData() {


        OkHttpHelper.getInstance().get(Contants.API.WARES_HOT + "?curPage=" + currPager + "&pageSize=" + pageSize, null, new BaseCallback<Page<Wares>>() {


            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {

                showData(waresPage);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });


    }

    private void showData(Page<Wares> waresPage) {

        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        HotAdapter adapter = new HotAdapter(getActivity(),waresPage.getList());
        mRecycleView.setAdapter(adapter);
    }
}
