package com.ln.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linan   on 2017/4/6.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mData;
    protected int mLayoutResId;
    protected Context mContext;
    public OnItemClickListenerL mListener; //item 点击事件
    public OnChildClickListenerL mChildListener; //子控件点击
    public OnLoadMoreListener mLoadMoreListener;


    public BaseAdapter(@LayoutRes int layoutId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        this.mLayoutResId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(mLayoutResId, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindViewClickListener(holder, position);
        int type = holder.getItemViewType();

            convert(holder, position, mData.get(position));

    }



    /**
     * 点击事件处理
     *
     * @param holder
     * @param postion
     */
    private void bindViewClickListener(final BaseViewHolder holder, final int postion) {
        View view = holder.getConvertView();
        if (view == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(BaseAdapter.this, postion);
            }
        });
    }

    protected abstract void convert(BaseViewHolder holder, int position, T data);

    public T getItem(int position) {
        if (position != -1)
            return mData.get(position);
        else
            return null;
    }


    @Override
    public int getItemCount() {
            return mData.size();
    }

    public void addData(List<T> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
            this.notifyDataSetChanged();
        }
    }



    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void addLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;

    }

    //****************************//
    //********点击事件处理********//
    //***************************//
    public interface OnItemClickListenerL {
        void onItemClick(BaseAdapter adapter, int position);
    }

    public void addItemClickListener(@NonNull OnItemClickListenerL listener) {
        this.mListener = listener;
    }

    /**
     * 子控件的点击事件
     */
    public interface OnChildClickListenerL {
        void onChildClick();
    }

    public void addChildClickListener(OnChildClickListenerL childListener) {
        this.mChildListener = childListener;
    }

    protected OnItemClickListenerL getItemClickListener() {
        return mListener;
    }


}
