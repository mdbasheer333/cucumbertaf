package org.cucumbertaf.utils.excel;

import io.cucumber.java.bs.I;
import io.cucumber.java.it.Ma;
import io.cucumber.java.sl.In;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cucumbertaf.utils.Globals;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;
    XSSFRow xssfRow;
    XSSFRow xssfRowHeader;
    XSSFCell xssfCell;

    String featureName;
    String filePath;
    int iteration;
    String scenarioName;
    String sheetName;

    public ExcelReader(String spath, String sheetName) {
        this.filePath = spath;
        this.featureName = sheetName;
    }

    public ExcelReader(String spath, String scenarioName, int iteration) {
        featureName = spath.split("/")[spath.split("/").length - 1].replaceAll(".feature", "");
        this.scenarioName = scenarioName;
        filePath = Globals.data_exl_path;
        this.iteration = iteration;
    }

    public void init() {
        try (FileInputStream fin = new FileInputStream(filePath)) {
            xssfWorkbook = new XSSFWorkbook(fin);
            xssfSheet = xssfWorkbook.getSheet(featureName);
            if (xssfSheet == null) {
                return;
            }
            xssfRowHeader = xssfSheet.getRow(0);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }

    public void writeToSheet(Map<String, Object> dataMap, int rowNumber) throws Exception {
        if (dataMap.entrySet().size() == 1) {
            return;
        }
        init();
        FileOutputStream fos = new FileOutputStream(filePath);
        dataMap.forEach((k, v) -> {
            try {
                writeToCell(rowNumber, k, v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        xssfWorkbook.write(fos);
        fos.close();
        flush();
    }

    public void writeToCell(int rowNumber, String columnName, Object valueToInsert) throws Exception {
        if (columnName.equalsIgnoreCase("iteration")) {
            return;
        }
        Map<String, Integer> map = getRowHeaderMap();
        xssfRow = xssfSheet.getRow(rowNumber);
        int colNumber = map.get(columnName);
        xssfRow.getCell(colNumber).setCellValue(String.valueOf(valueToInsert));
    }

    public void flush() {
        try {
            xssfWorkbook.close();
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }

    public Map<String, Integer> getRowHeaderMap() {
        Map<String, Integer> map = new HashMap<>();
        for (int j = 0; j < xssfRowHeader.getLastCellNum(); j++) {
            xssfCell = xssfRowHeader.getCell(j);
            if (xssfCell == null) {
                continue;
            }
            map.put(xssfRowHeader.getCell(j).getStringCellValue(), j);
        }
        return map;
    }

    public List<Map<String, String>> getAllSheetData() {

        List<Map<String, String>> data = new ArrayList<>();
        init();
        if (xssfWorkbook == null || xssfSheet == null) {
            return null;
        }
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            xssfRow = xssfSheet.getRow(i);
            if (xssfRow == null) {
                continue;
            }
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < xssfRow.getLastCellNum(); j++) {
                xssfCell = xssfRow.getCell(j);
                if (xssfCell == null) {
                    continue;
                }
                if (xssfCell.getCellType() == CellType.STRING) {
                    map.put(xssfRowHeader.getCell(j).getStringCellValue(), xssfCell.getStringCellValue());
                } else if (xssfCell.getCellType() == CellType.NUMERIC) {
                    map.put(xssfRowHeader.getCell(j).getStringCellValue(), String.valueOf(xssfCell.getNumericCellValue()));
                } else {
                    map.put(xssfRowHeader.getCell(j).getStringCellValue(), "");
                }
            }
            data.add(map);
        }
        flush();
        return data;
    }

    public List<Map<String, String>> getAllData() {
        List<Map<String, String>> data = new ArrayList<>();
        init();
        if (xssfWorkbook == null || xssfSheet == null) {
            return null;
        }
        int exeFlagColPos = getColPosition("ExecutionFlag");
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            xssfRow = xssfSheet.getRow(i);
            if (xssfRow == null) {
                continue;
            }
            if (xssfRow.getCell(exeFlagColPos).getStringCellValue().equalsIgnoreCase("yes")) {
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < xssfRow.getLastCellNum(); j++) {
                    xssfCell = xssfRow.getCell(j);
                    if (xssfCell == null) {
                        continue;
                    }
                    if (xssfCell.getCellType() == CellType.STRING) {
                        map.put(xssfRowHeader.getCell(j).getStringCellValue(), xssfCell.getStringCellValue());
                    } else if (xssfCell.getCellType() == CellType.NUMERIC) {
                        map.put(xssfRowHeader.getCell(j).getStringCellValue(), String.valueOf(xssfCell.getNumericCellValue()));
                    } else {
                        map.put(xssfRowHeader.getCell(j).getStringCellValue(), "");
                    }
                }
                data.add(map);
            }
        }
        flush();
        return data;
    }

    private int getColPosition(String colName) {
        int pos = -1;
        xssfRow = xssfSheet.getRow(0);
        int noOfColumns = xssfRow.getLastCellNum();
        for (int i = 0; i < noOfColumns; i++) {
            if (xssfRow.getCell(i).getStringCellValue().trim().equals(colName)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public Integer getNumberOfIterations() {
        init();
        int count = 0;
        int colPos = getColPosition("ExecutionFlag");
        if (xssfWorkbook == null || xssfSheet == null) {
            return null;
        }
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            xssfRow = xssfSheet.getRow(i);
            if (xssfRow == null) {
                continue;
            }
            xssfCell = xssfRow.getCell(colPos);
            if (xssfCell == null) {
                continue;
            }
            if (xssfCell.getStringCellValue().equalsIgnoreCase("yes")) {
                count++;
            }
        }
        flush();
        return count;
    }

}
