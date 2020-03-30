package com.guango.phonebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;


import com.guango.base.model.UserInfo;
import com.guango.base.store.GlobalInfo;
import com.guango.myui.MyAdapter;

import java.util.LinkedList;
import java.util.List;

public class PhonebookAdapter extends MyAdapter<UserInfo> {

    interface IView{
        void requestFriend(String toWhom);

        void refuseRequest(String name);

        void acceptRequest(String name);

        void notifyDataChanged();
    }

    private IView mView;

    public PhonebookAdapter(IView view){
        GlobalInfo.dataCenter.phonebook.observeForever(new Observer<LinkedList<UserInfo>>() {
            @Override
            public void onChanged(LinkedList<UserInfo> userInfos) {
                setData(userInfos);
            }
        });
        mView = view;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_friend, parent, false);
        return new PhonebookViewHolder(view, mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PhonebookViewHolder){
            if(getItemViewType(position) == UserInfo.CONTACT_TYPE.DONE.ordinal()){
                ((PhonebookViewHolder) holder).bindData(mData.get(position));
            }
            else if(getItemViewType(position) == UserInfo.CONTACT_TYPE.PENDING.ordinal()){
                ((PhonebookViewHolder) holder).bindPreFriend(mData.get(position));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType().ordinal();
    }
}
