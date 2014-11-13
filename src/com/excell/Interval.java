package com.excell;

import java.util.Date;

/**
 * Created by disp.chimc on 13.11.14.
 */
public class Interval{
    private Date start_interval;
    private Date end_interval;
    private int minute;

    Interval(Date start_interval, Date end_interval, int minute) {
        this.start_interval = start_interval;
        this.end_interval = end_interval;
        this.minute = minute;
    }

    public Date getStart_interval() {
        return start_interval;
    }

    public void setStart_interval(Date start_interval) {
        this.start_interval = start_interval;
    }

    public Date getEnd_interval() {
        return end_interval;
    }

    public void setEnd_interval(Date end_interval) {
        this.end_interval = end_interval;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

}

