import javax.swing.*;
import java.awt.*;

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
            FileManager.newFile();
            PanelLog.logString("New File created", Color.WHITE);
        });
        openButton.addActionListener(arg0 -> {
            FileManager.open();
            PanelLog.logString("File Opened", Color.WHITE);
        });
        saveButton.addActionListener(arg0 -> {
            FileManager.save();
            PanelLog.logString("File Saved", Color.WHITE);
        });

        compileButton.addActionListener(arg0 -> {
            PanelLog.logString("Compiled", Color.WHITE);
            compile();
        });
        translateButton.addActionListener(arg0 -> {
            PanelLog.logString("Translated", Color.WHITE);
            translate();
        });
    }

    public void newFile() {

    }

    public void compile() {
    	Compiler compiler = new Compiler();
		compiler.compile();
    }

    public void translate() {
        Translator translator = new Translator();
        translator.translate();
//        Translator translator=new Translator();
//        translator.translate();
    }
}