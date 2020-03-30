package com.guango.base.utilities;

import android.annotation.SuppressLint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHelper {

    private static ExecutorService service;

    @SuppressLint("CI_NotAllowInvokeExecutorsMethods")
    private static ExecutorService getService(){
        if(service == null){
            service =  Executors.newCachedThreadPool();
        }
        return service;
    }

    public static void runTask(Runnable runnable){
        getService().execute(runnable);
    }
}
