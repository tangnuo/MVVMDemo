package com.kedacom.mvvmdemo.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;


import com.kedacom.mvvmdemo.adapter.NewsAdapter;
import com.kedacom.mvvmdemo.bean.NewsBean;
import com.kedacom.mvvmdemo.bean.SimpleNewsBean;
import com.kedacom.mvvmdemo.constant.NewsConstant;
import com.kedacom.mvvmdemo.net.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： 周旭 on 2017年10月18日 0018.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class NewsVM {
    private static final String TAG = "NewsVM";
    List<SimpleNewsBean> simpleNewsBeanList = new ArrayList<SimpleNewsBean>();
    private NewsAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型


    // 是否显示Loading
    public final ObservableInt isShowLoading = new ObservableInt();
    // 错误信息
    public final ObservableField<String> errorMessage = new ObservableField<>();

    public NewsVM(NewsAdapter mAdapter) {
        this.mAdapter = mAdapter;
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = NewsConstant.LoadData.FIRST_LOAD;
        loadNewsData(currPage);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = NewsConstant.LoadData.REFRESH;
        currPage = 1;
        loadNewsData(currPage);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = NewsConstant.LoadData.LOAD_MORE;
        currPage++;
        loadNewsData(currPage);
    }


    public void loadNewsData(final int page) {
        RetrofitHelper.getInstance().getApiService().getNewsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBean>() {
                    @Override
                    public void onNext(@NonNull NewsBean newsBean) {
                        Log.i(TAG, "onNext: ");
                        List<NewsBean.OthersBean> othersBeanList = newsBean.getOthers();
                        simpleNewsBeanList.clear();
                        if (othersBeanList != null && othersBeanList.size() > 0) {
                            for (NewsBean.OthersBean othersBean : othersBeanList) {
                                String thumbnail = othersBean.getThumbnail();
                                String name = othersBean.getName();
                                String description = othersBean.getDescription();
                                Log.i(TAG, "thumbnail:---->" + thumbnail);
                                Log.i(TAG, "name:---->" + name);
                                Log.i(TAG, "description:---->" + description);

                                //构造Adapter所需的数据源
                                SimpleNewsBean simpleNewsBean = new SimpleNewsBean();
                                simpleNewsBean.thumbnail.set(thumbnail);
                                simpleNewsBean.name.set(name);
                                simpleNewsBean.description.set(description);
                                simpleNewsBeanList.add(simpleNewsBean);

                                if (page > 1) {
                                    //这个接口暂时没有分页的数据，这里为了模拟分页，通过取第1条数据作为分页的数据
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        Log.i(TAG, "onStart: ");
                        loadStart();
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        Log.i(TAG, "onError: " + throwable.getMessage());
                        loadFailure(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadSuccess(simpleNewsBeanList);
                                loadComplete();
                            }
                        }, 2000);
                    }
                });
    }


    /***************************回调的接口**************************/


    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
    }


    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currPage > 1) {
            //加载失败需要回到加载之前的页数
            currPage--;
        }
//        mNewsView.loadFailure(message);
        isShowLoading.set(3);
        errorMessage.set(message);
    }


    public void loadStart() {
//        mNewsView.loadStart(loadType);
        isShowLoading.set(1);
    }


    public void loadComplete() {
//        mNewsView.loadComplete();
        isShowLoading.set(2);
    }
}

