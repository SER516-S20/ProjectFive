import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	JMenuBar mb;  
	JMenuItem clear, save, restore, quit, newTab,compile;  
	java.util.List<Point> displayList = new ArrayList<Point>();
	String pathName = "";
	//JButton clearBtn = new JButton("Clear");
	Point initial = new Point(0, 0);
	

	public JMenuBar createMenu() {
		 try {
			 clear = new JMenuItem("Clear");
			save = new JMenuItem("Save");
				restore = new JMenuItem("Restore");
				 quit = new JMenuItem("Quit");
				 newTab = new JMenuItem("NewTab");
				 compile = new JMenuItem("Compile");
			//Panel pan = new Panel();
			menu=new JMenu("Menu"); 
			mb=new JMenuBar();  
			clear.addActionListener(this);
			//pan.add(clearBtn);
			save.addActionListener(this);
			//pan.add(saveBtn);
			restore.addActionListener(this);
			//pan.add(restoreBtn);
			quit.addActionListener(this);
			//pan.add(quitBtn);
			newTab.addActionListener(this);
			//pan.add(newTabBtn);
			compile.addActionListener(this);
			//pan.add(compileBtn);
			//pan.setSize(500, 50);
			//return pan;
			mb.add(menu);
			menu.add(newTab);
			menu.add(save);
			menu.add(restore);
			menu.add(clear);
			menu.add(compile);
			menu.add(quit);
			
			
			//mb.setSize(400,400);
			return mb;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		if (e.getSource() == clear) {
			clearDrawingBoard();
		} else if (e.getSource() == save) {
			SaveFileChooser();
			objSFM.saveShape(pathName);
		} else if (e.getSource() == restore) {
			LoadFileChooser();
			objSFM.restoreShape(pathName);
		} else if (e.getSource() == quit) {
			MainWindow.CloseApplication();	
		}
		else if (e.getSource() == newTab) {
			MainWindow.obj.AddPanel();
		}
		else if (e.getSource() == compile) {

		}
	}

	public void clearDrawingBoard()
	{
		try{
			//TODO
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
