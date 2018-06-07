package com.kedacom.mvvmdemo.view;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kedacom.mvvmdemo.R;
import com.kedacom.mvvmdemo.databinding.ActivityMainBinding;
import com.kedacom.mvvmdemo.helper.MyHandler;
import com.kedacom.mvvmdemo.util.ToastUtils;
import com.kedacom.mvvmdemo.viewmodel.ExpressViewModel;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private ExpressViewModel expressViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        expressViewModel = new ExpressViewModel(binding);

        binding.setMsg("1234");
        initClick(binding);
        showData();


    }

    private void showData() {
        // 显示Loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在获取快递信息...");
        expressViewModel.isShowLoading.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (expressViewModel.isShowLoading.get()) {
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        });

        // 显示错误信息
        expressViewModel.errorMessage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Toast.makeText(MainActivity.this, expressViewModel.errorMessage.get(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initClick(ActivityMainBinding binding) {
        // 点击事件
        binding.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressViewModel.getExpressInfo("yuantong", "11111111111");
            }
        });



        MyHandler myHandler = new MyHandler();
        binding.setHandler(myHandler);



        myHandler.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(MainActivity.this,"按钮指向的是变量");
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(MainActivity.this,"通过id指定方法");
            }
        });
    }

}
