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
                FileManager.open();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Saved");
                FileManager.save();
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

    public void compile() {

    }

    public void translate() {

    }
}