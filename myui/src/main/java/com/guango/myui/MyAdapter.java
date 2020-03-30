package com.guango.myui;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class MyAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mData = new ArrayList<>(1);

    public void setData(List<T> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }
        return mData.size();
    }

}
