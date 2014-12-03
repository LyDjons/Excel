package com.swing;

/**
 * Created by disp.chimc on 02.12.14.
 */


import com.disp.Disp;
import com.disp.disp.control.DispControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import java.util.Date;


public class MenuDemo {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";

    public JMenuBar createMenuBar() {
        final JMenuBar menuBar;
        JMenu menuFile;
        JMenuItem loadItem;
        JMenu saveMenu;
        JMenuItem saveItemPath;
        JMenuItem saveItemNew;
        final Disp disp = new DispControl();

        //Create the menuFile bar.
        menuBar = new JMenuBar();
            menuFile = new JMenu("Файл");
        menuBar.add(menuFile);

        //a group of JMenuItems
        loadItem = new JMenuItem("Загрузить ДУТ");
        saveMenu = new JMenu("Создать отчет");

        saveItemPath = new JMenuItem("В существующий файл");
        saveMenu.add(saveItemPath);

            loadItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                   final String path_load=getPathToFile("Загрузить");
                    if(path_load==null) return;

                    Thread thread = new Thread(){
                  public void  run(){
                      output.append(new Date() + " :  Операция  Загрузки ДУТ..." + newline);

                      try {


                          disp.loadReport(path_load);


                      } catch (Exception e) {
                           output.append("Не удаллсь загрузить файл");
                          return;
                      }

                      output.append("Файл успешно загружен!"+newline);
                  }

                };
                    thread.start();
                }
            });

        saveItemPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String path_save = getPathToFile("Сохранить");
                if (path_save == null) return;


                Thread thread = new Thread() {
                    public void run() {
                        output.append(new Date() + " :  Терпение, пытаюсь сохранить..." + newline);
                        try{
                            disp.load_config("config/config.xlsx");
                        }catch (FileNotFoundException e){

                            output.append("Не удалось загрузить configs.xlsx");
                        }catch (Exception e){

                        }
                        try {

                          disp.save_report(disp.getReport(),path_save,disp.getConfigs());


                        } catch (Exception e1) {

                           output.append("Не удалось сохранить. Что то не так!");
                            return;
                        }

                        output.append("Файл сохранен! " + newline);
                    }

                };
                thread.start();
            }
        });

        ActionListener saveActionListener = new SaveActionListener();
            saveMenu.addActionListener(saveActionListener);

             menuFile.add(loadItem);
             menuFile.add(saveMenu);


        return menuBar;
    }
    public String getPathToFile(String name_dialog){
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, name_dialog);
        if (ret == JFileChooser.APPROVE_OPTION) {
            return fileopen.getSelectedFile().getPath();

        } else return null;
    }
    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }


    public class SaveActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            output.append(new Date() + " :  Операция  охранения..." + newline);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MenuDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Super disp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MenuDemo demo = new MenuDemo();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                createAndShowGUI();

            }
        });
    }
}
