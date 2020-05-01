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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
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

        newButton.addActionListener(arg0 -> {
            System.out.println("New File created");
            newFile();
        });

        openButton.addActionListener(arg0 -> {
            System.out.println("Opened");
            FileManager.open();
        });
        saveButton.addActionListener(arg0 -> {
            System.out.println("Saved");
            FileManager.save();
        });

        compileButton.addActionListener(arg0 -> {
            System.out.println("Compiled");
            compile();
        });
        translateButton.addActionListener(arg0 -> {
            System.out.println("Translated");
            translate();
        });
    }

    public void newFile() {

    }

    public void compile() {

    }

    public void translate() {

    }
}