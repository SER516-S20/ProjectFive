import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Contains Menu items
 *
 * @author Aditya Bajaj
 * @since April 26, 2020
 */

public class MenuBar {
    JMenuBar menu;

    public MenuBar() {
        menu = new JMenuBar();
        JMenu fileButton = new JMenu("File");
        JMenu runButton = new JMenu("Project");

        menu.add(fileButton);
        JMenuItem newButton = new JMenuItem("New");
        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");
        fileButton.add(newButton);
        fileButton.add(openButton);
        fileButton.add(saveButton);

        menu.add(runButton);
        JMenuItem compileButton = new JMenuItem("Compile");
        JMenuItem translateButton = new JMenuItem("Translate");
        runButton.add(compileButton);
        runButton.add(translateButton);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("New File created");
                newFile();
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Opened");
                open();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Saved");
                save();
            }
        });

        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Compiled");
                compile();
            }
        });
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Translated");
                translate();
            }
        });
    }

    public void newFile() {

    }

    public void save() {
        FileDialog dialog = new FileDialog(MainFrame.mainFrame, "Enter file name to Save");
        dialog.setMode(FileDialog.SAVE);
        System.out.println(dialog.getDirectory());
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file == null)
            return;
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        Component[] tabsToSave = MainFrame.PANEL_RIGHT.getComponents();
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tabsToSave);
            objectOutputStream.close();
            fileOutputStream.close();
            JOptionPane.showMessageDialog(MainFrame.mainFrame, "Saved as " + file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        FileDialog dialog = new FileDialog(MainFrame.mainFrame, "Select file to open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file == null)
            return;
        MainFrame.PANEL_RIGHT.removeAll();
        PanelRight.tabNum = 1;
        Component[] tabsToOpen;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            tabsToOpen = (Component[]) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            for (Component component : tabsToOpen) {
                PanelRightTab tab = (PanelRightTab) component;
                PanelRight.tabNum++;
                MainFrame.PANEL_RIGHT.addTab("Tab " + PanelRight.tabNum, tab);
                ListenersPanelRightTab.addAllListenersToTab(tab);
                tab.repaint();
            }
            Database.selectedTab = (PanelRightTab) tabsToOpen[0];

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void compile() {

    }

    public void translate() {

    }
}