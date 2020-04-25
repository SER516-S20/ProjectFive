import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Kunal Sharma
 * @created on 02-18-2020
 * @version 1.0
 */
public class Menu extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	java.util.List<Point> displayList = new ArrayList<Point>();
	String pathName = "";
	JButton clearBtn = new JButton("Clear");
	Point initial = new Point(0, 0);
	JButton saveBtn = new JButton("Save");
	JButton restoreBtn = new JButton("Restore");
	JButton quitBtn = new JButton("Quit");
	JButton newTabBtn = new JButton("NewTab");
	JButton compileBtn = new JButton("Compile");

	public Panel CreateMenu() {
		Panel pan = new Panel();
		clearBtn.addActionListener(this);
		pan.add(clearBtn);
		saveBtn.addActionListener(this);
		pan.add(saveBtn);
		restoreBtn.addActionListener(this);
		pan.add(restoreBtn);
		quitBtn.addActionListener(this);
		pan.add(quitBtn);
		newTabBtn.addActionListener(this);
		pan.add(newTabBtn);
		compileBtn.addActionListener(this);
		pan.add(compileBtn);
		pan.setSize(500, 50);
		return pan;
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
		if (e.getSource() == clearBtn) {
			clearDrawingBoard();
		} else if (e.getSource() == saveBtn) {
			SaveFileChooser();
			objSFM.saveShape(pathName);
		} else if (e.getSource() == restoreBtn) {
			LoadFileChooser();
			objSFM.restoreShape(pathName);
		} else if (e.getSource() == quitBtn) {
			MainWindow.CloseApplication();	
		}
		else if (e.getSource() == newTabBtn) {
			MainWindow.obj.AddPanel();
		}
		else if (e.getSource() == compileBtn) {

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
