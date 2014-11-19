package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LyDjons on 10.10.14.
 */
public class TransportAction {
    private String status;
    private String place;
    private Date start;
    private Date end;
    private Date interval;
    private double milage;

    public TransportAction(String status,String place, String start, String end,String interval,String milage) throws ParseException {
        this.status=status;
        this.place = place;

        this.start =new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(start);
        this.end = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(end);
        this.interval =new SimpleDateFormat("HH:mm:ss").parse(interval);
        this.milage = Double.valueOf(milage);

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public double getMilage() {
        return milage;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setMilage(double milage) {
        this.milage = milage;
    }

    public Date getInterval() {
        return interval;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "TransportAction{" +
                "status='" + status + '\'' +
                ", place='" + place + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", milage=" + milage +
                '}';
    }
}
