package com.swing;

/**
 * Created by disp.chimc on 02.12.14.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/* MenuDemo.java requires images/middle.gif. */

/*
 * This class is just like MenuLookDemo, except the menu items
 * actually do something, thanks to event listeners.
 */
public class MenuDemo {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";

    public JMenuBar createMenuBar() {
        final JMenuBar menuBar;
        JMenu menuFile;
        JMenuItem loadItem;
        final JMenuItem menuSaveItem;


        //Create the menuFile bar.
        menuBar = new JMenuBar();
            menuFile = new JMenu("Файл");
        menuBar.add(menuFile);

        //a group of JMenuItems
        loadItem = new JMenuItem("Загрузить ДУТ");
        menuSaveItem = new JMenuItem("Создать отчет");
      //  ActionListener loadActionListener = new LoadActionListener();
            loadItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(){
                  public void  run(){
                      output.append(new Date() + " :  Операция  Загрузки ДУТ..." + newline);

                      try {

                          Thread.sleep(10000);    // здесь должна происходить загрузка дута


                      } catch (InterruptedException e1) {
                          e1.printStackTrace();
                      }

                      output.append("Well done!");
                  }

                };
                    thread.start();
                }
            });
        ActionListener saveActionListener = new SaveActionListener();
            menuSaveItem.addActionListener(saveActionListener);

             menuFile.add(loadItem);
             menuFile.add(menuSaveItem);


        return menuBar;
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

   /* public class LoadActionListener implements ActionListener {

       public void actionPerformed(ActionEvent e) {
            output.append(new Date() + " :  Операция  Загрузки ДУТ..." + newline);
            try {

                Thread.sleep(10000);

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            output.append("Well done!");


    }*/
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
