package com;

import com.config.Transport;
import com.config.XmlConverter;
import com.excell.Pinter;
import com.excell.SaveExclell;
import com.excell.TransportExcell;
import com.worck.Disp;
import com.worck.DispControl;

import java.util.ArrayList;

/**
 * Created by disp.chimc on 28.10.14.
 */
public class MainClass {
    public static void main(String arg[]){

        Disp disp = new DispControl();



        ArrayList<Report> report =disp.load_report("Document.xlsx");


       ArrayList<TransportExcell> transportExcell = new ArrayList<TransportExcell>();
        for(Report re: report){
         transportExcell.add(new TransportExcell(re,30));

        }


    //отображение интервалов стоянки
        for(TransportExcell tr: transportExcell){
            System.out.println(tr.toString());
             for(Pinter p : tr.getPintersList()){
                 System.out.println(p.toString());
             }
        }


       try{
        SaveExclell saveExclell = new SaveExclell("sourse - копия.xlsx","test");
        saveExclell.createHatList();
       saveExclell.create(transportExcell);




        saveExclell.update();
       }catch(Exception e){
           System.out.println("WARNING! Proizoshel pirdec");
       }
        System.out.println("..well done!");


    }


}
