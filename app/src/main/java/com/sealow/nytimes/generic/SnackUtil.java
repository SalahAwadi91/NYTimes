package com.sealow.nytimes.generic;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.sealow.nytimes.R;


/**
 * For show custom {@link Snackbar} color with icon & retry button.
 */
@SuppressWarnings("unused")
@SuppressLint("InflateParams")
public class SnackUtil {

    private static SnackUtil mInstance;
    private Snackbar snackbar;

    /**
     * This method apply singleton pattern for get shared SnackUtil instance.
     *
     * @return SnackUtil
     */
    @UiThread
    public static synchronized SnackUtil getInstance() {
        if (mInstance == null) {
            mInstance = new SnackUtil();
        }
        return mInstance;
    }

    private SnackUtil() {

    }

    /**
     * For show green {@link Snackbar} with done icon.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @see Snackbar#make(View, CharSequence, int)
     */
    @UiThread
    public void success(View rootView, String msg, View.OnClickListener... onClickListener) {
        showSnack(rootView, msg, R.color.green, R.drawable.ic_done, onClickListener);
    }

    /**
     * For show light red {@link Snackbar} with error icon.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @see Snackbar#make(View, CharSequence, int)
     */
    @UiThread
    public void failed(View rootView, String msg, View.OnClickListener... onClickListener) {
        showSnack(rootView, msg, R.color.lightRed, R.drawable.ic_close, onClickListener);
    }

    /**
     * For show orange {@link Snackbar} with warning icon.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @see Snackbar#make(View, CharSequence, int)
     */
    @UiThread
    public void warning(View rootView, String msg, View.OnClickListener... onClickListener) {
        showSnack(rootView, msg, R.color.orange, R.drawable.ic_warning, onClickListener);
    }

    /**
     * For show light blue {@link Snackbar} with info icon.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @see Snackbar#make(View, CharSequence, int)
     */
    @UiThread
    public void info(View rootView, String msg, View.OnClickListener... onClickListener) {
        showSnack(rootView, msg, R.color.lightBlue, R.drawable.ic_info, onClickListener);
    }

    /**
     * For show default {@link Snackbar}.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @see Snackbar#make(View, CharSequence, int)
     */
    @UiThread
    public void defaultSnack(View rootView, String msg, View.OnClickListener... onClickListener) {
        int duration = onClickListener != null && onClickListener.length > 0 ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_LONG;
        Snackbar snackbar = Snackbar.make(rootView, msg, duration);

        if (onClickListener != null && onClickListener.length > 0) {
            snackbar.setAction(R.string.retry, onClickListener[0]);
        }

        snackbar.show();
    }

    /**
     * For show {@link Snackbar} by type.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @see SnackTypes
     */
    @UiThread
    public void snack(View rootView, String msg, SnackTypes type, View.OnClickListener... onClickListener) {
        switch (type) {
            case FAILED:
                failed(rootView, msg, onClickListener);
                break;
            case INFO:
                info(rootView, msg, onClickListener);
                break;
            case SUCCESS:
                success(rootView, msg, onClickListener);
                break;
            case WARNING:
                warning(rootView, msg, onClickListener);
                break;
            case DEFAULT:
                defaultSnack(rootView, msg, onClickListener);
                break;
        }
    }

    /**
     * For show custom {@link Snackbar} color with icon.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param color           The {@link Snackbar} background color.
     * @param icon            The icon you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @see Snackbar#make(View, CharSequence, int)
     */
    @UiThread
    private void showSnack(View rootView, String msg, @ColorRes int color, @DrawableRes int icon, View.OnClickListener... onClickListener) {
        int duration = onClickListener != null && onClickListener.length > 0 ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_LONG;
        snackbar = Snackbar.make(rootView, "", duration);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.removeAllViews();
        layout.setPadding(0, 0, 0, 0);
        layout.addView(getView(rootView, msg, color, icon, onClickListener));
        snackbar.show();
    }

    /**
     * For get {@link Snackbar} custom view.
     *
     * @param rootView        The view you want show {@link Snackbar}.
     * @param msg             The message you want preview to user.
     * @param color           The {@link Snackbar} background color.
     * @param icon            The icon you want preview to user.
     * @param onClickListener Listener for click on retry button don't send any params
     *                        if you want hide retry button.
     * @return Custom {@link Snackbar} view
     * @see LayoutInflater#inflate(int, ViewGroup)
     */
    @UiThread
    private View getView(View rootView, String msg, @ColorRes int color, @DrawableRes int icon, View.OnClickListener... onClickListener) {
        View view = LayoutInflater.from(rootView.getContext()).inflate(R.layout.snack_layout, null);

        AppCompatTextView tvMsg = view.findViewById(R.id.tv_toast_msg);
        AppCompatImageView ivToastIcon = view.findViewById(R.id.iv_toast_icon);
        AppCompatTextView tvSnackAction = view.findViewById(R.id.tv_snack_action);

        view.findViewById(R.id.ll_toast).setBackgroundColor(
                ContextCompat.getColor(rootView.getContext(), color)
        );
        tvMsg.setText(msg);
        ivToastIcon.setImageResource(icon);
        ivToastIcon.setColorFilter(
                ContextCompat.getColor(rootView.getContext(), color)
        );

        if (onClickListener.length > 0) {
            tvSnackAction.setVisibility(View.VISIBLE);
            tvSnackAction.setOnClickListener((v) -> {
                onClickListener[0].onClick(v);
                if (snackbar != null)
                    snackbar.dismiss();

            });
        }

        return view;
    }

    public void dismiss() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    /**
     * Enum types for show {@link Snackbar} by global method and pass type as param.
     */
    public enum SnackTypes {
        FAILED, WARNING, INFO, SUCCESS, DEFAULT
    }

}
