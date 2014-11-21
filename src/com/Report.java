package com;

import com.config.Transport;
import com.config.XmlConverter;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by LyDjons on 10.10.14.
 */
public class Report {

    private int tracker;
    private String transport;
    private Double distance_total_km;
    private Date time_total;
    private Date time_stop;

    private Info info;
    private static ArrayList<Transport> mtsTransportXML;
    static {
        try {
           mtsTransportXML = XmlConverter.unmarshalling("config/config.xml");
        } catch (JAXBException e) {
            e.printStackTrace();

        }
    }
   private ArrayList<TransportAction> transportActions = new ArrayList<TransportAction>();

    public Report(int tracker, String transport,Double distance,Date time_work,Date time_stop) {
        this.tracker = tracker;
        this.transport = transport;
        this.distance_total_km = distance;
        this.time_total =time_work;
        this.time_stop = time_stop;
        try{
for(Transport tr: mtsTransportXML){
    if(tr.getTracker()==tracker){
        this.info= new Info(tr.getLenght_harvasters(),tr.getDriver(),tr.getPhone(),tr.getGos());
    }
}
if(this.info==null) this.info = new Info();
        }catch(Exception e){
            e.printStackTrace();
            this.info = new Info();
        }


    }

    public Report(int tracker, String transport,Info info) {
        this.tracker = tracker;
        this.transport = transport;
        this.info = info;

    }

    public int getTracker() {
        return tracker;
    }

    public void setTracker(int tracker) {
        this.tracker = tracker;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Double getDistance_total_km() {
        return distance_total_km;
    }

    public void setDistance_total_km(Double distance_total_km) {
        this.distance_total_km = distance_total_km;
    }

    public Date getTime_total() {
        return time_total;
    }

    public void setTime_total(Date time_total) {
        this.time_total = time_total;
    }

    public Date getTime_stop() {
        return time_stop;
    }

    public void setTime_stop(Date time_stop) {
        this.time_stop = time_stop;
    }

    public Info getInfo() {
        return info;
    }
    //Метод для получения полного отчета по комбайнам
    public ArrayList<TransportAction> getTransportActions() {
        return this.transportActions;
    }

    @Override
    public String toString() {
        return "Report{" +
                "time_total=" + time_total +
                ", time_stop =" + time_stop +
                ", distance_total_km=" + distance_total_km +
                ", transport='" + transport + '\'' +
                ", tracker=" + tracker +
                '}';
    }

    //меот для добавление в список транспорта событий транспорта
    public void addTransportAction(TransportAction transportAction){
      this.transportActions.add(transportAction);

    }
}
