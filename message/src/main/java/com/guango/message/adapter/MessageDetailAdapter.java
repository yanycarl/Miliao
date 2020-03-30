package com.guango.message.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.guango.base.model.MessageModel;
import com.guango.base.store.GlobalInfo;
import com.guango.message.ui.MessageDetailViewHolder;
import com.guango.message.R;

import java.util.List;

public class MessageDetailAdapter extends RecyclerView.Adapter implements Observer {

    public void setMessageModels(List<MessageModel> messageModels) {
        this.messageModels = messageModels;
        notifyDataSetChanged();
    }

    private List<MessageModel> messageModels;

    public MessageDetailAdapter() {
        GlobalInfo.dataCenter.currentGroup.observeForever(this);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        GlobalInfo.dataCenter.messageGroupModels.removeObserver(this);
    }

    @Override
    public int getItemViewType(int position) {
        return messageModels.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left, parent, false);
        } else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right, parent, false);
        }
        return new MessageDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MessageDetailViewHolder) {
            MessageDetailViewHolder temp = (MessageDetailViewHolder) holder;
            temp.setData(messageModels.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(messageModels == null){
            return 0;
        }
        return messageModels.size();
    }

    @Override
    public void onChanged(Object o) {
        try {
            setMessageModels((List<MessageModel>) o);
        } catch (ClassCastException e) {
            Log.e("yanyao", e.toString());
        }
    }
}
