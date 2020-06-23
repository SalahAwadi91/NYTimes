package com.sealow.nytimes.interfaces;

public interface CallEvents {

    void onSuccess(Object response);
    void onFauiler(String error);
}
