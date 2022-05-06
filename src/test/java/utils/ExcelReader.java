package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    static Workbook book;
    static Sheet sheet;

    /**
     * this method will open the xlsx file
     * @param filePath
     */
    public static void openExcel(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the sheet
     * @param sheetName
     */
    public static void getSheet(String sheetName){
        sheet = book.getSheet(sheetName);
    }

    /**
     * this method gets the total number of rows
     * @return
     */
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * this method will get the number of columnns
     * @param rowIndex
     * @return
     */
    public static int getColumnCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    /**
     * this method gets the data from a cell in String format
     * @param rowIndex
     * @param colIndex
     * @return
     */
    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    /**
     * this method stores the cell data into a map
     * @param filePath
     * @param sheetName
     * @return
     */
    public static List<Map<String, String>> excelIntoMap(String filePath, String sheetName){
        openExcel(filePath);
        getSheet(sheetName);
        List<Map<String, String>> listData = new ArrayList<>();

        //this accoutns for headers
        for(int row = 1; row<getRowCount();row++){
            Map<String,String> map = new LinkedHashMap<>();
            for(int col=0; col<getColumnCount(row);col++){
                //cell map, headers are keys in the 0index row
                map.put(getCellData(0,col),getCellData(row,col));
            }
            listData.add(map);
        }
        return listData;
    }

}
