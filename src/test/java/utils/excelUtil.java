package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class excelUtil {

    public Map readExcel(String testcaseName) throws IOException {

        Map<String, Object> excelData = new HashMap<String, Object>();;

        try {
            String excelFilePath = "/Users/jitendra/Downloads/restAssured/src/test/java/testData/testExcel.xlsx";
            File file = new File(excelFilePath);

            if (file.exists() && !file.isDirectory()) {
                FileInputStream fis = new FileInputStream(file);

                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workbook.getSheet("testData");

                Row headerRow = sheet.getRow(0);
                Cell headerCell = headerRow.getCell(0);

                int lastCol = headerRow.getLastCellNum();
                int lastRow = sheet.getLastRowNum();

                for (int i = 0; i < lastRow; i++) {
                    Row tempRow = sheet.getRow(i);
                    Cell tempCell = tempRow.getCell(0);
                    if (tempCell.getStringCellValue().equals(testcaseName)) {
                        for (int j = 1; j < lastCol; j++) {
                            headerCell = headerRow.getCell(j);
                            tempCell = tempRow.getCell(j);

                            switch (tempCell.getCellType()){
                                case STRING:
                                    excelData.put(headerCell.getStringCellValue(), tempCell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    excelData.put(headerCell.getStringCellValue(), Integer.valueOf((int)tempCell.getNumericCellValue()));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
//        System.out.println(excelData);
        return excelData;
    }

    public void writeExcel(String testcaseName, String key, String value) {
        try {
            String excelFilePath = "/Users/jitendra/Downloads/restAssured/src/test/java/testData/testExcel.xlsx";
            File file = new File(excelFilePath);

            if (file.exists() && !file.isDirectory()) {
                FileInputStream fis = new FileInputStream(file);

                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workbook.getSheet("testData");

                Row headerRow = sheet.getRow(0);
                Cell headerCell = headerRow.getCell(0);

                int lastCol = headerRow.getLastCellNum();
                int lastRow = sheet.getLastRowNum();
                for (int i = 0; i < lastRow; i++) {
                    Row tempRow = sheet.getRow(i);
                    Cell tempCell = tempRow.getCell(0);
                    if (tempCell.getStringCellValue().equals(testcaseName)) {
                        Cell r = tempRow.getCell(0);
                        r.getColumnIndex();

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map readData(String testcaseName) throws IOException {
        Map<String, Object> excelData = new HashMap<String, Object>();

        try {
            String excelFilePath = "/Users/jitendra/Downloads/restAssured/src/test/java/testData/testExcel.xlsx";
            File file = new File(excelFilePath);

            if (file.exists() && !file.isDirectory()) {
                FileInputStream fis = new FileInputStream(file);

                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workbook.getSheet("testData");

                // Iterate through each rows one by one
                Iterator<Row> rowIterator = sheet.iterator();
                Row headerRow = rowIterator.next();
                Iterator<Cell> cellItr = headerRow.cellIterator();


                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    // For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext() && cellItr.hasNext()) {
                        Cell cell = cellIterator.next();
                        // Check the cell type and format accordingly
                        Cell headerCell = cellItr.next();
                            switch (cell.getCellType()) {
                                case STRING:
                                    excelData.put(headerCell.getStringCellValue(), cell.getStringCellValue());

                                    break;
                                case NUMERIC:
                                    excelData.put(headerCell.getStringCellValue(), Integer.valueOf((int) cell.getNumericCellValue()));
                                    break;
                            }
                        }
                    }

            }

        } catch(Exception e){
                e.printStackTrace();
            }
        return excelData;

    }

    public static void main(String[] args) throws IOException {
        excelUtil eu = new excelUtil();
        System.out.println(eu.readExcel("TC002"));
        System.out.println(eu.readData("TC002"));
//

    }
}


