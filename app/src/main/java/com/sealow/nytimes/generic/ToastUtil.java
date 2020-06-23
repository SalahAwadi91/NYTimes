package com.sealow.nytimes.generic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.sealow.nytimes.R;


/**
 * For show custom {@link Toast} color with icon.
 */
@SuppressLint("InflateParams")
public class ToastUtil {

    private static ToastUtil mInstance;

    /**
     * This method apply singleton pattern for get shared ToastUtil instance.
     *
     * @return ToastUtil
     */
    @UiThread
    public static synchronized ToastUtil getInstance() {
        if (mInstance == null) {
            mInstance = new ToastUtil();
        }
        return mInstance;
    }

    private ToastUtil() {

    }

    /**
     * For show green {@link Toast} with done icon.
     *
     * @param context context a {@link Context} object.
     * @param msg     The message you want preview to user.
     * @see Toast#makeText(Context, CharSequence, int)
     */
    @UiThread
    public void success(Context context, String msg) {
        showToast(context, getView(context, msg, R.color.green, R.drawable.ic_done));
    }

    /**
     * For show light red {@link Toast} with error icon.
     *
     * @param context context a {@link Context} object.
     * @param msg     The message you want preview to user.
     * @see Toast#makeText(Context, CharSequence, int)
     */
    @UiThread
    public void failed(Context context, String msg) {
        showToast(context, getView(context, msg, R.color.lightRed, R.drawable.ic_close));
    }

    /**
     * For show orange {@link Toast} with warning icon.
     *
     * @param context context a {@link Context} object.
     * @param msg     The message you want preview to user.
     * @see Toast#makeText(Context, CharSequence, int)
     */
    @UiThread
    public void warning(Context context, String msg) {
        showToast(context, getView(context, msg, R.color.orange, R.drawable.ic_warning));
    }

    /**
     * For show light blue {@link Toast} with info icon.
     *
     * @param context context a {@link Context} object.
     * @param msg     The message you want preview to user.
     * @see Toast#makeText(Context, CharSequence, int)
     */
    @UiThread
    public void info(Context context, String msg) {
        showToast(context, getView(context, msg, R.color.lightBlue, R.drawable.ic_info));
    }

    /**
     * For show default {@link Toast}.
     *
     * @param context context a {@link Context} object.
     * @param msg     The message you want preview to user.
     * @see Toast#makeText(Context, CharSequence, int)
     */
    @UiThread
    public void defaultToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * For show toast by type {@link Toast}.
     *
     * @param context context a {@link Context} object.
     * @param msg     The message you want preview to user.
     * @see ToastTypes
     */
    @UiThread
    public void toast(Context context, String msg, ToastTypes type) {
        switch (type) {
            case FAILED:
                failed(context, msg);
                break;
            case INFO:
                info(context, msg);
                break;
            case SUCCESS:
                success(context, msg);
                break;
            case WARNING:
                warning(context, msg);
                break;
            case DEFAULT:
                defaultToast(context, msg);
                break;
        }
    }

    /**
     * For show green {@link Toast} with done icon.
     *
     * @param context context a {@link Context} object.
     * @param view    The custom view for preview to user.
     * @see Toast#makeText(Context, CharSequence, int)
     */
    @UiThread
    private void showToast(Context context, View view) {
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    /**
     * For get {@link Toast} custom view.
     *
     * @param context context a {@link Context} object.
     * @param msg     The message you want preview to user.
     * @param color   The {@link Toast} background color.
     * @param icon    The icon you want preview to user.
     * @return Custom {@link Toast} view
     * @see LayoutInflater#inflate(int, ViewGroup)
     */
    @UiThread
    private View getView(Context context, String msg, @ColorRes int color, @DrawableRes int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);

        AppCompatTextView tvMsg = view.findViewById(R.id.tv_toast_msg);
        AppCompatImageView ivToastIcon = view.findViewById(R.id.iv_toast_icon);

        ViewCompat.setBackgroundTintList(
                view.findViewById(R.id.ll_toast),
                ColorStateList.valueOf(ContextCompat.getColor(context, color))
        );
//        if (msg != null && !msg.isEmpty()) {
//            tvMsg.setText(msg);
//        } else {
//            tvMsg.setVisibility(View.GONE);
//        }
        tvMsg.setVisibility(View.GONE);
        ivToastIcon.setImageResource(icon);
        ivToastIcon.setColorFilter(
                ContextCompat.getColor(context, color)
        );
        return view;
    }

    /**
     * Enum types for show toast by global method and pass type as param.
     */
    public enum ToastTypes {
        FAILED, WARNING, INFO, SUCCESS, DEFAULT
    }

}
