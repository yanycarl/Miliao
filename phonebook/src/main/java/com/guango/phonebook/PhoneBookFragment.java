package com.guango.phonebook;

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

import com.guango.base.Interface.IUserInfo;
import com.guango.base.model.SearchFriendResponse;
import com.guango.base.model.UserInfo;
import com.guango.base.store.GlobalInfo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class PhoneBookFragment extends Fragment implements View.OnClickListener, PhonebookAdapter.IView, PhonebookPresenter.IView {

    public static PhoneBookFragment newInstance() {
        return new PhoneBookFragment();
    }

    private RecyclerView mList;
    private PhonebookPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phonebook, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mList = view.findViewById(R.id.message_list);
        view.findViewById(R.id.add_friend).setOnClickListener(this);
        initViews();
    }

    private void initViews() {
        PhonebookAdapter adapter = new PhonebookAdapter(this);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mList.setAdapter(adapter);

        if(GlobalInfo.dataCenter.phonebook.getValue() == null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("客服小姐姐");
            userInfo.setType(IUserInfo.CONTACT_TYPE.DONE);
            LinkedList<UserInfo> phoneBook = new LinkedList<>();
            phoneBook.addFirst(userInfo);
            GlobalInfo.dataCenter.phonebook.postValue(phoneBook);
        }

        mPresenter = new PhonebookPresenter(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_friend){
            Intent intent = new Intent(this.getActivity(), NewFriendActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void requestFriend(String toWhom) {
        mPresenter.confirmRequest(toWhom);
    }

    @Override
    public void refuseRequest(String name) {
        mPresenter.replyRequest(name, false);
    }

    @Override
    public void acceptRequest(String name) {
        mPresenter.replyRequest(name, true);
    }

    @Override
    public void notifyDataChanged() {

    }

    @Override
    public void onReceivedResult(SearchFriendResponse response) {

    }
}
