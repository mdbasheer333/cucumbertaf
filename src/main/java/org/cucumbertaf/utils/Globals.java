package org.cucumbertaf.utils;

import java.util.Map;

public class Globals {
    public static ThreadLocal<Map<String, Integer>> counterTracker = new ThreadLocal<>();

}
