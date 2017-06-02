package com.ln.fragment.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ln.adapters.NewsAdapter;
import com.ln.base.BaseAdapter;
import com.ln.bean.NewsBean;
import com.ln.https.RetrofitHelper;
import com.ln.utils.DividerItemDecoration;
import com.ln.view.R;
import com.ln.view.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class GuoJIFragment extends Fragment {
    private View view;
    private Context mContext;
    private NewsAdapter mAdapter;

    @BindView(R.id.news_rn)
    RecyclerView mRecyclerView;

    private static final String ARG_PARAM1 = "currType";
    private static final String ARG_PARAM2 = "param2";

    private String currNewsType; //表示新闻类型
    private String mParam2;

    public static GuoJIFragment newInstance(String currType, String param2) {
        GuoJIFragment fragment = new GuoJIFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, currType);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currNewsType = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = container.getContext();
        view = inflater.inflate(R.layout.fragment_guo_ji, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        LinearLayoutManager lm = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new NewsAdapter(R.layout.adapter_layout, null);
        mRecyclerView.setAdapter(mAdapter);
        reqNews();
        mAdapter.addItemClickListener(new BaseAdapter.OnItemClickListenerL() {
            @Override
            public void onItemClick(BaseAdapter adapter, int position) {
                NewsBean.ResultBean.DataBean b = (NewsBean.ResultBean.DataBean) adapter.getItem(position);
                Intent mIntent = new Intent(mContext, WebActivity.class);
                mIntent.putExtra("url", b.getUrl());
                mIntent.putExtra("title", b.getCategory());
                startActivity(mIntent);
            }
        });
    }

    private void reqNews() {
        RetrofitHelper.getInstance().creat().reqNews("bc05474dd386731c3ffc5946620ec1a6", currNewsType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(@NonNull NewsBean newsBean) throws Exception {
                        mAdapter.addData(newsBean.getResult().getData());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
