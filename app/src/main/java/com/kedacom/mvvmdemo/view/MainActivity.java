package com.kedacom.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.kedacom.mvvmdemo.R;
import com.kedacom.mvvmdemo.adapter.ItemAdapter;
import com.kedacom.mvvmdemo.bean.ItemBean;
import com.kedacom.mvvmdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemAdapter recyclerViewAdapter = new ItemAdapter(this, beanList);
        binding.recyclerView.setAdapter(recyclerViewAdapter);
    }


    private void initData() {
        beanList = new ArrayList<>();
        beanList.add(new ItemBean("新闻列表", NewListActivity.class));
        beanList.add(new ItemBean("新闻列表", NewListActivity.class));
    }
}
