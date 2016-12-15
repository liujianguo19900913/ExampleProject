package com.nengxin.example.exampleproject.model.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.nengxin.example.exampleproject.model.application.ActivityStackControlUtil;


public class ExitDialogBuilder {
	public static AlertDialog.Builder getBuilder(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setPositiveButton("隐藏", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				context.startActivity(intent);
			}
		});
		builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityStackControlUtil.finishProgram();
				android.os.Process.killProcess(android.os.Process.myPid());
				dialog.dismiss();
				// 返回键最小化程序
				// Intent intent = new Intent(Intent.ACTION_MAIN);
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// intent.addCategory(Intent.CATEGORY_HOME);
				// context.startActivity(intent);
			}
		});
		builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setTitle("真的要退出吗?");
		return builder;
	}
}
