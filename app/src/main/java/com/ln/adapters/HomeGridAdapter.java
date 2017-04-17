package com.ln.adapters;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;

import com.ln.base.BaseAdapter;
import com.ln.base.BaseViewHolder;
import com.ln.view.R;

import java.util.List;

/**
 * description: 首页网格布局适配器
 * Created by liNan on 2017/4/17 9:48
 */

public class HomeGridAdapter extends BaseAdapter<SparseArray<String>> {


    public HomeGridAdapter(@LayoutRes int layoutId, List<SparseArray<String>> data) {
        super(layoutId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, int position, SparseArray<String> data) {
        holder.setText(R.id.ada_home_grid_tv, data.valueAt(position));
        Log.i("homeGrid",data.keyAt(position)+"===");
        ImageView iv = holder.getView(R.id.ada_home_grid_iv);
        iv.setImageLevel(data.keyAt(position));
    }
}
