package com.config;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by disp.chimc on 27.10.14.
 */
public class Text {
    private static Date data_rounding(Date date){
        int hour = date.getHours();
        int minute = date.getMinutes();
        if (minute>=45) {
            date.setHours(++hour);
            date.setMinutes(0);
        } else
        if(minute<45 && minute>=30){
            date.setMinutes(30);
        }else
        if(minute>=15 && minute<30){
            date.setMinutes(30);
        }else
        if(minute<15){
            date.setMinutes(0);
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
       Date d = new Date();
        d.setMinutes(d.getMinutes()+30);
        System.out.print(data_rounding(d));

    }
}
