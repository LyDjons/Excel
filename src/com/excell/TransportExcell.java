package com.excell;

import com.Report;
import com.TransportAction;
import com.config.Transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by disp.chimc on 31.10.14.
 */
public class TransportExcell {

    private String department;
    private String transport_mark;
   public String gos;
    private String type_of_work;
    private String fio;

    private Date start;
    private Date end;
   private ArrayList<Interval> intervals;

    public TransportExcell(Report report) {
       department= get_list_departments_of_work();
       transport_mark =report.getTransport();
       gos = report.getInfo().getGos();
       type_of_work = get_type_of_work();
       fio  = report.getInfo().getDriver();

    }
    private static String get_type_of_work(){
        return "Збирання кукурудзи_Тест";
    }
    private static String get_list_departments_of_work(){
        return "Хмільниця_Тест";
    }

    @Override
    public String toString() {
        return "TransportExcell{" +
                "department='" + department + '\'' +
                ", transport_mark='" + transport_mark + '\'' +
                ", gos='" + gos + '\'' +
                ", type_of_work='" + type_of_work + '\'' +
                ", fio='" + fio + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", intervals=" + intervals +
                '}';
    }

    class Interval{
    Date start_interval;
    Date end_interval;
}

}
