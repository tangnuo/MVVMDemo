package com.kedacom.mvvmdemo.util;

import com.orhanobut.logger.Logger;
/**
 * @Dec ：日志工具类
 * @Author : Caowj
 * @Date : 2018/6/12 10:45
 */
public class LogUtil {
    public static void d(String message) {
        Logger.d(message);
    }

    public static void v(String message) {
        Logger.v(message);
    }

    public static void e(String message) {
        Logger.e(message);
    }
}
