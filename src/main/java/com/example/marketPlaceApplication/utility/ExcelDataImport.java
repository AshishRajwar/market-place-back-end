package com.example.marketPlaceApplication.utility;

import com.example.marketPlaceApplication.models.Item;
import com.example.marketPlaceApplication.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Component
public class ExcelDataImport {
    @Autowired
    private ItemRepository itemRepository;
    public ResponseEntity<List<Item>> importExcelFileUtility (MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Item> itemList=new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Item item = new Item();
                XSSFRow row = worksheet.getRow(index);
                item.setName(row.getCell(0).getStringCellValue());
                item.setPrice((int) row.getCell(1).getNumericCellValue());
                item.setDescription(row.getCell(2).getStringCellValue());
                itemList.add(item);
            }
        }
        itemRepository.saveAll(itemList);
        return new ResponseEntity<>(itemList, status) ;
    }
}
