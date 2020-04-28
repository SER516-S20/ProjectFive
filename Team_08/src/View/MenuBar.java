package View;

import View.LoadDocument;
import Controller.*;
import Model.*;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
/**
 * This class consists of GUI for the Menu options.
 * @author Kartik Mathpal
 * @version 1.0
 */

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	MenuBar() {
		String FILE = "File";
		JMenu file = new JMenu(FILE);
		JMenu project = new JMenu("Project");
		JMenuBar menuBar = new JMenuBar();

		String SAVE = "Save";
		SaveDocument saveDocument = new SaveDocument(SAVE);
		saveDocument.addActionListener(saveDocument);

		String LOAD = "Open";
		LoadDocument loadDocument = new LoadDocument(LOAD);
		loadDocument.addActionListener(loadDocument);

		String COMPILE = "Compile";
		CompileFile compileFile = new CompileFile(COMPILE);
		compileFile.addActionListener(compileFile);

		file.add(saveDocument);
		file.add(loadDocument);
		project.add(compileFile);
		menuBar.add(file);
		menuBar.add(project);

		JButton NewTab = new JButton("  New Tab  ");
		Border border = new LineBorder(Color.BLACK, 1);
		NewTab.setBorder(border);
		NewTab.addActionListener(e -> {
			if (e.getActionCommand().equals("  New Tab  ")) {
				System.out.println("New Tab has been clicked");
				//Frame fr = new Frame();
				//fr.createRightpanel();
			}
		});
		JButton Compiler = new JButton("  Compile  ");
		Compiler.setBorder(border);
		Compiler.addActionListener(e -> {
		});
		this.add(menuBar);
		this.add(NewTab);
	}

	private void createTabs() {


	}

}
