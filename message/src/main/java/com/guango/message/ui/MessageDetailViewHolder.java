package com.guango.message.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guango.base.model.MessageModel;
import com.guango.message.R;

public class MessageDetailViewHolder extends RecyclerView.ViewHolder {

    private ImageView mAvatar;
    private TextView mName;
    private TextView info;
    private TextView mTime;

    public MessageDetailViewHolder(@NonNull View itemView) {
        super(itemView);

//        mAvatar = itemView.findViewById(R.id.avatar);
//        mName = itemView.findViewById(R.id.message_name);
        info = itemView.findViewById(R.id.message_info);
//        mTime = itemView.findViewById(R.id.message_time);
    }

    public void setData(MessageModel model){
        info.setText(model.getInfo());
    }

}
