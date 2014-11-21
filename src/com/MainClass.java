package com;


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
         transportExcell.add(new TransportExcell(re));


        }
/*for(Report re: report){
    System.out.print(re.toString());
    for(TransportAction ta: re.getTransportActions()){
        System.out.println("        "+ta.toString());
    }
}
    //отображение интервалов стоянки
     /*   for(TransportExcell tr: transportExcell){
            System.out.println(tr.toString());

        }
*/

       try{
        SaveExclell saveExclell = new SaveExclell("sourse - копия.xlsx","test");
             saveExclell.createHatList();
             saveExclell.create(transportExcell);
             saveExclell.update();
       }catch(Exception e){
           System.out.println("WARNING! Proizoshel pirdec");
       }



    }


}
