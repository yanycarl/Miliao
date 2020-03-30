package com.guango.phonebook;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.guango.base.model.SearchFriendResponse;
import com.guango.base.model.SimpleUserInfo;
import com.guango.base.utilities.StringUtil;

import java.util.LinkedList;
import java.util.Objects;

public class NewFriendActivity extends AppCompatActivity implements View.OnClickListener, PhonebookPresenter.IView, PhonebookAdapter.IView {

    EditText mInput;
    View mCommit;
    RecyclerView mList;
    SearchFriendAdapter mAdapter;
    View mClose;

    private PhonebookPresenter mPresenter;

    private String request_code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_friend);

        mList = findViewById(R.id.search_list);
        mInput = findViewById(R.id.search_input);
        mCommit = findViewById(R.id.function);
        mClose = findViewById(R.id.close);

        mClose.setOnClickListener(this);
        mCommit.setOnClickListener(this);

        mPresenter = new PhonebookPresenter(this);

        mAdapter = new SearchFriendAdapter(this);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.function){
            mAdapter.setData(new LinkedList<SimpleUserInfo>());
            request_code = StringUtil.generateRansomString();
            mPresenter.searchFriend(mInput.getText().toString(), request_code);
        }else if(v.getId() == R.id.close) {
            finish();
        }
    }

    @Override
    public void onReceivedResult(SearchFriendResponse response) {
        if(response.getRequestCode().equals(request_code)) {
            if(response.getData() != null){
                mAdapter.setData(response.getData());
            }
            else {
                Toast.makeText(this, "没有找到相关结果", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void requestFriend(String toWhom) {
        mPresenter.confirmRequest(toWhom);
    }

    @Override
    public void refuseRequest(String name) {

    }

    @Override
    public void acceptRequest(String name) {

    }

    @Override
    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }
}
