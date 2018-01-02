package com.cong.happy_shop.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.HotAdapter;
import com.cong.happy_shop.base.BaseFragment;
import com.cong.happy_shop.bean.Page;
import com.cong.happy_shop.bean.Wares;
import com.cong.happy_shop.http.BaseCallback;
import com.cong.happy_shop.http.Contants;
import com.cong.happy_shop.http.OkHttpHelper;
import com.cong.happy_shop.views.CustomToolbar;

import java.util.List;

import butterknife.BindView;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

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
    @BindView(R.id.refresh_view)
    MaterialRefreshLayout refreshView;

    private int currentPage = 1;

    private int pageSize = 10;

    //下拉刷新和上拉加载更多的状态
    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;
    private int STATE = STATE_NORMAL;//当前状态


    private HotAdapter adapter;
    private List<Wares> waresData;
    private int totalPage = 3;
    private int totalCount;
    private String copyright;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        mCustomToolbar.setToolTitle("热门");

        //加载网络数据
        getData();
        //初始化下拉刷新上拉加载更多
        initRefreshState();
    }

    public void getData() {

        //请求地址
        String url = Contants.API.WARES_HOT + "?curPage=" + currentPage + "&pageSize=" + pageSize;

        OkHttpHelper.getInstance().get(url, null, new BaseCallback<Page<Wares>>() {
            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {

                waresData = waresPage.getList();
                currentPage = waresPage.getCurrentPage();//当前页
                totalCount = waresPage.getTotalCount();

                showData();
            }
            @Override
            public void onError(Response response, int code, Exception e) {


            }
        });


    }

    /**
     * 显示数据
     */
    private void showData() {

        //在显示数据的时候就要根据状态来判断怎么刷新加载数据
        //正常：创建一个Adapter来绑定数据
        //刷新：Adapter已经有数据了，所以要在Adapter里面添加数据
        //加载更多：在Adapter里面添加数据，在第一页的最后一条数据那里进行添加

        switch (STATE) {
            case STATE_NORMAL://正常

                mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new HotAdapter(getActivity(), waresData);
                mRecycleView.setAdapter(adapter);
                break;

            case STATE_REFRESH://下拉刷新
                //清空数据
                adapter.clearData();
                //添加数据
                adapter.addData(waresData);
                //滚动到第0条Position
                mRecycleView.scrollToPosition(0);
                //完成刷新
                refreshView.finishRefresh();
                break;

            case STATE_MORE://上拉加载更多

                //在最后一条的位置添加下一页数据
                adapter.addData(adapter.getDatas().size(), waresData);
                //滚动到最后一条
                mRecycleView.scrollToPosition(adapter.getDatas().size() + 1);
                //完成加载更多数据
                refreshView.finishRefreshLoadMore();
                break;
        }


    }


    //初始化刷新
    public void initRefreshState() {

        refreshView.setLoadMore(true);
        //刷新控件的监听
        refreshView.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新数据
                refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //如果当前页小于等于总页就证明有数据了，可以加载数据
                if (currentPage <= totalPage) {
                    LoadMore();
                } else {
                    refreshView.finishRefreshLoadMore();
                    Toast.makeText(getContext(), "没有数据了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //加载更多调用
    private void LoadMore() {

        //改变当前状态为加载更多
        STATE = STATE_MORE;
        //每加载一次，就把当前页加一次
        currentPage = ++currentPage;
        //重新加载网络数据
        getData();

    }

    //下拉刷新数据调用
    public void refreshData() {

        //改变当前状态为下拉刷新
        STATE = STATE_REFRESH;
        //重新加载网络数据
        getData();
    }
}
