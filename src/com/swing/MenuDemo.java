package com.swing;

/**
 * Created by disp.chimc on 02.12.14.
 */


import com.disp.Disp;
import com.disp.disp.control.DispControl;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.Date;


public class MenuDemo {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";

    public JMenuBar createMenuBar() {
        final JMenuBar menuBar;
        JMenu menuFile;
        JMenu menuSetting;
        JMenuItem loadItem;
        JMenu saveMenu;
        JMenu menuInfo;
        JMenuItem saveItemPath;
        JMenuItem saveItemNew;
        JMenuItem show_config;
        JMenuItem autor;
        final Disp disp = new DispControl();

        //Create the menuFile bar.
        menuBar = new JMenuBar();
            menuFile = new JMenu("Файл");
            menuSetting = new JMenu("Настройки");
            menuInfo = new JMenu(" WTF???");

        menuBar.add(menuFile);
        menuBar.add(menuSetting);
        menuBar.add(menuInfo);

        //a group of JMenuItems
        loadItem = new JMenuItem("Загрузить ДУТ");
        saveMenu = new JMenu("Создать отчет");
            saveItemPath = new JMenuItem("В существующий файл");
            saveItemNew = new JMenuItem("В новый файл");

            saveMenu.add(saveItemPath);
            saveMenu.add(saveItemNew);
        show_config = new JMenuItem("Файл конфгурации");
        autor = new JMenuItem("Об авторе");
        autor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.cancelButtonText", "Annuler");
                UIManager.put("OptionPane.noButtonText", "Non");
                UIManager.put("OptionPane.okButtonText", "Спасибо, благодарности отправил");
                UIManager.put("OptionPane.yesButtonText", "Oui");
                JFrame parent = new JFrame();
                String multiLineMsg[] = { "Hello,", "World"} ;
                JOptionPane.showMessageDialog(parent, multiLineMsg);

            }
        });
        show_config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Desktop desktop = null;

                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File("config/config.xlsx"));


                } catch (Exception e3) {
                   output.append("Не найден путь. Ищи вручную"+newline);
                }
            }

        });
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
        saveItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               final String path_save = getPathToFile("Cоздать")+".xlsx";
                try {
                    File file = new File(path_save);
                    file.createNewFile();
                    FileOutputStream fis = new FileOutputStream(path_save);
                    Workbook workbook = new XSSFWorkbook();
                        workbook.createSheet("Лист1");
                        workbook.write(fis);
                } catch (Exception e1) {
                    output.append("Не удалось создать файл");
                }
                if (path_save == null) return;
                Thread thread = new Thread() {
                    public void run() {
                        output.append(new Date() + " :  Терпение, пытаюсь сохранить..." + newline);
                        try{
                            disp.load_config("config/config.xlsx");
                        }catch (Exception e){
                            output.append("Не удалось загрузить configs.xlsx"+newline);
                        }
                        try {

                            disp.save_report(disp.getReport(),path_save,disp.getConfigs());

                        } catch (Exception e1) {
                           output.append("Не удалось сохранить. Что то не так!"+newline);
                            return;
                        }

                        output.append("Файл сохранен! " + newline);
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
                        }catch (Exception e){

                            output.append("Не удалось загрузить configs.xlsx");
                        }
                        try {

                          disp.save_report(disp.getReport(),path_save,disp.getConfigs());

                        } catch (Exception e1) {
                           output.append("Не удалось сохранить. Что то не так!"+newline);
                            return;
                        }
                        output.append("Файл сохранен! " + newline);
                    }

                };
                thread.start();
            }
        });

             menuFile.add(loadItem);
             menuFile.add(saveMenu);
        menuSetting.add(show_config);
        menuInfo.add(autor);

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
        frame.setAlwaysOnTop(true);

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
