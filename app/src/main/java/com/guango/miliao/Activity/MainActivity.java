package com.guango.miliao.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.guango.account.Activity.MineFragment;
import com.guango.base.activity.BaseActivity;
import com.guango.message.Activity.MessageFragment;
import com.guango.message.Presenter.MessagePresenter;
import com.guango.miliao.R;
import com.guango.miliao.fragment.BottomTabFragment;
import com.guango.phonebook.PhoneBookFragment;

import java.util.Objects;


public class MainActivity extends BaseActivity implements BottomTabFragment.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessagePresenter.startFetchMessage();
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = BottomTabFragment.newInstance(this);
        transaction.replace(R.id.bottom_tab_container, fragment);
        transaction.commit();

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        fragment = MessageFragment.newInstance();
        transaction2.replace(R.id.content_container, fragment);
        transaction2.commit();
    }

    @Override
    public void setMessage() {
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        Fragment fragment = MessageFragment.newInstance();
        transaction2.replace(R.id.content_container, fragment);
        transaction2.commit();
    }

    @Override
    public void setMine() {
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        Fragment fragment = MineFragment.newInstance();
        transaction2.replace(R.id.content_container, fragment);
        transaction2.commit();
    }

    @Override
    public void setPhonebook() {
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        Fragment fragment = PhoneBookFragment.newInstance();
        transaction2.replace(R.id.content_container, fragment);
        transaction2.commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认退出嘛");
        builder.setPositiveButton("是的", backDialogListener);
        builder.setNegativeButton("不要", backDialogListener);
        builder.show();
    }

    private DialogInterface.OnClickListener backDialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == -1) {
                closeAllActivity();
            } else {
                dialog.dismiss();
            }
        }
    };
}
