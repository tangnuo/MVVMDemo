package com.kedacom.mvvmdemo.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/12 10:47
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        //默认初始化：https://github.com/orhanobut/logger
//        Logger.addLogAdapter(new AndroidLogAdapter());

        //自定义初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .logStrategy(new LogcatLogStrategy()) //（可选）更改要打印的日志策略。 默认LogCat
                .tag("CaoWJ")   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));


//        //保存日志到文件
//        FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
//                .tag("custom")
//                .build();
//
//        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
    }


}
