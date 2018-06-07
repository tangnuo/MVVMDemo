package com.kedacom.mvvmdemo.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.kedacom.mvvmdemo.view.NewListActivity;

/**
 * @Dec ：事件处理
 * @Author : Caowj
 * @Date : 2018/6/7 10:28
 */
public class EventHandler {

    private View.OnClickListener myListener;

    public View.OnClickListener getMyListener() {
        return myListener;
    }

    public void setMyListener(View.OnClickListener myListener) {
        this.myListener = myListener;
    }

    public void gotoNews(View view) {
        Context mContext = view.getContext();
        Intent mIntent = new Intent(mContext, NewListActivity.class);
        mContext.startActivity(mIntent);
    }

    public void gotoNews2(View view, String msg) {
        Context mContext = view.getContext();
        Toast.makeText(mContext, "数据" + msg, Toast.LENGTH_SHORT).show();
    }


}
