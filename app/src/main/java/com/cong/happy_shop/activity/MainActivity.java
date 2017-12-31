package com.cong.happy_shop.activity;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.cong.happy_shop.R;
import com.cong.happy_shop.base.BaseActivity;
import com.cong.happy_shop.bean.Tab;
import com.cong.happy_shop.fragment.CartFragment;
import com.cong.happy_shop.fragment.CategoryFragment;
import com.cong.happy_shop.fragment.HomeFragment;
import com.cong.happy_shop.fragment.HotFragment;
import com.cong.happy_shop.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.real_tab_content)
    FrameLayout mRealTabContent;
    @BindView(android.R.id.tabcontent)
    FrameLayout mTabContent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    private List<Tab> mTabs;
    private LayoutInflater mInflater;


    @Override
    protected void init() {

        //把5个装有图标，文字，Fragment的Tab对象，都添加到List集合
        initTabData();
        //把List集合里的元素都添加到Tab里面
        initTab();

    }

    private void initTab() {
        //TabHost调用setup，设置一个需要展示内容页的布局ID
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);

        //遍历装了5个对象的集合
        for (Tab tab : mTabs) {
            //通过Tab里的文字，得到一个TabSpec
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));
            //使用mInflater把一个XML布局设置为一个View，并设置这个View里面的文字和图片
            View view = buildIndicator(tab);
            //指定视图作为选项卡指示器。
            tabSpec.setIndicator(view);
            //最后把TabSpec和Fragment都放到TabHost里面
            mTabHost.addTab(tabSpec, tab.getFragment(), null);
        }
    }

    private void initTabData() {

        //新建5个分页，并且添加到List当中，便于管理，其中的图标我们使用了selector进行状态选择，即选中的时候会变色。
        Tab home = new Tab(R.drawable.selector_icon_home, R.string.home, HomeFragment.class);
        Tab hot = new Tab(R.drawable.selector_icon_hot, R.string.hot, HotFragment.class);
        Tab category = new Tab(R.drawable.selector_icon_category, R.string.category, CategoryFragment.class);
        Tab car = new Tab(R.drawable.selector_icon_cart, R.string.car, CartFragment.class);
        Tab mine = new Tab(R.drawable.selector_icon_mine, R.string.mine, MineFragment.class);


        //把5个页面都添加到集合
        mTabs = new ArrayList<>(5);
        mTabs.add(home);
        mTabs.add(hot);
        mTabs.add(category);
        mTabs.add(car);
        mTabs.add(mine);


    }

    //给一个Tab的View添加文字和图片
    private View buildIndicator(Tab tab) {
        mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon_tab);
        TextView tv = (TextView) view.findViewById(R.id.txt_indicator);
        icon.setBackgroundResource(tab.getIcon());
        tv.setText(tab.getTitle());
        return view;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }
}
