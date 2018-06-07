package com.kedacom.mvvmdemo.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.kedacom.mvvmdemo.view.NewListActivity;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/7 10:28
 */
public class MyHandler {

    private View.OnClickListener listener;

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void gotoNews(View view) {
        Context mContext = view.getContext();
        Intent mIntent = new Intent(mContext, NewListActivity.class);
        mContext.startActivity(mIntent);
    }

    public void gotoNews(View view, String msg) {
        Context mContext = view.getContext();
        Toast.makeText(mContext, "数据" + msg, Toast.LENGTH_SHORT).show();
    }


}
