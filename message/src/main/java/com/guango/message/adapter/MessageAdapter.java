package com.guango.message.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.MessageModel;
import com.guango.base.store.GlobalInfo;
import com.guango.message.R;
import com.guango.message.ui.MessageViewHolder;
import com.guango.myui.MyAdapter;

import java.util.LinkedList;

public class MessageAdapter extends MyAdapter<MessageGroupModel> implements Observer<LinkedList<MessageGroupModel>> {

    public MessageAdapter(){
        GlobalInfo.dataCenter.messageGroupModels.observeForever(this);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        GlobalInfo.dataCenter.messageGroupModels.removeObserver(this);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messagelist, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MessageViewHolder){
            LinkedList<MessageModel> linkedList = mData.get(position).getMessageGroup();
            ((MessageViewHolder) holder).setData(linkedList.getLast(), position);
        }
    }

    @Override
    public void onChanged(LinkedList<MessageGroupModel> messageGroupModels) {
        setData(messageGroupModels);
    }
}
