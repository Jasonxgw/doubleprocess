package com.honghe.guardtest;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
// 参考 ：https://blog.csdn.net/weixin_30342209/article/details/96362888?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164325598016780357274631%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=164325598016780357274631&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-96362888.pc_search_insert_ulrmf&utm_term=android+%E5%8F%8C%E8%BF%9B%E7%A8%8B%E5%AE%88%E6%8A%A4&spm=1018.2226.3001.4187
public class MyApplication extends Application {
    private static MainActivity mainActivity = null;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity activity) {
        mainActivity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (isMainProcess(getApplicationContext())) {
            // 是当前线程为主线程 启动本地服务
            startService(new Intent(this, LocalService.class));
        } else {
            return;
        }
    }

    /**
     * 获取当前进程名
     */
    public String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService
                (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

    public boolean isMainProcess(Context context) {
        /**
         * 是否为主进程
         */
        boolean isMainProcess;
        isMainProcess = context.getApplicationContext().getPackageName().equals
                (getCurrentProcessName(context));
        return isMainProcess;
    }
}
