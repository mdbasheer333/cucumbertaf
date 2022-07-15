package org.cucumbertaf.utils.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

//    public List<Map<String, String>> getAllData(String spath, String sname, int iteration) {
//        ExcelConfiguration config = new ExcelConfiguration.ExcelConfigurationBuilder()
//                .setFileName("Excel")
//                .setFileLocation("Location")
//                .setSheetName("Sheet")
//                .setIndex(iteration)
//                .build();
//        return new ExcelDataReader(config).getAllRows();
//    }

    public List<Map<String, String>> getAllData(String spath, String sname, int iteration) {
        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("spath", spath);
        map.put("sname", sname);
        map.put("iteration", String.valueOf(iteration));
        data.add(map);
        return data;
    }

}
