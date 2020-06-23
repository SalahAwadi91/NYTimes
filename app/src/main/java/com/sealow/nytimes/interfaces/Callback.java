package com.sealow.nytimes.interfaces;

import androidx.annotation.UiThread;

import retrofit2.Call;
import retrofit2.Response;


public interface Callback {

    @UiThread
    void onSuccess(int tag, Object result);

    @UiThread
    void onFailed(int tag, Call call, Throwable throwable);

    @UiThread
    void onUnSuccess(int tag, Call call, Response response);
}
