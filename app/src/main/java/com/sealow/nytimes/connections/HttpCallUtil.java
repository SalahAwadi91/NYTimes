package com.sealow.nytimes.connections;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.sealow.nytimes.generic.MConstants;
import com.sealow.nytimes.interfaces.Callback;
import com.sealow.nytimes.interfaces.RxCallback;


import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;


public class HttpCallUtil {

    private static HttpCallUtil mInstance;
    private Handler handler = new Handler();
    private int counter = 0;
    private int delay = 1000;

    /**
     * This method apply singleton pattern for get shared HttpCallUtil instance.
     *
     * @return HttpCallUtil
     */
    public static synchronized HttpCallUtil getInstance() {
        if (mInstance == null)
            mInstance = new HttpCallUtil();
        return mInstance;
    }

    /**
     * For start new http call & handle (503 & 502) status codes.
     *
     * @param tag      The unique tag for receive it from listener.
     * @param call     The call you want execute.
     * @param callback The callback for handle request response.
     * @see Call
     * @see retrofit2.Callback

     */
    public <T> void newCall(int tag, Call<T> call, Callback callback) {
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.isSuccessful()) {
                    counter = 0;
                    callback.onSuccess(tag, response.body());
                } else if (response.code() < 500 ) {
                    if (counter != MConstants.RETRY_COUNT) {
                        handler.postDelayed(() -> {
                            call.clone().enqueue(this);
                            delay = MConstants.RETRY_DELAY_BETWEEN_REQUEST * counter;
                            counter++;
                        }, delay);
                    } else {
                        counter = 0;
                        callback.onUnSuccess(tag, call, response);
                    }
                } else {
                    counter = 0;
                    callback.onUnSuccess(tag, call, response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                counter = 0;
                callback.onFailed(tag, call, t);
            }
        });
    }

    /**
     * For start new two http call and retrieve result as list of objects.
     *
     * @param observable1 The observable you want execute.
     * @param callback    The callback for handle request response.
     * @see Call
     * @see retrofit2.Callback
     */
    public <T> void newOneCall(RxCallback callback, Observable<T> observable1) {
        Scheduler scheduler = Schedulers.newThread();
                  observable1
                          .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object object) {
                        callback.onNext(object, observable1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }
    /**
     * For start new two http call and retrieve result as list of objects.
     *
     * @param observable1 The observable you want execute.
     * @param callback    The callback for handle request response.
     * @see Call
     * @see retrofit2.Callback
     */
    public <T, T1> void newTwoCalls(RxCallback callback, Observable<T> observable1, Observable<T1> observable2) {
        Scheduler scheduler = Schedulers.newThread();
        Observable.zip(observable1, observable2, (t, t2) -> Arrays.asList(t, t2)).subscribeOn(scheduler)
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Object> object) {
                        callback.onNext(object, observable1, observable2);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }

    /**
     * For start new three http call and retrieve result as list of objects.
     *
     * @param observable1 The observable you want execute.
     * @param callback    The callback for handle request response.
     * @see Call
     * @see retrofit2.Callback
     */
    public <T, T2, T3> void newThreeCalls(RxCallback callback, Observable<T> observable1,
                                          Observable<T2> observable2, Observable<T3> observable3) {
        Scheduler scheduler = Schedulers.newThread();
        Observable.zip(observable1, observable2, observable3, (t, t2, t3) ->
                Arrays.asList(t, t2, t3)).subscribeOn(scheduler)
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Object> object) {
                        callback.onNext(object, observable1, observable2);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }
}
