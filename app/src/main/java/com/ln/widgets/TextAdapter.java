package com.ln.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ln.view.R;

/**
 * description: 使用adapterViewFlipper实现跑马灯效果
 * Created by liNan on 2017/4/14 15:14
 */

public class TextAdapter extends BaseAdapter {
    String[] lst;

    public TextAdapter(String[] lst) {
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst == null ? 0 : lst.length;
    }

    @Override
    public Object getItem(int position) {
        return lst[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
//        TextView tv = new TextView(parent.getContext());
//        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        tv.setText(lst.get(position));
        FlipperViewHolder holder;
        if (convertView == null) {
            holder = new FlipperViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_flipper, null);
            holder.tv = (TextView) convertView.findViewById(R.id.flipper_tv);
            holder.iv = (ImageView) convertView.findViewById(R.id.flipper_iv);
            convertView.setTag(holder);
        } else {
            holder = (FlipperViewHolder) convertView.getTag();
        }
        holder.tv.setText(lst[position]);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), lst[position], Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class FlipperViewHolder {
        TextView tv;
        ImageView iv;
    }

}
