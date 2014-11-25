package com.disp.disp.control;

import com.disp.disp.control.loadExcell.ExcellLoader;
import com.disp.disp.control.loadExcell.Report;
import com.disp.Disp;
import com.disp.disp.control.saveExcell.SaveExclell;
import com.disp.disp.control.saveExcell.TransportExcell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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

    public void save_report(ArrayList<Report> reports,String path)  {
        //получаем имя листа(дата)
        Date d = (Date)reports.get(0).getTime_stop().clone();
        String list_name = ""+d.getDate()+"."+(d.getMonth()+1);

        System.out.println(list_name);
        ArrayList<TransportExcell> transportExcell = new ArrayList<TransportExcell>();
        for(Report re: reports){
            transportExcell.add(new TransportExcell(re));

        }

        try{
            SaveExclell saveExclell = new SaveExclell(path,list_name);
            saveExclell.createHatList();
            saveExclell.create(transportExcell);
            saveExclell.update();
        }catch(Exception e){
            System.out.println("Error! Can not write report to excell");
        }
    }
}
