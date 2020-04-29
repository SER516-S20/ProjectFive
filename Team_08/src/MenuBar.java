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
		String MENU = "| MENU |";
		JMenu menu = new JMenu(MENU);
		JMenuBar menuBar = new JMenuBar();

		String SAVE = "Save Document";
		SaveDocument saveDocument = new SaveDocument(SAVE);
		saveDocument.addActionListener(saveDocument);

		String LOAD = "Load Document";
		LoadDocument loadDocument = new LoadDocument(LOAD);
		loadDocument.addActionListener(loadDocument);

		String COMPILE = "Compile Document";
		CompileFile compileFile = new CompileFile(COMPILE);
		compileFile.addActionListener(compileFile);
		
		menu.add(saveDocument);
		menu.add(loadDocument);
		menu.add(compileFile);
		menuBar.add(menu);

		//JButton NewTab = new JButton("  New Tab  ");
		Border border = new LineBorder(Color.BLACK, 1);
//		NewTab.setBorder(border);
//		NewTab.addActionListener(e -> {
//			if (e.getActionCommand().equals("  New Tab  ")) {
//				System.out.println("New Tab has been clicked");
//			}
//		});
		JButton Compiler = new JButton("  Compile  ");
		Compiler.setBorder(border);
		Compiler.addActionListener(e -> {
		});
		this.add(menuBar);
	}

}
