package com.guango.phonebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.guango.base.model.SimpleUserInfo;
import com.guango.myui.MyAdapter;


public class SearchFriendAdapter extends MyAdapter<SimpleUserInfo> {

    private PhonebookAdapter.IView mView;

    public SearchFriendAdapter(PhonebookAdapter.IView view){
        mView = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_friend, parent,false);
        return new PhonebookViewHolder(view, mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PhonebookViewHolder){
            ((PhonebookViewHolder) holder).bindNewFriend(mData.get(position));
        }
    }
}
