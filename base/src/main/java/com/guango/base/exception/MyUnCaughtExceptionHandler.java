package com.guango.base.exception;

import android.app.AlertDialog;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.guango.base.activity.BaseActivity;
import com.guango.base.store.GlobalInfo;

public class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Intent intent = new Intent(GlobalInfo.getGlobalContext(), ExceptionActivity.class);
        StringBuilder info = new StringBuilder();
        info.append("程序发生错误，即将退出应用，详细信息如下： \n");
        info.append(e.getLocalizedMessage()).append("\n");
        for(StackTraceElement element: e.getStackTrace()){
            info.append(element.toString()).append("\n");
        }
        intent.putExtra(ExceptionActivity.STACK_INFO, info.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.activityStack.getFirst());
        builder.setMessage(info.toString());
        builder.show();
    }
}
