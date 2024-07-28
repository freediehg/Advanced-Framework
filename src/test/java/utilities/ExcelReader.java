package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static XSSFWorkbook workbook;
    public static FileInputStream file;
    public static XSSFSheet sheet;
    public static Map<String, String> dataMap = new HashMap<String, String>();

    /**
     * =============================================================================
     * Method: setMapData | Description: This method is used for loading the Excel file
     * and add data into map properties file | Parameters : filePath,sheetName, testcase Name |
     * Return: dataMap
     * =============================================================================
     */
    public static Map<String, String> setMapData(String excelFilePath, String sheetName, String tcName) throws Exception {
        dataMap.clear();
        int lastRow = ExcelReader.excelLoad(excelFilePath, sheetName);
        for (int i = 0; i <= lastRow; i++) {
            boolean b = false;
            DataFormatter formatter = new DataFormatter();
            String rowTC = formatter.formatCellValue(ExcelReader.sheet.getRow(i).getCell(0));
            if (rowTC.equalsIgnoreCase(tcName)) {
                int last_CellNumber = sheet.getRow(i).getLastCellNum() - 1;
                for (int j = 1; j <= last_CellNumber; j++) {
                    String key = formatter.formatCellValue(ExcelReader.sheet.getRow(0).getCell(j));
                    String value = formatter.formatCellValue(ExcelReader.sheet.getRow(i).getCell(j));
                    dataMap.put(key, value);
                }
                b = true;
                break;
            }
            if (b) {
                break;
            }
        }
        return dataMap;
    }

    /**
     * =============================================================================
     * Method: excelLoad | Description: This method is used for loading the Excel file
     * and checking rows count properties file | Parameters : filePath, sheetName |
     * Return: rows count
     * =============================================================================
     */
    public static int excelLoad(String excelFilePath, String sheetName) throws Exception {
        try {
            file = new FileInputStream(new File(excelFilePath));
            workbook = new XSSFWorkbook(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheet(sheetName);
        int rowNo = sheet.getLastRowNum();
        return rowNo;
    }

}
