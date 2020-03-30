package com.guango.account.Activity;

import com.guango.network.Fetcher.LoginFetcher;

public class LoginPresenter {
    public static void login(String user, String psw){
        LoginFetcher.login(user, psw);
    }

    public static void registerAccount(String user, String psw){
        LoginFetcher.registerAccount(user, psw);
    }
}
