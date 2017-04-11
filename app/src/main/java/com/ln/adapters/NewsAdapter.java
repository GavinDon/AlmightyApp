package com.ln.adapters;

import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ln.base.BaseAdapter;
import com.ln.base.BaseViewHolder;
import com.ln.bean.NewsBean;
import com.ln.view.R;

import java.util.List;

/**
 * Created by linan   on 2017/4/7.
 */

public class NewsAdapter extends BaseAdapter<NewsBean.ResultBean.DataBean> {

    public NewsAdapter(@LayoutRes int layoutId, List<NewsBean.ResultBean.DataBean> data) {
        super(layoutId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, int position, NewsBean.ResultBean.DataBean data) {
        holder.setText(R.id.ada_tv_title, data.getTitle())
                .setText(R.id.ada_tv_introduce, data.getUrl())
                .setText(R.id.ada_tv_from, data.getAuthor_name());

        Glide.with(mContext).load(data.getThumbnail_pic_s()).asBitmap().error(R.mipmap.icon).into((ImageView) holder.getView(R.id.ada_iv));


    }
}
