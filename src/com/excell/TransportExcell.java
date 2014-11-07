package com.excell;

import com.Report;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by disp.chimc on 31.10.14.
 */
public class TransportExcell {

    private Set<String> department;
    private String transport_mark;
    private String gos;
    private String type_of_work;
    private String fio;

    private Date start;
    private Date end;
   private ArrayList<Interval> intervals;

    public TransportExcell(Report report) {
       //department
       transport_mark =report.getTransport();

    }

    class Interval{
    Date start_interval;
    Date end_interval;
}

}
