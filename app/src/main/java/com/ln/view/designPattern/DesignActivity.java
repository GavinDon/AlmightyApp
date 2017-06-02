package com.ln.view.designPattern;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ln.base.BaseActivity;
import com.ln.utils.DividerItemDecoration;
import com.ln.view.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设计模式
 */
public class DesignActivity extends BaseActivity {
    @BindView(R.id.design_rv)
    RecyclerView mRecyclerView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("设计模式");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(new DesignAdapter());
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_design;
    }

    /**
     * 列表适配器
     */
    class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.DesignViewHolder> implements View.OnClickListener {
        String[] designName = getResources().getStringArray(R.array.design);

        @Override
        public DesignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(DesignActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new DesignViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DesignViewHolder holder, int position) {
            holder.mTextView.setText(designName[position]);
            holder.mTextView.setTag(position);
            holder.mTextView.setOnClickListener(this);
        }

        @Override
        public int getItemCount() {
            return designName.length;
        }

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            switch (position){
                case 0:
                    gotoActivity(StrategyPatternActivity.class);
                    break;
            }
        }

        class DesignViewHolder extends RecyclerView.ViewHolder {
            @BindView(android.R.id.text1)
            TextView mTextView;

            public DesignViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }


    }
}
