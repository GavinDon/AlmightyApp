package com.ln.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by linan   on 2017/4/6.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;

    private View convertView;

    private BaseAdapter mAdapter;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
        this.convertView=itemView;
    }
    public View getConvertView(){
        return convertView;
    }

    public BaseViewHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * 从views 获取view
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    protected BaseViewHolder setAdapter(BaseAdapter adapter) {
        this.mAdapter = adapter;
        return this;
    }


}
