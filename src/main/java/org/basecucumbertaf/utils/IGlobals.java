package org.basecucumbertaf.utils;

import java.util.Map;

public class IGlobals {
    public static ThreadLocal<Map<String, Integer>> counterTracker = new ThreadLocal<>();
    public static String mail_exl_path = System.getProperty("user.dir") + "\\src\\main\\resources\\mailinfo.xlsm";
    public static String data_exl_path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\testdata.xlsm";
    public static Throwable error;
}
