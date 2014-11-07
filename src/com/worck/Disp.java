package com.worck;

import com.Report;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by disp.chimc on 28.10.14.
 */
public interface Disp {
    //выгрузка полного отчета в ArrayList
    public ArrayList<Report> load_report(String path);

    public void save_report(ArrayList<Report> reports, String path) throws IOException;
}
