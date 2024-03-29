package com.nengxin.example.exampleproject.model.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.Html;
import android.view.View;

import com.nengxin.example.exampleproject.R;
import com.nengxin.example.exampleproject.view.BaseActivity;


public class DialogUtil {
    public static AlertDialog createDialogView(Context context, int titleResId, int msgResId,
                                               DialogInterface.OnClickListener leftListener, int leftResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setMessage(msgResId);
        builder.setNegativeButton(leftResId, leftListener);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, int titleResId, String msg,
                                               DialogInterface.OnClickListener leftListener, int leftResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setMessage(msg);
        builder.setNegativeButton(leftResId, leftListener);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, String msg,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, int msg,
                                               DialogInterface.OnClickListener leftListener, int leftResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setNegativeButton(leftResId, leftListener);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, int titleResId, int msgResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setMessage(msgResId);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, int titleResId, String msgResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setMessage(msgResId);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogViewWithFinish(final Context context, int msgResId,
                                                         DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msgResId);
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ((BaseActivity) context).finish();
                ((BaseActivity) context).overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogViewWithFinish(final Context context, String msgResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msgResId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    dialog.dismiss();
                    ((BaseActivity) context).finish();
                    ((BaseActivity) context).overridePendingTransition(R.anim.left_in, R.anim.right_out);
                }
            });
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                ((BaseActivity) context).finish();
                ((BaseActivity) context).overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        builder.setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((BaseActivity) context).finish();
                ((BaseActivity) context).overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        return builder.create();
    }

    public static AlertDialog createDialogViewWithFinish(final Context context, int title, int msgResId,
                                                         DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msgResId);
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ((BaseActivity) context).finish();
                ((BaseActivity) context).overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogViewWithCancel(final Context context, int titleResId, int msgResId,
                                                         DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setMessage(msgResId);
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogViewWithCancel(final Context context, int titleResId, String msg,
                                                         DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setMessage(Html.fromHtml(msg));
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogViewWithCancel(final Context context, int msgResId,
                                                         DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msgResId);
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogViewWithCancel(final Context context, String msg,
                                                         DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }


    public static AlertDialog createDialogView(Context context, int titleResId, View view,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setView(view);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, int titleResId, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setView(view);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, View view,
                                               DialogInterface.OnClickListener leftListener, int leftResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setNegativeButton(leftResId, leftListener);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, int titleResId, View view,
                                               DialogInterface.OnClickListener leftListener, int leftResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(titleResId));
        builder.setView(view);
        builder.setNegativeButton(leftResId, leftListener);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static AlertDialog createDialogView(Context context, String titleString, View view,
                                               DialogInterface.OnClickListener leftListener, int leftResId,
                                               DialogInterface.OnClickListener rightListener, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleString);
        builder.setView(view);
        builder.setNegativeButton(leftResId, leftListener);
        builder.setPositiveButton(rightResId, rightListener);
        return builder.create();
    }

    public static void createDialogView(Context context, int msgId, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(msgId));
        builder.setCancelable(true);
        builder.setPositiveButton(rightResId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        try {
            builder.create().show();
        } catch (Exception e) {
        }
    }

    public static void createDialogView(Context context, String msg, int rightResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(Html.fromHtml(msg));
        builder.setCancelable(true);
        builder.setPositiveButton(rightResId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        try {
            builder.create().show();
        } catch (Exception e) {
        }
    }

    public static void createDialogView(Context context, String msg) {
        createDialogView(context, msg, R.string.btn_confirm);
    }

    public static void createDialogView(Context context, int msgId) {
        createDialogView(context, msgId, R.string.btn_confirm);
    }


}
