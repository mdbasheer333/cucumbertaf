package org.cucumbertaf.utils.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public List<Map<String, String>> getData(String spath, String sname, int iteration) {
        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("spath", spath);
        map.put("sname", sname);
        map.put("iteration", String.valueOf(iteration));
        data.add(map);
        return data;
    }

}
