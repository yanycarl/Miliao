package com.guango.account.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.guango.account.R;
import com.guango.base.model.MessageGroupModel;
import com.guango.base.model.MessageModel;
import com.guango.base.store.GlobalInfo;
import com.guango.router.RouterMap;

import java.util.LinkedList;

public class MineFragment extends Fragment {

    private EditText mAddFriendIn;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAddFriendIn = view.findViewById(R.id.add_friend_input);
        Button mAddFriend = view.findViewById(R.id.add_friend);
        mAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend();
            }
        });
    }

    private void addFriend(){
        int group = 0;
        LinkedList<MessageGroupModel> lists = GlobalInfo.dataCenter.messageGroupModels.getValue();
        LinkedList<MessageModel> single = null;
        if(lists == null){
            lists = new LinkedList<>();
        }
        for(MessageGroupModel model:lists){
            if(model.getName().equals(mAddFriendIn.getText().toString())){
                single = model.getMessageGroup();
                break;
            }
            group ++;
        }
        if(single == null){
            MessageGroupModel model = new MessageGroupModel();
            model.setName(mAddFriendIn.getText().toString());
            MessageModel model1 = new MessageModel("你们已成为朋友","",0);
            model.addData(model1);
            single = model.getMessageGroup();
            lists.addFirst(model);
            group = 0;
        }

        GlobalInfo.dataCenter.currentGroup.setValue(single);
        GlobalInfo.dataCenter.messageGroupModels.setValue(lists);

        Bundle bundle = new Bundle();
        bundle.putInt("index", group);
        RouterMap.openRoute("message", bundle);
    }
}
