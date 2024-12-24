package com.shop.web.db.service;

import com.shop.web.db.entity.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelToObjectService {
    public List<Product> parseExcelFile(InputStream inputStream) {
        List<Product> productList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            Iterator<Row> rows = sheet.iterator();

            // Skip the header row
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Product product = new Product();

                product.setId(currentRow.getCell(0).getStringCellValue());
                product.setProductCode(currentRow.getCell(1).getStringCellValue());
                product.setName(currentRow.getCell(2).getStringCellValue());
                product.setCategory(currentRow.getCell(3).getStringCellValue());

                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
