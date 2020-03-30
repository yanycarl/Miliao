package com.guango.message.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guango.base.activity.BaseActivity;
import com.guango.base.model.MessageGroupModel;
import com.guango.base.store.GlobalInfo;
import com.guango.message.Presenter.MessagePresenter;
import com.guango.message.R;
import com.guango.message.adapter.MessageDetailAdapter;

import java.util.LinkedList;
import java.util.Objects;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTitle;
    private RecyclerView mMessageListView;
    private Button mMessageButton;
    private View mScroll;
    private EditText mEditText;
    private Bundle bundle;
    private int group_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_message_open);

        mTitle = findViewById(R.id.title);
        mScroll = findViewById(R.id.scrollView);
        mMessageListView = findViewById(R.id.message_list);
        mEditText = findViewById(R.id.message_input);

        mMessageButton = findViewById(R.id.message_button);
        mMessageButton.setOnClickListener(this);

        findViewById(R.id.function).setVisibility(View.GONE);
        findViewById(R.id.close).setOnClickListener(this);
        findViewById(R.id.commit).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        if(getIntent().getExtras() != null){
            bundle = getIntent().getExtras();
        }

        if(bundle != null) {
            group_id = bundle.getInt("group_id");
        }
        LinkedList<MessageGroupModel> list = GlobalInfo.dataCenter.messageGroupModels.getValue();
        MessageGroupModel mMessageGroup = list != null ? list.get(group_id) : null;
        if(mMessageGroup != null){
            mTitle.setText(mMessageGroup.getName());
        }

        MessageDetailAdapter adapter = new MessageDetailAdapter();
        mMessageListView.setLayoutManager(new LinearLayoutManager(this));
        mMessageListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.message_button){
            mMessageButton.setVisibility(View.GONE);
            mScroll.setVisibility(View.VISIBLE);
        }
        else if(v.getId() == R.id.commit){
            wannaSendMessage();
        }
        else if(v.getId() == R.id.close){
            finish();
        }else if(v.getId() == R.id.function){
//            Intent intent = new Intent(MessageActivity.this, VoiceActivity.class);
//            intent.putExtra("name", mTitle.getText().toString());
//            intent.putExtra("type", 0);
//            startActivity(intent);
        }
    }

    private void wannaSendMessage() {
        if(mEditText.getText().length() == 0){
            Toast.makeText(this,"消息不能为空",Toast.LENGTH_SHORT).show();
        }
        else {
            mScroll.setVisibility(View.GONE);
            mMessageButton.setVisibility(View.VISIBLE);


            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View view = getWindow().peekDecorView();
            if (null != view) {
                assert imm != null;
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            String msg = mEditText.getText().toString();
            MessagePresenter.sendMsg(mTitle.getText().toString(), msg);
            mEditText.setText("");
        }
    }
}
