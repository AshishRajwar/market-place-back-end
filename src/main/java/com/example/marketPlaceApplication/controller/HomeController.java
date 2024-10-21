package com.example.marketPlaceApplication.controller;

import com.example.marketPlaceApplication.models.Item;
import com.example.marketPlaceApplication.services.HomeService;
import com.example.marketPlaceApplication.utility.ExcelDataImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private ExcelDataImport excelDataImport;

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<Item>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        return excelDataImport.importExcelFileUtility(files);
    }

    @GetMapping("home")
    public List<Item> getAllItems() {
        return homeService.getAllItems();
    }
}
