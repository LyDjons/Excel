package com.excell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by disp.chimc on 31.10.14.
 */
public class SaveExclell {
    private  FileOutputStream fos;
    private  FileInputStream inputStream;
    private  XSSFWorkbook workbook;
    private  Sheet sheet;
    //метод возвращает ячейку, в которой соответствует текущее время
    private static int  get_num_cell(Date date){
        Date countdata = new Date();
            countdata.setHours(7);
            countdata.setMinutes(0);
            countdata.setSeconds(0);
        int cell=5;
        for(int i =0;i<53;i++){
            if(date.getHours()==countdata.getHours()&& date.getMinutes()==countdata.getMinutes()) {

                return cell+1;
            } countdata.setMinutes(countdata.getMinutes()+30);
            cell++;
        }
        return -1;
          }

    //установка размеров колонок
    private  void setSizeColumn(Sheet sheet){
        sheet.setColumnWidth(0,3766);
        sheet.setColumnWidth(1,6107);
        sheet.setColumnWidth(2,2925);
        sheet.setColumnWidth(3,6619);
        sheet.setColumnWidth(4,5668);
        for(int i = 5 ; i < 101;i++)
            sheet.setColumnWidth(i,420);
    }
    //зарисовать ячейку
    private  void driwing_cell(int row, int cell,Color color){
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(new XSSFColor(color)); //цвет ячейки
            style.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
            sheet.getRow(row).getCell(cell).setCellStyle(style); //применить стиль
    }

    //округление даты (времени) до 30 мин
    private static Date data_rounding(Date date){
        int switchVariable = 0;
        double minute  = date.getMinutes();
        if(date.getSeconds()>=30) minute+=1;
        if( minute>=0 && minute <7.5) switchVariable = 5;
        else if( minute>=7.5 && minute <=15) switchVariable = 10;
        else if( minute>15 && minute <22.5) switchVariable = 20;
        else if( minute>=22.5 && minute <=30) switchVariable = 25;
        else if( minute>30 && minute <37.5) switchVariable = 35;
        else if( minute>=37.5 && minute <=45) switchVariable = 40;
        else if( minute>45 && minute <52.5) switchVariable = 50;
        else if( minute>52.5 && minute <=60) switchVariable = 55;

        switch (switchVariable)
        {
            case 5: date.setMinutes(0);date.setSeconds(0); break;
            case 10: date.setMinutes(15);date.setSeconds(0); break;
            case 20: date.setMinutes(15);date.setSeconds(0); break;
            case 25: date.setMinutes(30);date.setSeconds(0); break;
            case 35: date.setMinutes(30);date.setSeconds(0); break;
            case 40: date.setMinutes(45);date.setSeconds(0); break;
            case 50: date.setMinutes(45);date.setSeconds(0); break;
            case 55: date.setHours(17);date.setMinutes(0);date.setSeconds(0); break;
            default: date.setMinutes(0);date.setSeconds(0); break;
        }

        return date;
    }

    //конструктор класса
    public SaveExclell(String path,String createlistname) {
        try {
            inputStream = new FileInputStream(path);
            workbook  = new XSSFWorkbook(inputStream);
            fos = new FileOutputStream(path);
            sheet = workbook.createSheet(createlistname);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file!!!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error SaveExcel Constructor");
            e.printStackTrace();
        }

    }

    //создание шапки листа
    public void createHatList() throws IOException {
//установка размера колонок
        setSizeColumn(sheet);
        sheet.createRow(1).createCell(0).setCellValue("Робота комбайнів");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER); //выровняли по центру
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(255,255,0))); //цвет ячейки
        style.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
        sheet.getRow(1).getCell(0).setCellStyle(style); //применить стиль
//выделяем и объединяем Ячейки
        CellRangeAddress region = new CellRangeAddress(1, 1, 0, 1);//(firstRow,lastRow,firstCol,lastCol)
        sheet.addMergedRegion(region);
//Проворачивает дела с шапкой
        Row cap_row = workbook.getSheet(sheet.getSheetName()).createRow(2);
        int count =7;
        XSSFCellStyle style2 = workbook.createCellStyle();
        for(int i = 5; i < 101; i=i+4){
            if(count==25) count=1;
            cap_row.createCell(i).setCellValue(count++);
            region = new CellRangeAddress(2, 2, i, i+3);//(firstRow,lastRow,firstCol,lastCol)
            RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            sheet.addMergedRegion(region);
            style2.setAlignment(CellStyle.ALIGN_CENTER); //выровняли по центру
            style2.setFillForegroundColor(new XSSFColor(new java.awt.Color(191,191,191))); //цвет ячейки
            style2.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
            sheet.getRow(2).getCell(i).setCellStyle(style2); //применить стиль
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
    }

    public void create(ArrayList<TransportExcell> transportlist) throws IOException {
        int row = 4;
        //заполняем  инф. таблицу
        for(TransportExcell tr: transportlist){
            sheet.createRow(row).createCell(1).setCellValue(tr.getTransport_mark());
            sheet.getRow(row).createCell(0).setCellValue(tr.getDepartment());
            sheet.getRow(row).createCell(2).setCellValue(tr.getGos());
            sheet.getRow(row).createCell(3).setCellValue(tr.getType_of_work());
            sheet.getRow(row).createCell(4).setCellValue(tr.getFio());
            row++;

        }

        //создаем сетку
        for(int r = 3;r<transportlist.size()+4;r++){
            for(int i = 0;i<101;i++){
            CellRangeAddress region = new CellRangeAddress(r, r, i, i);//(firstRow,lastRow,firstCol,lastCol)
                RegionUtil.setBorderTop(CellStyle.BORDER_THIN, region, sheet, workbook);
                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region, sheet, workbook);

                if (i<5){
                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }


        }
       /* try{
        int row1 =4;
        for(TransportExcell tr: transportlist){
            for(int i =get_num_cell(data_rounding(tr.getStart()));i<=get_num_cell(data_rounding(tr.getEnd()));i++ ){

                driwing_cell(row1,i,new Color(0,176,80));
            }
            row1++;
        }
    }catch (Exception e){System.out.print("Ошибка рисования отчета");}
*/
    }
    public void update() throws IOException {
        workbook.write(fos);
    }

    public static void main(String args[]) throws IOException {


    }

}
