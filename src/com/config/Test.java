package com.config;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/**
 * Created by disp.chimc on 26.11.14.
 */
public class Test {


    public static void main(String []s) throws IOException {




File f = new File("config/config.xlsx");
        Desktop desktop = null;

        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            desktop.open(f);


        } catch (Exception e) {
            System.out.println("&&");
        }
    }


}
