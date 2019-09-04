package com.example.chronus.AlarmNotification;

/**
 * Toast提示显示工具类
 *
 */

import android.content.Context;
import android.widget.Toast;

import com.example.chronus.Notify;

public class ToastUtil {

    // 短时间显示Toast信息
    public static void showShort(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    // 长时间显示Toast信息
    public static void showLong(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    // 通知
    public static void showNotifiction(Context context ,String info){
        Notify nm = new Notify(context);
        nm.setNotification("提醒事项", info);
    }
}