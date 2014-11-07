package com.config;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;

/**
 * Created by disp.chimc on 27.10.14.
 */
public class Text {
    public static void main(String args[]){
        try {
            ArrayList<Transport> tr= XmlConverter.unmarshalling("D:/MyInfo/java/TestExcell/config/config.xml");
           for (Transport t:tr){
               System.out.println(t.toString());
           }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
