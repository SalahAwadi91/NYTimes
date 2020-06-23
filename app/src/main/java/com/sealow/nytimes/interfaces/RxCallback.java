package com.sealow.nytimes.interfaces;


import androidx.annotation.UiThread;

import java.util.List;

import io.reactivex.Observable;


public interface RxCallback {

    @UiThread
    void onFailed(String error);

    @UiThread
    void onNext(Object objects, Observable observables);
    @UiThread
    void onNext(List<Object> objects, Observable... observables);
    @UiThread
    void onComplete();
}
