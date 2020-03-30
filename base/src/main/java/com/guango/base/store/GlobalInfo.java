package com.guango.base.store;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

public class GlobalInfo {
    @SuppressLint("CI_StaticFieldLeak")
    static private Activity activity;

    public static Activity getContext() {
        return activity;
    }

    public static void setContext(Activity context) {
        GlobalInfo.activity = context;
    }

    public static DataCenter dataCenter;

    public static String myName = "";

    public static Context getGlobalContext() {
        return globalContext;
    }

    public static void setGlobalContext(Context globalContext) {
        GlobalInfo.globalContext = globalContext;
    }

    @SuppressLint("CI_StaticFieldLeak")
    static private Context globalContext;

    public static ServiceManager getServiceManager() {
        return GlobalInfo.serviceManager;
    }

    public static void setServiceManager(ServiceManager serviceManager) {
        GlobalInfo.serviceManager = serviceManager;
    }

    private static ServiceManager serviceManager;
}
