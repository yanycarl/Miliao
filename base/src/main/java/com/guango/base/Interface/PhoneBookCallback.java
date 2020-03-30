package com.guango.base.Interface;

import com.guango.base.model.SearchFriendResponse;

public abstract class PhoneBookCallback {
    public void onGotSearchResult(SearchFriendResponse response){}

    public void onGotReplyResult(boolean success){}
}
