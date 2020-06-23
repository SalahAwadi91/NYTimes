package com.sealow.nytimes.base;


import android.content.Context;

import androidx.annotation.StringRes;
import androidx.annotation.UiThread;

import com.sealow.nytimes.generic.SnackUtil;
import com.sealow.nytimes.generic.ToastUtil;


public interface BaseContract {

    interface Actions<V extends View> {
        @UiThread
        void onDestroy();

        @UiThread
        void onCreate(V view);

        void setContext(Context context);
    }

    interface View {
        @UiThread
        void start(Class mClass);

        @UiThread
        void showToast(String msg, ToastUtil.ToastTypes type);

        @UiThread
        void showToast(@StringRes int resId, ToastUtil.ToastTypes type);

        @UiThread
        void showSnackBar(String msg, SnackUtil.SnackTypes type, android.view.View.OnClickListener... onClickListeners);

        @UiThread
        void showSnackBar(@StringRes int resId, SnackUtil.SnackTypes type, android.view.View.OnClickListener... onClickListeners);

        @UiThread
        void showProgress();

        @UiThread
        void hideProgress();

        @UiThread
        void checkInternet();
    }
}
