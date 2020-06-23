package com.sealow.nytimes.generic;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;

import com.google.android.material.textfield.TextInputEditText;
import com.sealow.nytimes.R;
import com.shreyaspatil.MaterialDialog.AbstractDialog;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;


public class DialogUtils {

    public static void showDialog(Context context, String title, String msg) {
        MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(context.getString(R.string.okey), (dialogInterface, which) -> {
                })

                .build();
        mDialog.show();
    }


    public static void showBottomSheet(Context context, String title, String msg, @RawRes int rawAnim) {
        BottomSheetMaterialDialog mDialog = new BottomSheetMaterialDialog.Builder((Activity) context)
                .setTitle(title)
                .setMessage(msg)
                .setAnimation(rawAnim)
                .setPositiveButton(context.getString(R.string.okey), (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                })

                .build();
        mDialog.show();
    }

    public static void showDialog(Context context, String title, String msg, @RawRes int rawAnim) {
        MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                .setTitle(title)
                .setMessage(msg)
                .setAnimation(rawAnim)
                .setPositiveButton(context.getString(R.string.okey), (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                })

                .build();
        mDialog.show();
    }

    public static void showDialog(Context context, String title,
                                  String msg, @RawRes int rawAnim,
                                  AbstractDialog.OnClickListener positiveListener) {
        MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                .setTitle(title)
                .setMessage(msg)
                .setAnimation(rawAnim)
                .setPositiveButton(context.getString(R.string.okey), positiveListener)
                .build();
        mDialog.show();
    }


    public static void showDialog(Context context, @StringRes int title,
                                  @StringRes int msg) {
        MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                .setTitle(context.getString(title))
                .setMessage(context.getString(msg))
                .setPositiveButton(context.getString(R.string.okey), (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                })

                .build();
        mDialog.show();
    }



    public static void showDialog(Context context, @StringRes int title,
                                  @StringRes int msg, @RawRes int rawAnim) {
        MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                .setTitle(context.getString(title))
                .setMessage(context.getString(msg))
                .setAnimation(rawAnim)
                .setPositiveButton(context.getString(R.string.okey), (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                })

                .build();
        mDialog.show();
    }

    public static void showDialog(Context context, @StringRes int title,
                                  @StringRes int msg, @RawRes int rawAnim,
                                  AbstractDialog.OnClickListener positiveListener) {
        MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                .setTitle(context.getString(title))
                .setMessage(context.getString(msg))
                .setAnimation(rawAnim)
                .setPositiveButton(context.getString(R.string.okey), positiveListener)
                .build();
        mDialog.show();
    }
}
