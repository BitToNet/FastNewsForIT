package com.chingsoft.museum.apps;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.support.multidex.MultiDex;
import java.util.LinkedList;
import java.util.List;

public class App extends Application {
    public static  Context context;
    public static  Handler mainHandler;
    private static App     instance;
    private static final boolean        isDebug    = true;
    public static final  List<Activity> activities = new LinkedList<>();

    public Vibrator        mVibrator;



    private static String sessionId;

    public static void setSessionId(String id) {
        sessionId = id;
    }
    public static String getSessionId() {
        return sessionId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mainHandler = new Handler();


    }



    public static boolean isDebug() {
        return isDebug;
    }

    // 懒汉式单例模式
    public static App getInstance() {
        if (instance == null) {
            synchronized (App.class) {
                if (instance == null) {
                    instance = new App();
                }
            }
        }
        return instance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    /**
     * 完全退出
     * 一般用于“退出程序”功能
     */
    public static void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }
}
