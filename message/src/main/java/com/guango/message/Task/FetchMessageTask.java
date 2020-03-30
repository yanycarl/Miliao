package com.guango.message.Task;

import com.guango.message.Presenter.MessagePresenter;

public class FetchMessageTask implements Runnable {
    @Override
    public void run() {
        while (true){
            MessagePresenter.fetchMessage();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
