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
        FileOutputStream fos = new FileOutputStream(path);

        fos.close();
        System.out.println(" Written successfully matherfacker!!!");
    }
}
