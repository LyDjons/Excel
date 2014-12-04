package com.disp.disp.control;

import com.config.Config;
import com.disp.disp.control.loadExcell.ExcellLoader;
import com.disp.disp.control.loadExcell.Report;
import com.disp.Disp;
import com.disp.disp.control.saveExcell.SaveExclell;
import com.disp.disp.control.saveExcell.TransportExcell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by disp.chimc on 28.10.14.
 */
public class DispControl implements Disp {
    private ArrayList<Report> reports;
    private ArrayList<Config> configs;
    @Override
    public ArrayList<Report> load_report(String path) {
        ExcellLoader excellLoader = new ExcellLoader();
        try {
            excellLoader.loadExcell(path);
            ArrayList<Report> report =excellLoader.getReports();
         reports=report;
            return report;
        } catch (IOException e) {
            System.out.println("You have a problem with file: " + path);
            e.printStackTrace();
            return null;
        }

    }

    public void loadReport(String path) {
        ExcellLoader excellLoader = new ExcellLoader();
        try {
            excellLoader.loadExcell(path);
            ArrayList<Report> report =excellLoader.getReports();

            reports=report;
        } catch (IOException e) {
            System.out.println("You have a problem with file: " + path);
            e.printStackTrace();
        }

    }

    public void save_report(ArrayList<Report> reports,String path,ArrayList<Config>configs)  {
        //получаем имя листа(дата)
        Date d = (Date)reports.get(0).getTime_stop().clone();
        String list_name = ""+d.getDate()+"."+(d.getMonth()+1);

        System.out.println(list_name);
        ArrayList<TransportExcell> transportExcell = new ArrayList<TransportExcell>();
        for(Report re: reports){
            transportExcell.add(new TransportExcell(re,configs));


        }

        try{
            SaveExclell saveExclell = new SaveExclell(path,list_name);
            saveExclell.createHatList();
            saveExclell.create(transportExcell);
            saveExclell.update();
        }catch(Exception e){
            System.out.println("Error! Can not write report to excell");
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Config> load_config(String path) throws IOException {
        ArrayList<Config> configList = new ArrayList<Config>();
        FileInputStream inputStream = new FileInputStream(path);

        XSSFWorkbook workbook  = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("config");
        int row_total = sheet.getLastRowNum();

        for(int i = 1; i < row_total; i++){
            String tracker = sheet.getRow(i).getCell(0).toString();

            String num = sheet.getRow(i).getCell(7).toString();
            try{
                tracker=tracker.substring(0,tracker.indexOf("."));
                 num =num.substring(0,num.indexOf("."));
            }catch (Exception e){}
            configList.add(new Config(
                 tracker,
                    sheet.getRow(i).getCell(1).toString(),
                    sheet.getRow(i).getCell(2).toString(),
                    sheet.getRow(i).getCell(3).toString(),
                    sheet.getRow(i).getCell(4).toString(),
                    sheet.getRow(i).getCell(5).toString(),
                    sheet.getRow(i).getCell(6).toString(),
                    num
            ));
        }
        configs =configList;
        return configList;
    }

    @Override
    public ArrayList<Report> getReport() {
        return reports;
    }
    @Override
    public ArrayList<Config> getConfigs() {
        return configs;
    }
}
