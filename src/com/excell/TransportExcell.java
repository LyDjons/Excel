package com.excell;

import com.Report;
import com.TransportAction;
import com.config.Transport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by disp.chimc on 31.10.14.
 */
public class TransportExcell {

    private String department;
    private String transport_mark;
    private String gos;
    private String type_of_work;
    private String fio;
    private Date start;
    private Date end;
    private ArrayList<Interval> intervals;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTransport_mark() {
        return transport_mark;
    }

    public void setTransport_mark(String transport_mark) {
        this.transport_mark = transport_mark;
    }

    public String getGos() {
        return gos;
    }

    public void setGos(String gos) {
        this.gos = gos;
    }

    public String getType_of_work() {
        return type_of_work;
    }

    public void setType_of_work(String type_of_work) {
        this.type_of_work = type_of_work;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ArrayList<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(ArrayList<Interval> intervals) {
        this.intervals = intervals;
    }

    public TransportExcell(Report report,int minute) {
         department= get_list_departments_of_work();
         transport_mark =report.getTransport();
         gos = report.getInfo().getGos();
         type_of_work = get_type_of_work();
         fio  = report.getInfo().getDriver();

        if(getStartWork(report)==null){
            start=report.getTime_total();
           /* start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);*/
        }else
        {start = getStartWork(report);}
        if(getEndWork(report)==null){
           end=report.getTime_total();
            /*end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);*/
        }else {
         end = getEndWork(report);}

         intervals=getStopIntervals(report,minute);

    }

    public TransportExcell(Report report) {
        department= get_list_departments_of_work();
        transport_mark =report.getTransport();
        gos = report.getInfo().getGos();
        type_of_work = get_type_of_work();
        fio  = report.getInfo().getDriver();
        if(getStartWork(report)==null){
            start=report.getTime_total();
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
        }else
        {start = getStartWork(report);}
        if(getEndWork(report)==null){
            end=report.getTime_stop();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
        }else {
            end = getEndWork(report);}

        intervals=getStopIntervals(report,30);
    }

          //получение начала движения
    private static Date getStartWork(Report report){
        for(TransportAction transportAction :report.getTransportActions()){
            if (transportAction.getStatus().equals("Движение")) return transportAction.getStart();
        }

        return null;
    }
         //олучение окончания движения

    private static Date getEndWork(Report report){
        ArrayList<TransportAction> ta = report.getTransportActions();
        for(int j=ta.size()-1;j>-1;j--){
            if (ta.get(j).getStatus().equals("Движение")) {
                return ta.get(j).getEnd();
                 }
        }
       return  null;
    }

        // озвращает список остановок, которые больше чем stop_minute
        //ВАЖНО!!!!   только для транспорта который работает ДНЕМ
    private static ArrayList<Interval> getStopIntervals(Report report,int stop_minute){
        ArrayList<Interval> tekintervals = new ArrayList<Interval>();
        boolean flag = false;
        for(TransportAction ta: report.getTransportActions()){
            // если стоянка первая то не добавляем в лист, так как не внутри движения
         //   if(ta.getStatus().equals("Стоянка") && !flag ) {flag=true;continue;}
            if(ta.getStatus().equals("Стоянка") ){
                int minute = ta.getInterval().getMinutes();
                if (ta.getInterval().getSeconds()>30) minute++;
                if(ta.getInterval().getHours()>0) minute = minute + 60*ta.getInterval().getHours();
                if(minute<stop_minute) continue;
                    tekintervals.add(new Interval(ta.getStart(),ta.getEnd(),minute));
                flag=true;
            }
        }
if (tekintervals.size()==0) {
   System.out.println("+++++++++++++++++++++++++++++++++++"+tekintervals.size()+"++++++++++");
    return  null;
}
        return tekintervals;
    }

        //получение типа культуры с поля
    private static String get_type_of_work(){
        return "Збирання кукурудзи_Тест";
    }
    //получение Названия отделения, где находился транспорт
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
                '}';
    }



}
