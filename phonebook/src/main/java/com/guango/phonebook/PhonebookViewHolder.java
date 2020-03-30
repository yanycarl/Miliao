package com.guango.phonebook;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guango.base.Interface.IUserInfo;
import com.guango.base.model.SimpleUserInfo;
import com.guango.base.model.UserInfo;
import com.guango.router.RouterMap;

public class PhonebookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView name;
    private TextView hint;
    private View mOk;
    private View mNo;
    private View mSend;
    private PhonebookAdapter.IView mView;
    private View Whole;
    private IUserInfo userInfo;

    public PhonebookViewHolder(@NonNull View itemView, PhonebookAdapter.IView view) {
        super(itemView);

        name = itemView.findViewById(R.id.message_name);
        hint = itemView.findViewById(R.id.message_preview);
        mOk = itemView.findViewById(R.id.ok);
        mNo = itemView.findViewById(R.id.no);
        mSend = itemView.findViewById(R.id.request);
        mView = view;
        Whole = itemView;
        Whole.setOnClickListener(this);

        mOk.setOnClickListener(this);
        mNo.setOnClickListener(this);
        mSend.setOnClickListener(this);
    }

    public void bindData(UserInfo info){
        userInfo = info;
        mOk.setVisibility(View.GONE);
        mNo.setVisibility(View.GONE);
        hint.setVisibility(View.GONE);
        mSend.setVisibility(View.GONE);
        name.setText(info.getName());
    }

    public void bindNewFriend(SimpleUserInfo data){
        userInfo = data;
        hint.setText("");
        mOk.setVisibility(View.GONE);
        mNo.setVisibility(View.GONE);
        name.setText(data.getName());
        Whole.setEnabled(false);
        if(data.getType() == UserInfo.CONTACT_TYPE.SENT){
            mSend.setVisibility(View.GONE);
        }
    }

    public void bindPreFriend(UserInfo data){
        userInfo = data;
        hint.setText("请求加你为好友");
        mSend.setVisibility(View.GONE);
        name.setText(data.getName());
        Whole.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ok){
            mView.acceptRequest(name.getText().toString());
        }else if(v.getId() == R.id.no){
            mView.refuseRequest(name.getText().toString());
        }else if(v.getId() == R.id.request){
            mView.requestFriend(name.getText().toString());
            userInfo.setType(IUserInfo.CONTACT_TYPE.SENT);
            mView.notifyDataChanged();
        }else if(v == Whole){
            Bundle bundle = new Bundle();
            bundle.putString("name", name.getText().toString());
            RouterMap.openRoute("message_detail", bundle);
        }
    }
}
