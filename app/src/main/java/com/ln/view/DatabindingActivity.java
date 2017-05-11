package com.ln.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ln.bean.UserBean;
import com.ln.view.databinding.ActivityDatabindingBinding;

public class DatabindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        UserBean bean = new UserBean("头条", "问答", "博客", "我的", true);
        binding.setUser(bean);
    }

    public void showToast(View view) {
        Toast.makeText(this, "viewiew", Toast.LENGTH_SHORT).show();
    }


}
