package com.guango.message.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guango.base.model.MessageModel;
import com.guango.base.store.GlobalInfo;
import com.guango.message.Presenter.MessagePresenter;
import com.guango.message.R;

public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mName;
    private TextView mPreviewMessage;
    private TextView mTime;
    private int mViewGroupId;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        mName = itemView.findViewById(R.id.message_name);
        mPreviewMessage = itemView.findViewById(R.id.message_preview);
        mTime = itemView.findViewById(R.id.message_time);
        itemView.setOnClickListener(this);
    }

    public void setData(MessageModel model, int viewGroupId){
        mViewGroupId = viewGroupId;
        if(model == null){
            return;
        }
        mTime.setText(model.getTime());
        if(GlobalInfo.dataCenter.messageGroupModels.getValue() != null) {
            mName.setText(GlobalInfo.dataCenter.messageGroupModels.getValue().get(viewGroupId).getName());
        }
        mPreviewMessage.setText(model.getInfo());
    }

    @Override
    public void onClick(View v) {
        MessagePresenter.openMessage(mViewGroupId);
    }
}
