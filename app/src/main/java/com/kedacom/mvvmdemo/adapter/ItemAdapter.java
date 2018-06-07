package com.kedacom.mvvmdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.kedacom.mvvmdemo.R;
import com.kedacom.mvvmdemo.bean.ItemBean;
import com.kedacom.mvvmdemo.databinding.ItemViewBinding;

import java.util.List;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/7 14:06
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.BindingHolder> {

    private List<ItemBean> list;
    public Context mContext;

    public ItemAdapter(Context mContext, List<ItemBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ItemAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_view, parent, false);

        BindingHolder holder = new BindingHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.BindingHolder holder, int position) {
        ItemViewBinding binding = holder.getBinding();
        binding.setVariable(BR.itemBean, list.get(position));
        binding.setVariable(BR.position, position);
        binding.setVariable(BR.adapter, this);
        binding.executePendingBindings();//防止闪烁
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * 跳转具体的activity
     * @param position
     */
    public void gotoInstance(int position) {
        ItemBean itemBean = list.get(position);
        mContext.startActivity(new Intent(mContext, itemBean.getCls()));
    }

    public class BindingHolder extends RecyclerView.ViewHolder {

        private ItemViewBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }


        public ItemViewBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemViewBinding binding) {
            this.binding = binding;
        }
    }
}

