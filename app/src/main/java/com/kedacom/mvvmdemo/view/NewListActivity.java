package com.kedacom.mvvmdemo.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kedacom.mvvmdemo.R;
import com.kedacom.mvvmdemo.adapter.NewsAdapter;
import com.kedacom.mvvmdemo.databinding.ActivityNewListBinding;
import com.kedacom.mvvmdemo.helper.DialogHelper;
import com.kedacom.mvvmdemo.helper.MyHandler;
import com.kedacom.mvvmdemo.viewmodel.NewsVM;

/**
 * Android MVVM模式的RecyclerView Demo
 * <p>
 * https://github.com/zhouxu88/MVVMDemo
 * </p>
 */
public class NewListActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    private Context mContext;
    private ActivityNewListBinding binding;
    private NewsAdapter newsAdapter; //新闻列表的适配器
    private NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_list);
        mContext = this;

        initRecyclerView();
        newsVM = new NewsVM(newsAdapter);
        initDataChange();
    }

    private void initDataChange() {

        newsVM.isShowLoading.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                int isShow = newsVM.isShowLoading.get();
                if (isShow == 1) {
                    DialogHelper.getInstance().show(mContext, "加载中...");
                } else if (isShow == 2) {
                    loadComplete();
                } else if (isShow == 3) {
                    loadFailure();
                } else {
                    Log.d("caowj", "未知的isShow");
                }
            }
        });

        // 显示错误信息
        newsVM.errorMessage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Toast.makeText(mContext, newsVM.errorMessage.get(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        binding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        binding.newsRv.setAdapter(newsAdapter);
    }

    @Override
    public void onRefresh() {
        newsVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        newsVM.loadMoreData();
    }


    /***********************页面刷新********************/

    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
    }

    public void loadFailure() {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
    }
}
