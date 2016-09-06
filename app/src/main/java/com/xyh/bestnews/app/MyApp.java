package com.xyh.bestnews.app;

import android.app.Application;
import android.os.Handler;

import org.xutils.x;

/**
 * Created by Administrator on 2016/8/31.
 */
public class MyApp extends Application{
    private  static  MyApp app;
    public   static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        x.Ext.init(this);
        handler=new Handler();
    }
    public  static  MyApp getApp(){
        return  app;
    }
}
