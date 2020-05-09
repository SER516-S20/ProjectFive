import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Kunal Sharma
 * @created on 02-18-2020
 * @version 1.0
 */
public class Menu implements ActionListener {

	private static final long serialVersionUID = 1L;
	JMenu menu;
	static Boolean boolState = false;
	JMenuBar mb;
	JMenuItem save, restore, quit, newTab, compile, translate;
	java.util.List<Point> displayList = new ArrayList<Point>();
	String pathName = "";
	Point initial = new Point(0, 0);

	public JMenuBar createMenu() {
		try {
			save = new JMenuItem("Save");
			restore = new JMenuItem("Restore");
			quit = new JMenuItem("Quit");
			newTab = new JMenuItem("New");
			compile = new JMenuItem("Compile");
			translate = new JMenuItem("Translate");
			menu = new JMenu("Menu");
			mb = new JMenuBar();
			save.addActionListener(this);
			restore.addActionListener(this);
			quit.addActionListener(this);
			newTab.addActionListener(this);
			compile.addActionListener(this);
			translate.addActionListener(this);
			mb.add(menu);
			menu.add(newTab);
			menu.add(save);
			menu.add(restore);
			menu.add(compile);
			menu.add(quit);
			menu.add(translate);
			return mb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void LoadFileChooser() {
		try {
			JFileChooser selectFile = new JFileChooser();
			selectFile.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Data File", "dat");
			selectFile.addChoosableFileFilter(filter);
			selectFile.showOpenDialog(null);
			File f = selectFile.getSelectedFile();
			if (f.exists()) {
				if (!getFileExtension(f).equals("dat")) {
					JOptionPane.showMessageDialog(null, "Invalid File format");
				} else {
					pathName = f.getAbsolutePath();
					JOptionPane.showMessageDialog(null, "File Loaded Successfully");
				}
			} else {

			}
		} catch (Exception ex) {
		}
	}

	public void SaveFileChooser() {
		try {
			JFileChooser selectFile = new JFileChooser();
			selectFile.setDialogTitle("Save As");
			selectFile.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("dat", "dat");
			selectFile.addChoosableFileFilter(filter);
			int result = selectFile.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = selectFile.getSelectedFile();
				if (!getFileExtension(f).equals("dat")) {
					JOptionPane.showMessageDialog(null, "Invalid File format");
				} else if (getFileExtension(f).equals("dat")) {
					pathName = f.getAbsolutePath();
					JOptionPane.showMessageDialog(null, "File Saved Successfully");
				}
			}
		} catch (Exception ex) {

		}
	}

	private String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

	public void actionPerformed(ActionEvent e) {
		SystemFileManager objSFM = new SystemFileManager();
		if (e.getSource() == save) {
			SaveFileChooser();
			objSFM.saveShape(pathName);
		} else if (e.getSource() == restore) {
			LoadFileChooser();
			objSFM.restoreShape(pathName);
		} else if (e.getSource() == quit) {
			MainWindow.CloseApplication();
		} else if (e.getSource() == newTab) {
			boolState = true;
			System.out.println("Before"+MainWindow.tabbedPane.getTabCount());
			MainWindow.tabbedPane.removeAll();
			NewTab.mapRightPanels.clear();
			NewTab.localCounter = 0;
			MainWindow.obj.AddPanel("new_tab");
			System.out.println(MainWindow.tabbedPane.getTabCount());
			//new NewTab("button");
		} else if (e.getSource() == compile) {
			Compiler cObj = new Compiler();
			for (Integer key : NewTab.mapRightPanels.keySet()) {
				System.out.println("Compiling Tab number: " + key);
				cObj.compilePanel(key, NewTab.mapRightPanels.get(key));
			}
		}
	}

	public void clearDrawingBoard() {
		try {
			// TODO
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
