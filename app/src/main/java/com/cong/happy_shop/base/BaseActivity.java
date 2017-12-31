package com.cong.happy_shop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Author ：Cong
 * Time   : 2017/12/19
 * desc   ：
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(setLayoutId());

        ButterKnife.bind(this);

        init();

    }

    protected abstract void init();

    protected abstract int setLayoutId();
}
