package com;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.apache.poi.ss.util.CellRangeAddress.*;

/**
 * Created by disp.chimc on 28.10.14.
 */
public class TestSave {
    //устанавливам размер колонок
    private static FileOutputStream fos;
    private static FileInputStream inputStream;
    private static XSSFWorkbook workbook;
    private static Sheet sheet;
    public static void setSizeColumn(Sheet sheet){
        sheet.setColumnWidth(0,3766);
        sheet.setColumnWidth(1,6107);
        sheet.setColumnWidth(2,2925);
        sheet.setColumnWidth(3,6619);
        sheet.setColumnWidth(4,5668);
        for(int i = 5 ; i < 53;i++)
            sheet.setColumnWidth(i,841);
    }

    //делаем копию файла
    public static void copy(File source, File dest) throws IOException {
        try {
            Files.copy(source.toPath(), dest.toPath());

        } catch (IOException e) {
            System.out.println("Can not copy file! File  deleted!!!") ;
            dest.delete();
            throw new IOException();
        }
    }
    public static void main(String args[]) throws IOException {

String path = "D:/MyInfo/java/TestExcell/sourse - копия.xlsx";

        inputStream = new FileInputStream(path);
        workbook  = new XSSFWorkbook(inputStream);
        fos = new FileOutputStream(path);

         sheet = workbook.createSheet("test1");
      // Sheet  sheet = workbook.getSheet("test1");
        setSizeColumn(sheet);

         sheet.createRow(1).createCell(0).setCellValue("Робота комбайнів");

        XSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(CellStyle.ALIGN_CENTER);  //выровняли по центру
            style.setFillForegroundColor(new XSSFColor(new Color(255,255,0))); //цвет ячейки
            style.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
            sheet.getRow(1).getCell(0).setCellStyle(style);  //применить стиль
        //выделяем и объединяем Ячейки
        CellRangeAddress region = new CellRangeAddress(1, 1, 0, 1);//(firstRow,lastRow,firstCol,lastCol)

        sheet.addMergedRegion(region);

    //Проворачивает дела с шапкой
        Row cap_row = workbook.getSheet(sheet.getSheetName()).createRow(2);

        int count =7;
        XSSFCellStyle style2 = workbook.createCellStyle();

       for(int i = 5; i < 53; i=i+2){
           if(count==25) count=1;
           cap_row.createCell(i).setCellValue(count++);
          region = new CellRangeAddress(2, 2, i, i+1);//(firstRow,lastRow,firstCol,lastCol)
           RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
           RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
           RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
           RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
           sheet.addMergedRegion(region);
           style2.setAlignment(CellStyle.ALIGN_CENTER);  //выровняли по центру
           style2.setFillForegroundColor(new XSSFColor(new Color(191,191,191))); //цвет ячейки
           style2.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
           sheet.getRow(2).getCell(i).setCellStyle(style2);  //применить стиль
       }

        cap_row.createCell(0).setCellValue("Відділення");
        cap_row.createCell(1).setCellValue("Марка ТЗ");
        cap_row.createCell(2).setCellValue("Держ. №");
        cap_row.createCell(3).setCellValue("Вид робіт");
        cap_row.createCell(4).setCellValue("ПІБ");
        for(int i=0;i<5;i++) sheet.getRow(2).getCell(i).setCellStyle(style2);
        for(int i=0; i<6;i++){
            CellStyle cellStyle = sheet.getRow(2).getCell(i).getCellStyle();
            cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        }



         workbook.write(fos);


    }
}
