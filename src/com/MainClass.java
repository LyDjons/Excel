package com;

import com.worck.Disp;
import com.worck.DispControl;

import java.util.ArrayList;

/**
 * Created by disp.chimc on 28.10.14.
 */
public class MainClass {
    public static void main(String arg[]){

        Disp disp = new DispControl();

        ArrayList<Report> report =disp.load_report("D:/MyInfo/java/Excell/Document.xlsx");
        for(Report re: report){
            System.out.println(re.toString());
        }

    }
}
