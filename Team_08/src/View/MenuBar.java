package View;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;


import Controller.*;
import Model.*;

/**
 * This class consists of GUI for the Menu options.
 *
 * @author Kartik Mathpal
 * @author Sandya Manoharan
 * @version 1.1
 */

public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    MenuBar() {

        String FileMenu = "File";
        JMenu Fmenu = new JMenu(FileMenu);
        JMenuBar FmenuBar = new JMenuBar();

        String ProjectMenu = "Project";
        JMenu Pmenu = new JMenu(ProjectMenu);
        JMenuBar PmenuBar = new JMenuBar();

        String OpenDoc = "Open";
        LoadDocument loadDocument = new LoadDocument(OpenDoc);
        loadDocument.addActionListener(loadDocument);

        String NewDoc = "New";
        NewDocument newDocument = new NewDocument(NewDoc);
        newDocument.addActionListener(newDocument);

        String SaveDoc = "Save";
        SaveDocument saveDocument = new SaveDocument(SaveDoc);
        saveDocument.addActionListener(saveDocument);

        String CompileProject = "Compile";
        CompileFile compileFile = new CompileFile(CompileProject);
        compileFile.addActionListener(compileFile);

        String TranslateProject = "Translate";
        Translate translate = new Translate(TranslateProject);
        translate.addActionListener(translate);

        Fmenu.add(newDocument);
        Fmenu.add(loadDocument);
        Fmenu.add(saveDocument);

        FmenuBar.add(Fmenu);

        Pmenu.add(compileFile);
        Pmenu.add(translate);
        PmenuBar.add(Pmenu);

        this.add(FmenuBar);
        this.add(Pmenu);

    }
}