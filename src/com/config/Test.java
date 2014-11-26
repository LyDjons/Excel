package com.config;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by disp.chimc on 26.11.14.
 */
public class Test {
   public  ArrayList<Config> get_config(String path) throws IOException {
        ArrayList<Config> configList = new ArrayList<Config>();
        FileInputStream inputStream = new FileInputStream(path);

        XSSFWorkbook workbook  = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("config");
        int row_total = sheet.getLastRowNum();

       for(int i = 1; i < row_total; i++){

               configList.add(new Config(
                       sheet.getRow(i).getCell(0).toString(),
                       sheet.getRow(i).getCell(1).toString(),
                       sheet.getRow(i).getCell(2).toString(),
                       sheet.getRow(i).getCell(3).toString(),
                       sheet.getRow(i).getCell(4).toString(),
                       sheet.getRow(i).getCell(5).toString(),
                       sheet.getRow(i).getCell(6).toString(),
                       sheet.getRow(i).getCell(7).toString()
                ));


       }

       return configList;
    }

    public static void main(String []s) throws IOException {
       Test t = new Test();
        ArrayList<Config> configs = t.get_config("config/config.xlsx");
     for(Config c: configs){
         System.out.println(c.toString());
     }


    }
}
