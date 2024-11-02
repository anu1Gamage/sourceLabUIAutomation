package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class excelUtility {
    private String filePath;
    private static Workbook workbook;

    //Constructor to initialize file path
    public excelUtility(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            this.workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to read data from a specific cell
    public static String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        return cell.toString(); // Returns cell value as String
    }

    // Method to write data to a specific cell
    public void setCellData(String sheetName, int rowNum, int colNum, String data) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
        }
        Cell cell = row.createCell(colNum);
        cell.setCellValue(data);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath))) {
            workbook.write(fileOutputStream); // Write changes to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to get Total Row count
    public static int getTotalRowCount(String sheetName) throws IOException {
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        return rowCount;
    }

    //Method to get Total Row count
    public static int getTotalColumnsCount(String sheetName, int rowNum) throws IOException {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        int colCount = row.getLastCellNum();
        workbook.close();
        return colCount;
    }

    // Method to close the workbook
    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
