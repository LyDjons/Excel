package com;


import com.config.Config;
import com.disp.disp.control.loadExcell.Report;
import com.disp.disp.control.saveExcell.SaveExclell;
import com.disp.disp.control.saveExcell.TransportExcell;
import com.disp.Disp;
import com.disp.disp.control.DispControl;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by disp.chimc on 28.10.14.
 */
public class MainClass {
    public static void main(String arg[]){

        Disp disp = new DispControl();

             ArrayList<Report> report =disp.load_report("Document.xlsx");
        ArrayList<Config> configs;
        try {
          configs = disp.load_config("config/config.xlsx");


        } catch (IOException e) {
            configs = null;
        }

       disp.save_report(report,"sourse - копия.xlsx",configs);


    }


}
