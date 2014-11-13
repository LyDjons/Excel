package com;

import com.config.Transport;
import com.config.XmlConverter;
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



        ArrayList<Report> report =disp.load_report("D:/MyInfo/java/Excell/Document.xlsx");
        ArrayList<TransportExcell> transportExcell = new ArrayList<TransportExcell>();
        for(Report re: report){
         transportExcell.add(new TransportExcell(re,30));


        }
for(TransportExcell tr: transportExcell){
    System.out.println(tr.toString());
    for(int i = 0; i < tr.getIntervals().size();i++ )
        System.out.println("    "+tr.getIntervals().get(i).getStart_interval()+
                            "    "+tr.getIntervals().get(i).getEnd_interval()+
                              "    "+tr.getIntervals().get(i).getMinute());
}

    }

}
