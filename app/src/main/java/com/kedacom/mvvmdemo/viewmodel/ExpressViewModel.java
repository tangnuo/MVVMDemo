package com.kedacom.mvvmdemo.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.kedacom.mvvmdemo.bean.ExpressInfo;
import com.kedacom.mvvmdemo.databinding.ActivityMainBinding;
import com.kedacom.mvvmdemo.net.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/6 15:07
 */
public class ExpressViewModel {

    public ExpressInfo expressInfo;

    // 是否显示Loading
    public final ObservableBoolean isShowLoading = new ObservableBoolean();
    // 错误信息
    public final ObservableField<String> errorMessage = new ObservableField<>();

    public ExpressViewModel(ActivityMainBinding binding) {
        expressInfo = new ExpressInfo();
        binding.setViewModel(this);
    }

    /**
     * 获取快递信息
     *
     * @param type   快递类型
     * @param postid 快递单号
     */
    public void getExpressInfo(String type, String postid) {
        isShowLoading.set(true);

        RetrofitHelper.getInstance().getApiService().getExpressInfoRx(type, postid)
                .subscribeOn(Schedulers.io()) // 在子线程中进行Http访问
                .observeOn(AndroidSchedulers.mainThread()) // UI线程处理返回接口
                .subscribe(new DefaultObserver<ExpressInfo>() {  // 订阅
                    @Override
                    public void onNext(@NonNull ExpressInfo expressInfo) {
                        ExpressViewModel.this.expressInfo.setExpressInfo(expressInfo);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorMessage.set(e.getMessage());
                        isShowLoading.set(false);
                    }

                    @Override
                    public void onComplete() {
                        isShowLoading.set(false);
                    }
                });
    }

}
