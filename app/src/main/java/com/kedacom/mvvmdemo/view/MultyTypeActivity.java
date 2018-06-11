package com.kedacom.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.kedacom.mvvmdemo.R;
import com.kedacom.mvvmdemo.adapter.MultiRecyclerViewAdapter;
import com.kedacom.mvvmdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * 多类型列表
 * <p>
 * https://github.com/alidili/Demos/tree/master/DataBindingDemo
 * </p>
 *
 * @author Caowj
 * @Date 2018/6/7 15:54
 */
public class MultyTypeActivity extends AppCompatActivity {

    List<String> dataBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initData();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultiRecyclerViewAdapter multiRecyclerViewAdapter = new MultiRecyclerViewAdapter(dataBeans);
        binding.recyclerView.setAdapter(multiRecyclerViewAdapter);

    }

    private void initData() {
        dataBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataBeans.add(String.valueOf(i));
        }
    }

}
