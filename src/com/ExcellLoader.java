package com;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by disp.chimc on 20.10.14.
 */
public class ExcellLoader {
    private  ArrayList<Report> reports = new ArrayList<Report>();

    //мтод конвертирует информацию о транспорте в объект Report
    private  Report convertStringtoTransport(String str){
        str= str.substring(23,str.length()-9);
        int tracker = Integer.parseInt(str.substring(0, 3));
        String transport =str.substring(5, str.length() - 2) ;
        return new Report(tracker,transport);
    }

    //метод загружает данные в список с Excell отчета
    public void loadExcell(String file_path) throws IOException{
        XSSFCell cell = null;
        Row row = null;
        FileInputStream inputStream = new FileInputStream(file_path);
        XSSFWorkbook workbook  = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int row_total = sheet.getLastRowNum();
        String pointer ;
        Report unitreport;
        for(int i= 4;i<row_total;i++){
            row = sheet.getRow(i);
            cell= (XSSFCell) row.getCell(0);

            //читает с файла треккер, название комбайна, и конвертирует в Report объект
            try{
                pointer = cell.toString();

                if(pointer.contains("Транспортное средство")==true){
                    unitreport = convertStringtoTransport(pointer);
                    i=i+3;
                    for(;i<row_total;i++){
                        row =sheet.getRow(i);
                        cell =(XSSFCell) row.getCell(0);
                        if(i+1>row_total||(((XSSFCell) (sheet.getRow(i+1)).getCell(0)).toString()).contains("Транспортное средство")==true){
                            break;
                        }

                        TransportAction transportAction = new TransportAction(
                                String.valueOf(row.getCell(1)),
                                String.valueOf(row.getCell(2)),
                                String.valueOf(row.getCell(4)),
                                String.valueOf(row.getCell(5)),
                                String.valueOf(row.getCell(7)),
                                String.valueOf(row.getCell(8)));

                        unitreport.addTransportAction(transportAction);

                    }
                    reports.add(unitreport);

                }
            }catch (Exception e){
                System.out.println("Ошибка чтения с файла треккера, комбайна, и и конвертирования!");
                e.printStackTrace();
            }

        }
    }


    public  ArrayList<Report> getReports() {
        return reports;
    }
}
