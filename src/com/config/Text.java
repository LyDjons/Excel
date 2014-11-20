package com.config;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by disp.chimc on 27.10.14.
 */
public class Text {
    private static Date data_rounding(Date date){
        int switchVariable = 0;
        double minute  = date.getMinutes();
        if(date.getSeconds()>=30) minute+=1;
        if( minute>=0 && minute <7.5) switchVariable = 5;
        else if( minute>=7.5 && minute <=15) switchVariable = 10;
        else if( minute>15 && minute <22.5) switchVariable = 20;
        else if( minute>=22.5 && minute <=30) switchVariable = 25;
        else if( minute>30 && minute <37.5) switchVariable = 35;
        else if( minute>=37.5 && minute <=45) switchVariable = 40;
        else if( minute>45 && minute <52.5) switchVariable = 50;
        else if( minute>52.5 && minute <=60) switchVariable = 55;

        switch (switchVariable)
        {
            case 5: date.setMinutes(0);date.setSeconds(0); break;
            case 10: date.setMinutes(15);date.setSeconds(0); break;
            case 20: date.setMinutes(15);date.setSeconds(0); break;
            case 25: date.setMinutes(30);date.setSeconds(0); break;
            case 35: date.setMinutes(30);date.setSeconds(0); break;
            case 40: date.setMinutes(45);date.setSeconds(0); break;
            case 50: date.setMinutes(45);date.setSeconds(0); break;
            case 55: date.setHours(17);date.setMinutes(0);date.setSeconds(0); break;
            default: date.setMinutes(0);date.setSeconds(0); break;
        }

        return date;
    }
    public static void main(String args[]){
      /*  try {
            ArrayList<Transport> tr= XmlConverter.unmarshalling("D:/MyInfo/java/TestExcell/config/config.xml");
           for (Transport t:tr){
               System.out.println(t.toString());
           }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }*/
Date date = new Date();


        date.setHours(16);date.setMinutes(59);date.setSeconds(32);

   System.out.print(data_rounding(date));
}



}
