package com.guango.message.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guango.message.R;
import com.guango.message.adapter.MessageAdapter;
import com.guango.router.RouterMap;

public class MessageFragment extends Fragment {

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    private RecyclerView mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mseeage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mList = view.findViewById(R.id.message_list);
        View mAddFriend = view.findViewById(R.id.add_friend);
        mAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterMap.openRoute("new_friend", null);
            }
        });
        initViews();
    }

    private void initViews() {
        MessageAdapter adapter = new MessageAdapter();
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mList.setAdapter(adapter);
    }
}
