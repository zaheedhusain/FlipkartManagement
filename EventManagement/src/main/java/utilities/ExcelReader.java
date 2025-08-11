package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private Sheet sheet;

    public ExcelReader(String filePath, String sheetName) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getRowData(int rowIndex) {
        Map<String, String> data = new HashMap<>();
        Row headerRow = sheet.getRow(0);
        Row row = sheet.getRow(rowIndex);

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String key = headerRow.getCell(i).getStringCellValue();
            Cell cell = row.getCell(i);
            String value = "";

            if (cell != null) {
                cell.setCellType(CellType.STRING);
                value = cell.getStringCellValue();
            }

            data.put(key, value);
        }

        return data;
    }
}
