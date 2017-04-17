package com.ln.adapters;

import android.util.SparseArray;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ln.view.R;

import java.util.List;

/**
 * description: 首页网格布局适配器
 * Created by liNan on 2017/4/17 9:48
 */

public class HomeGridAdapter extends BaseQuickAdapter<SparseArray<String>, BaseViewHolder> {


    public HomeGridAdapter(int layoutResId, List<SparseArray<String>> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SparseArray<String> item) {
        helper.setText(R.id.ada_home_grid_tv, item.valueAt(0));
        ImageView mImageView = helper.getView(R.id.ada_home_grid_iv);
        mImageView.setImageLevel(item.keyAt(0));
    }
}
