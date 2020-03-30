package com.guango.miliao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.guango.miliao.R;

public class BottomTabFragment extends Fragment implements View.OnClickListener {

    public interface IView {
        void setMessage();

        void setMine();

        void setPhonebook();
    }

    public static BottomTabFragment newInstance(IView view) {

        BottomTabFragment fragment = new BottomTabFragment();
        fragment.mView = view;
        return fragment;
    }

    private IView mView;

    private ImageView mPicMessage;
    private ImageView mPicMine;
    private ImageView mPicPhone;
    private TextView mTextMessage;
    private TextView mTextMine;
    private TextView mTextPhone;

    private View mMessage;
    private View mMine;
    private View mPhonebook;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessage = view.findViewById(R.id.message);
        mMine = view.findViewById(R.id.mine);
        mPhonebook = view.findViewById(R.id.phonebook);
        mPicMessage = mMessage.findViewById(R.id.image);
        mPicMine = mMine.findViewById(R.id.image);
        mTextMessage = mMessage.findViewById(R.id.name);
        mTextMine = mMine.findViewById(R.id.name);
        mPicPhone = mPhonebook.findViewById(R.id.image);
        mTextPhone = mPhonebook.findViewById(R.id.name);

        initView();
    }

    @SuppressWarnings("deprecation")
    private void initView() {
        mTextMine.setText("我的");
        mTextMessage.setText("消息");
        mTextPhone.setText("通讯录");
        mPicMine.setImageDrawable(getResources().getDrawable(R.drawable.icon_me));
        mPicMessage.setImageDrawable(getResources().getDrawable(R.drawable.icon_message));
        mPicPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_iconfont_phonebook));

        mMessage.setOnClickListener(this);
        mMine.setOnClickListener(this);
        mPhonebook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mine) {
            mView.setMine();
        } else if (v.getId() == R.id.message) {
            mView.setMessage();
        } else if (v.getId() == R.id.phonebook) {
            mView.setPhonebook();
        }
    }
}
