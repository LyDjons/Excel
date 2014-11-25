package com.disp.disp.control.saveExcell;

import com.disp.disp.control.loadExcell.Report;
import com.disp.disp.control.loadExcell.TransportAction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

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
    private ArrayList<Pinter> pintersList;



    public String getDepartment() {
        return department;
    }

    public ArrayList<TransportAction> getTransportAction(Report report){
        return report.getTransportActions();
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
        return (Date)start.clone();
    }

    public void setStart(Date start) {
        this.start = (Date)start.clone();
    }

    public Date getEnd() {
        return (Date)end.clone();
    }

    public void setEnd(Date end) {
        this.end = (Date)end.clone();
    }

    public TransportExcell(Report report) {
        department= get_list_departments_of_work();
        transport_mark =report.getTransport();
        gos = report.getInfo().getGos();
        type_of_work = get_type_of_work();
        fio  = report.getInfo().getDriver();
        if(getStartWork(report)==null){

            start=(Date)report.getTime_total().clone();
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
        }else
        {start = (Date)getStartWork(report).clone();}
        if(getEndWork(report)==null){

            end=(Date)report.getTime_stop().clone();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
        }else {
            end = (Date)getEndWork(report).clone();}
pintersList=getPainterListIntervalNumColumn(report.getTransportActions());

    }

          //получение начала движения
    private static Date getStartWork(Report report){
        for(TransportAction transportAction :report.getTransportActions()){
            if (transportAction.getStatus().equals("Движение")) return (Date)transportAction.getStart().clone();
        }
        return null;
    }
         //олучение окончания движения

    private static Date getEndWork(Report report){
        ArrayList<TransportAction> ta = report.getTransportActions();
        for(int j=ta.size()-1;j>-1;j--){
            if (ta.get(j).getStatus().equals("Движение")) {
                return (Date)ta.get(j).getEnd().clone();
                 }
        }
       return  null;
    }



        //получение типа культуры с поля
    private static String get_type_of_work(){
        return "Збирання кукурудзи_Тест";
    }
    //получение Названия отделения, где находился транспорт
    private static String get_list_departments_of_work(){
        return "Хмільниця_Тест";
    }
    public ArrayList<Pinter> getPainterListIntervalNumColumn(ArrayList<TransportAction> action){
        ArrayList<Pinter> painterarray = new ArrayList<Pinter>();

        for(TransportAction transportAction:action){

            if(transportAction.getStart().getHours()<7) continue;
if(transportAction.getStatus().contains("Стоянка") && transportAction.getInterval().getMinutes()<15 &&
transportAction.getInterval().getHours()<0) continue;


            int start =get_num_cell(transportAction.getStart());
            int end = get_num_cell(transportAction.getEnd());
            if(start==end) continue;
            if(transportAction.getStatus().contains("Стоянка") && (transportAction.getInterval().getHours()>0 ||
            transportAction.getInterval().getMinutes()>14)){
                painterarray.add(new Pinter(start,end,new Color(255,255,0)));
            }
            if(transportAction.getStatus().contains("Движение")){
                painterarray.add(new Pinter(start,end,new Color(0,176,80)));
            }

        }

        return painterarray;
    }

    public int getFirstIndexWorkGreen(ArrayList<Pinter> list){
        for(Pinter p : list){
            if(p.getColor().equals(new Color(0,176,80))) return p.getStart();
        }
        return 5;
    }

    public int getLastIndexWorkGreen(ArrayList<Pinter> list){
        int index =5;
        for(Pinter p : list){
            if(p.getColor().equals(new Color(0,176,80))) index=p.getEnd();
        }
        return index;
    }

    //округление даты (времени) до 15 мин
    private static Date data_rounding(Date date){
        int switchVariable = 0;
        double minute = date.getMinutes();
        if(date.getSeconds()>=30) minute+=1;
        if( minute>=0 && minute <7.5) switchVariable = 5;
        else if( minute>=7.5 && minute <=15) switchVariable = 10;
        else if( minute>15 && minute <22.5) switchVariable = 20;
        else if( minute>=22.5 && minute <=30) switchVariable = 25;
        else if( minute>30 && minute <37.5) switchVariable = 35;
        else if( minute>=37.5 && minute <=45) switchVariable = 40;
        else if( minute>45 && minute <52.5) switchVariable = 50;
        else if( minute>52.5 && minute <60) switchVariable = 55;
        switch (switchVariable)
        {
            case 5: date.setMinutes(0);date.setSeconds(0); break;
            case 10: date.setMinutes(15);date.setSeconds(0); break;
            case 20: date.setMinutes(15);date.setSeconds(0); break;
            case 25: date.setMinutes(30);date.setSeconds(0); break;
            case 35: date.setMinutes(30);date.setSeconds(0); break;
            case 40: date.setMinutes(45);date.setSeconds(0); break;
            case 50: date.setMinutes(45);date.setSeconds(0); break;
            case 55: date.setHours(date.getHours()+1);date.setMinutes(0);date.setSeconds(0); break;
            default: date.setMinutes(0);date.setSeconds(0); break;
        }
        return (Date)date.clone();
    }

    private static int  get_num_cell(Date date){
        date = (Date)data_rounding(date).clone();
        Date countdata = new Date();
        countdata.setHours(7);
        countdata.setMinutes(0);
        countdata.setSeconds(0);
        int cell=5;
        for(int i =0;i<101;i++){
            //    System.out.println("data = " + date + "     count  " + countdata + "   cell=" + cell);
            if(date.getHours()==countdata.getHours()&& date.getMinutes()==countdata.getMinutes()) {
                // System.out.println("result=" + cell);
                return cell;
            } countdata.setMinutes(countdata.getMinutes()+15);
            cell++;
        }
        return -1;
    }

    public ArrayList<Pinter> getPintersList() {
        return pintersList;
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
