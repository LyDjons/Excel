package com.config;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by disp.chimc on 27.10.14.
 */
public class Text {

    public static void main(String args[]){
    Date a = new Date();
        System.out.println(a);
    Date b =(Date)a.clone();
        b.setHours(11);
        System.out.println(a);
}



}
