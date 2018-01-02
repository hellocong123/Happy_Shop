package com.cong.happy_shop.fragment;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.cong.happy_shop.R;
import com.cong.happy_shop.adapter.BaseAdapter;
import com.cong.happy_shop.adapter.CategoryNameAdapter;
import com.cong.happy_shop.adapter.CategoryWaresAdapter;
import com.cong.happy_shop.base.BaseFragment;
import com.cong.happy_shop.bean.Category;
import com.cong.happy_shop.bean.CategoryBanner;
import com.cong.happy_shop.bean.Page;
import com.cong.happy_shop.bean.Wares;
import com.cong.happy_shop.http.BaseCallback;
import com.cong.happy_shop.http.Contants;
import com.cong.happy_shop.http.OkHttpHelper;
import com.cong.happy_shop.views.CustomToolbar;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Response;

/**
 * Author ：Cong
 * Time   : 2017/12/19
 * desc   ：
 */

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.custom_toolbar)
    CustomToolbar mCustomToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerview_wares)
    RecyclerView recyclerviewWares;
    @BindView(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;

    private List<Category> categoryData;
    private CategoryNameAdapter nameAdapter;
    private Object nameData;
    private List<CategoryBanner> banners;
    private List<Wares> waresData;
    private CategoryWaresAdapter waresAdapter;
    private long categoryId = 1;

    private int currPage = 1;
    private int pageSize = 10;


    //下拉刷新和上拉加载更多的状态
    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;
    private int STATE = STATE_NORMAL;//当前状态


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initData() {
        mCustomToolbar.setToolTitle("分类");
        loadData();
        initRefreshView();
    }

    private void initRefreshView() {


        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                refreshData();//刷新数据
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                loadMore();//加载更多
            }
        });
    }

    private void loadMore() {

        STATE = STATE_MORE;

        getWaresData(categoryId);
    }

    private void refreshData() {

        STATE = STATE_REFRESH;

        currPage = ++currPage;

    }

    private void loadData() {

        getNameData();

        getWaresData(categoryId);

        getBannerData();

        //http://112.124.22.238:8081/course_api/wares/list?categoryId=categoryId&curPage=currPage&pageSize=pageSize


    }


    public void getNameData() {

        OkHttpHelper.getInstance().get(Contants.API.CATEGORY_LIST, new BaseCallback<List<Category>>() {
            @Override
            public void onSuccess(Response response, List<Category> category) {
                categoryData = category;
                showNameData();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    private void showNameData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        nameAdapter = new CategoryNameAdapter(getContext(), categoryData);
        recyclerView.setAdapter(nameAdapter);
        nameAdapter.addClickListener(new BaseAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Category category = categoryData.get(position);

                categoryId = category.getId();

                getWaresData(categoryId);
            }
        });

    }

    public void getBannerData() {

        String url = Contants.API.BANNER + "?type=1";

        OkHttpHelper.getInstance().get(url, new BaseCallback<List<CategoryBanner>>() {


            @Override
            public void onSuccess(Response response, List<CategoryBanner> categoryBanners) {
                banners = categoryBanners;
                showBannerData();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void showBannerData() {


        List<String> imageList = new ArrayList<>();

        for (int i = 0; i < banners.size(); i++) {
            String image = banners.get(i).getImgUrl();
            imageList.add(image);
        }


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageList);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


    public void getWaresData(long categoryId) {
        String url = Contants.API.WARES_LIST + "?categoryId=" + categoryId + "&curPage=" + currPage + "&pageSize=" + pageSize;
        OkHttpHelper.getInstance().get(url, new BaseCallback<Page<Wares>>() {

            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {

                waresData = waresPage.getList();

                showWaresData();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void showWaresData() {

        recyclerviewWares.setLayoutManager(new GridLayoutManager(getContext(), 2));
        waresAdapter = new CategoryWaresAdapter(getContext(), waresData);
        recyclerviewWares.setAdapter(waresAdapter);

    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Picasso 加载图片简单用法
            //     Picasso.with(context).load((String) path).networkPolicy(NetworkPolicy.NO_CACHE).into(imageView);

            Glide.with(context).load((String) path).into(imageView);


        }
    }
}
