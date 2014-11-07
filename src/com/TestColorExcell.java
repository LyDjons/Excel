package com;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by disp.chimc on 22.10.14.
 */
public class TestColorExcell {
    //округление времени в нужную сторону
    private static Date data_rounding(Date date){
        int hour = date.getHours();
        int minute = date.getMinutes();
        if (minute>=45) {
            date.setHours(++hour);
            date.setMinutes(0);
        } else
        if(minute<45 && minute>=30){
            date.setMinutes(30);
        }else
        if(minute>=15 && minute<30){
            date.setMinutes(30);
        }else
        if(minute<15){
            date.setMinutes(0);
        }
        return date;
    }

    public static void main(String []args) throws Exception {
        FileInputStream inputStream = new FileInputStream("D:/MyInfo/java/TestExcell/comb.xlsx");
        XSSFWorkbook workbook  = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheet("Countries");
        int rowIndex = 0;
        Row row = sheet.getRow(rowIndex + 1);
        Cell cell0 = row.getCell(0);


        XSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(0,176,80)));
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell0.setCellStyle(style1);

        FileOutputStream fos = new FileOutputStream("D:/MyInfo/java/TestExcell/comb.xlsx");
        workbook.write(fos);
        fos.close();
        System.out.println(" written successfully");
    }

}
