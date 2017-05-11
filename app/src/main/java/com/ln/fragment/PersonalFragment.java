package com.ln.fragment;

import android.os.Bundle;
import android.view.View;

import com.ln.base.BaseFragment;
import com.ln.view.R;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by linan   on 2017/4/5.
 */

public class PersonalFragment extends BaseFragment {
    @Override
    public int setLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {
       RequestBody  descriptionString= RequestBody.create(MediaType.parse("multipart/form-data")," hello 这是文件描述");

     }
}
