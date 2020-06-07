package com.guango.base.activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity {

    public static LinkedList<BaseActivity> activityStack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        insertActivity();

        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setStatusBarColor(Color.parseColor("#000000"));
        initViews();
        initData();
    }

    public static void closeAllActivity(){
        for(BaseActivity activity: activityStack){
            activity.finish();
        }
    }

    private void insertActivity() {
        if(activityStack == null){
            activityStack = new LinkedList<>();
        }
        activityStack.addFirst(this);
    }

    protected abstract void initViews();

    protected void initData(){}

    private void removeActivity() {
        if(activityStack != null && activityStack.size() >= 1){
            activityStack.removeFirst();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        removeActivity();
    }
}
