package com.cong.happy_shop.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cong.happy_shop.R;


/**
 * Author ：Cong
 * Time   : 2017/12/19
 * desc   ：
 */

public class CustomToolbar extends Toolbar {

//    @BindView(R.id.toolbar_searchview)
//    EditText mToolbarSearchview;
//    @BindView(R.id.toolbar_title)
//    TextView mToolbarTitle;
//    @BindView(R.id.toolbar_rightButton)
//    Button mToolbarRightButton;

    EditText mToolbarSearchview;
    TextView mToolbarTitle;
    Button mToolbarRightButton;


    private LayoutInflater mInflater;

//    public CharSequence mRightButton;
//    public boolean mIsShowSearchView;
//    public CharSequence mToolTitle;

    public CustomToolbar(Context context) {
        this(context, null);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setContentInsetsRelative(10, 10);
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {

            //TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar);

            //通过自定义属性集的名字，得到里面包包围的所有属性
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CustomToolbar, defStyleAttr, 0);

            //获取自定义属性
//            mIsShowSearchView = a.getBoolean(R.styleable.CustomToolbar_isShowSearchView, false);
//            mRightButton = a.getText(R.styleable.CustomToolbar_rightButton);
//            mToolTitle = a.getText(R.styleable.CustomToolbar_title);

            a.recycle();

        }
    }

    //设置右按钮，并进行显示
    public void setRightButton(CharSequence rightButton) {
        mToolbarRightButton.setText(rightButton);
        mToolbarRightButton.setVisibility(VISIBLE);
    }
    //设置搜索框，并进行显示
    public void setShowSearchView(boolean showSearchView) {
        if (showSearchView) {
            mToolbarSearchview.setVisibility(VISIBLE);
        }
    }
    //设置右标题，并进行显示
    public void setToolTitle(CharSequence toolTitle) {
        mToolbarTitle.setText(toolTitle);
        mToolbarTitle.setVisibility(VISIBLE);
    }


    //初始化控件，把子View添加到Toolbar里面
    private void init() {

        mInflater = LayoutInflater.from(getContext());

        View view = mInflater.inflate(R.layout.toolbar, null);

        mToolbarTitle = (TextView) view.findViewById(R.id.toolbar_title1);
        mToolbarRightButton = (Button) view.findViewById(R.id.toolbar_rightButton);
        mToolbarSearchview = (EditText) view.findViewById(R.id.toolbar_search_view);

        //参数
        LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL);

        //把子View和参数都添加到布局控件上面
        addView(view, lp);
    }

}
