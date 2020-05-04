import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;

/**
 * File Manager to save and open the workspace
 *
 * @author Praveen Kumar P
 * @author Aditya Bajaj
 * @since April 29, 2020
 */
public class FileManager {

    /**
     * Saves all the tabs as a file
     */
    static public void save() {

        FileDialog dialog = new FileDialog(MainFrame.mainFrame, "Enter file name to Save");
        dialog.setMode(FileDialog.SAVE);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();

        if (file == null)
            return;
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        Component[] tabsToSave = MainFrame.PANEL_RIGHT.getComponents();

        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(tabsToSave);
            objectOutputStream.writeObject(ListenersPanelRightTab.mapOP);
            objectOutputStream.writeObject(ListenersInputPopup.mapTab);

            objectOutputStream.close();
            fileOutputStream.close();
            JOptionPane.showMessageDialog(MainFrame.mainFrame, "Saved as " + file);

        } catch (FileNotFoundException e) {
            PanelLog.logString("File not found", Color.RED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open a saved file and load it into the software
     */
    static void open() {

        FileDialog dialog = new FileDialog(MainFrame.mainFrame, "Select file to open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        if (file == null)
            return;

        MainFrame.PANEL_RIGHT.removeAll();
        ListenersPanelRightTab.mapOP.clear();
        ListenersInputPopup.mapTab.clear();

        Component[] tabsToOpen;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            tabsToOpen = (Component[]) objectInputStream.readObject();
            ListenersPanelRightTab.mapOP = (HashMap<Op, PanelRightTab>) objectInputStream.readObject();
            ListenersInputPopup.mapTab = (HashMap<PanelRightTab, String>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            PanelRight.tabNum = 1;
            int x = 1;
            for (Component component : tabsToOpen) {
                PanelRightTab tab = (PanelRightTab) component;
                if (x == 1) {
                    MainFrame.PANEL_RIGHT.addTab("Tab 1", tab);
                    x = 0;
                } else
                    MainFrame.PANEL_RIGHT.addTab(ListenersInputPopup.mapTab.get(component), tab);
                PanelRight.tabNum++;

                ListenersPanelRightTab.addListenersToPanelOps(tab);
                ListenersPanelRightTab.addRightPanelTabListeners(tab);
                tab.repaint();
            }
            Database.selectedTab = (PanelRightTab) tabsToOpen[0];

        } catch (FileNotFoundException e) {
            PanelLog.logString("File not found", Color.RED);
        } catch (IOException e) {
            PanelLog.logString("Error initializing stream", Color.RED);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public void newFile() {
        MainFrame.PANEL_RIGHT.removeAll();
        ListenersPanelRightTab.mapOP.clear();
        ListenersInputPopup.mapTab.clear();
        PanelRight.tabNum = 1;
        MainFrame.PANEL_RIGHT.addNewTab();
    }

}



