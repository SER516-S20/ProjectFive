import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * @author Nikitha,Ashwath,Tarun Snehith Kishore Reddy Karna,
 * @Since 03-10-2020
 * @version 1.0
 */


public class Frame extends JFrame {
	private static final String FRAME_TITLE = "Team4";
	JFrame frame = new JFrame("Swing Tester");
	static JTabbedPane tabbedPane;
	static ArrayList<DrawingCanvas> canvasArray;
	JMenuBar menubar;
	public Frame() {
		this.setTitle(FRAME_TITLE);
		this.setSize(800, 600);
		this.setMinimumSize(new Dimension(800, 600));
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		menubar = new JMenuBar();
		menubar.setMinimumSize(new Dimension(800,40));
		final JLabel label = new JLabel();
		this.setJMenuBar(menubar);
		addFile();
		addProject();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void addFile() {
		JMenu menu = new JMenu("File");
		menubar.add(menu);
		JMenuItem newspace = new JMenuItem("New");
		JMenuItem load = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		load.addActionListener(new LoadManager(this));
		save.addActionListener(new SaveManager(this));
		newspace.addActionListener(new NewSpace(this));
		menu.add(save);
		menu.add(load);
		menu.add(newspace);
	}
	
	public void addProject() {
		JMenu menu = new JMenu("Project");
		menubar.add(menu);
		JMenuItem compile = new JMenuItem("Compile");
		JMenuItem translate = new JMenuItem("Translate");
		compile.addActionListener(new Compile(this));
		translate.addActionListener(new Translate());
		menu.add(compile);
		menu.add(translate);
	}
	
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
	
	public void setTabbedPane(JTabbedPane pane)
	{
		tabbedPane = pane;
	}
	
	public ArrayList<DrawingCanvas> getCanvasArray()
	{
		return canvasArray;
	}
	
	public void setCanvasArray(ArrayList<DrawingCanvas> array)
	{
		canvasArray = array;
	}
	
	public static void main(String[] args) {
		new Frame();
	}
}
