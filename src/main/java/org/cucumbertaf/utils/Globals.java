package org.cucumbertaf.utils;

import java.util.Map;

public class Globals {
    public static ThreadLocal<Map<String, Integer>> counterTracker = new ThreadLocal<>();
    public static String mail_exl_path = System.getProperty("user.dir") + "\\src\\main\\resources\\mailinfo.xlsx";
    public static String data_exl_path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\testdata.xlsx";
    public static Throwable error;
}
