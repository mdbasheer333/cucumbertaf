package org.cucumbertaf.utils.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
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
        filePath = spath;
        this.featureName = sheetName;
    }

    public ExcelReader(String spath, String scenarioName, int iteration) {
        featureName = spath.split("/")[spath.split("/").length - 1].replaceAll(".feature", "");
        this.scenarioName = scenarioName;
        filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\testdata.xlsx";
        this.iteration = iteration;
    }

    public void init() {
        try (FileInputStream fin = new FileInputStream(filePath)) {
            xssfWorkbook = new XSSFWorkbook(fin);
            xssfSheet = xssfWorkbook.getSheet(featureName);
            if (xssfSheet==null){
                return;
            }
            xssfRowHeader = xssfSheet.getRow(0);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            xssfWorkbook.close();
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
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

        int featureColPos = getColPosition("feature");
        int scenario = getColPosition("scenario");
        int iteration = getColPosition("iteration");
        for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
            xssfRow = xssfSheet.getRow(i);
            if (xssfRow == null) {
                continue;
            }
            if (xssfRow.getCell(featureColPos).getStringCellValue().equals(featureName)) {
                //  if (xssfRow.getCell(scenario).getStringCellValue().equals(scenarioName)) {
                if (xssfRow.getCell(iteration).getNumericCellValue() == this.iteration) {
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
                    // map.clear();
                }
                //}
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

}
