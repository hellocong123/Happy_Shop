package com.cong.happy_shop.fragment;


import com.cong.happy_shop.R;
import com.cong.happy_shop.base.BaseFragment;
import com.cong.happy_shop.views.CustomToolbar;

import butterknife.BindView;

/**
 * Author ：Cong
 * Time   : 2017/12/19
 * desc   ：
 */

public class CartFragment extends BaseFragment {
    @BindView(R.id.custom_toolbar)
    CustomToolbar mCustomToolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void init() {


        mCustomToolbar.setToolTitle("购物车");
        mCustomToolbar.setRightButton("编辑");

    }

}
