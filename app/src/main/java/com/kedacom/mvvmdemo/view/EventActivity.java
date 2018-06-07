package com.kedacom.mvvmdemo.view;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kedacom.mvvmdemo.R;
import com.kedacom.mvvmdemo.helper.EventHandler;
import com.kedacom.mvvmdemo.util.ToastUtils;
import com.kedacom.mvvmdemo.databinding.ActivityEventBinding;
import com.kedacom.mvvmdemo.viewmodel.ExpressViewModel;

public class EventActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private ExpressViewModel expressViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityEventBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event);
        expressViewModel = new ExpressViewModel(binding);

        binding.setMsg("1234");
        initClick(binding);
        showData();


    }

    /**
     * 显示数据变化
     */
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
                Toast.makeText(EventActivity.this, expressViewModel.errorMessage.get(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * 点击事件
     * <p>
     *     主要有一下几种形式：
     *     1、xml中指向具体方法；
     *     2、xml中指向OnClickListener变量(变量可以定义在xml中，也可以定义在类中)；
     *     3、代码中指定具体的按钮id；
     *     4、是否使用lambda表达式；
     * </p>
     * @param binding
     */
    private void initClick(ActivityEventBinding binding) {
        // 点击事件（变量定义在xml中）
        binding.setClickListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressViewModel.getExpressInfo("yuantong", "11111111111");
            }
        });



        EventHandler EventHandler = new EventHandler();
        binding.setHandler(EventHandler);

        //按钮指向的是变量（（变量定义在class中））
        EventHandler.setMyListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(EventActivity.this,"按钮指向的是变量");
            }
        });


        //通过id指定方法
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(EventActivity.this,"通过id指定方法");
            }
        });
    }
}
