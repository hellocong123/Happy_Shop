package com.cong.happy_shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cong.happy_shop.holder.BaseViewHolder;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/26
 * desc   ：
 */

public abstract class BaseAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> mDatas;
    private Context mContext;
    private int layoutResId;
    private final LayoutInflater mInflater;

    public ClickListener mClickListener;

    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void addClickListener(ClickListener listener) {
        this.mClickListener = listener;
    }

    public BaseAdapter(Context context, List<T> datas, int LayoutResId) {
        this.mContext = context;
        this.mDatas = datas;
        this.layoutResId = LayoutResId;
        mInflater = LayoutInflater.from(mContext);
    }

    //封装一个ViewHolder，把要创建的ViewHolder都定义在BaseViewHolder一个基类里面
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(layoutResId, parent, false);
        return new BaseViewHolder(view, mClickListener);
    }

    //绑定数据通过抽象的方法来实现
    @Override //不知道为什么不用这个才可以编译通过
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T item = mDatas.get(position);
        convert((H) holder, item);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public abstract void convert(BaseViewHolder holder, T item);


    //清空数据
    public void clearData() {
        mDatas.clear();
        notifyItemRangeRemoved(0, mDatas.size());
    }

    //移除某个条目
    public void removeItem(T t) {

        int position = mDatas.indexOf(t);
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    //得到数据
    public List<T> getDatas() {
        return mDatas;
    }


    //添加一个条目
    public void addData(List<T> datas) {
        addData(0, datas);
    }

    public void addData(int position, List<T> list) {
        if (list != null && list.size() > 0) {
            for (T t : list) {
                mDatas.add(position, t);
                notifyItemInserted(position);
            }
        }
    }
}
