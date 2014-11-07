package com.worck;

import com.ExcellLoader;
import com.Report;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by disp.chimc on 28.10.14.
 */
public class DispControl implements Disp {
    @Override
    public ArrayList<Report> load_report(String path) {
        ExcellLoader excellLoader = new ExcellLoader();
        try {
            excellLoader.loadExcell(path);
            ArrayList<Report> reports =excellLoader.getReports();
            return reports;
        } catch (IOException e) {
            System.out.println("You have a problem with file: " + path);
            e.printStackTrace();
            return null;
        }


    }

    public void save_report(ArrayList<Report> reports,String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        XSSFWorkbook workbook  = new XSSFWorkbook(inputStream);
        FileOutputStream fos = new FileOutputStream(path);
        Sheet sheet;
        Row row;
        Cell cell=null;
            try{
               /*  sheet = workbook.createSheet(String.valueOf(new Date().getTime()));
                row =sheet.createRow(1);

                cell = row.createCell(1);
cell.setCellValue("blablabla");
                workbook.write(fos);*/
            }catch(IllegalArgumentException e){
                System.out.println("Can not create sheet !");
                workbook.close();
                fos.close();
                return;
                }



        /*
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
        */
        fos.close();
        System.out.println(" Written successfully matherfacker!!!");
    }
}
