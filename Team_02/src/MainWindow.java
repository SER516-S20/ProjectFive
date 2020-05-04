import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

/**
 * @author Kunal Sharma
 * @created on 01-27-2020
 * @version 1.0
 * @author Kunal Sharma
 * @created on 02-19-2020
 * @version 2.0
 */
public class MainWindow extends JFrame{
	static JPanel drawingBoardPanel = new JPanel();
	static JFrame frame;
	static JPanel p1;
	static NewTab obj;
	static JTabbedPane tabbedPane = new JTabbedPane();
	static int testWidth = 1200;
	
	public MainWindow()
	{

		this.setSize(ShapeDimension.frameWidth, ShapeDimension.frameHeight);
		//int frameHeight = 800;
		//int frameWidth = 1200;
		this.setTitle("Project Five - Team 2");
		//this.setBackground(Color.LIGHT_GRAY);
		//this.setSize(new Dimension(frameWidth, frameHeight));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		Menu objMenu = new Menu();
		Shapes leftShapes = new Shapes();
		//obj = new NewTab("main_window");
		new NewTab("main_window");
		Shapes shapes = new Shapes();
		//jlabel.setText("Welcome");
		//this.getContentPane().add(jlabel, BorderLayout.SOUTH);
		//tabbedPane.addChangeListener(this);
		//DrawingArea drawingArea = new DrawingArea();
		//this.getContentPane().add(drawingArea);
		this.getContentPane().add(leftShapes.CreateLeftShapes(), BorderLayout.WEST);
		this.add(tabbedPane, BorderLayout.CENTER);
		JMenuBar menuBar = objMenu.createMenu();
		this.setJMenuBar(menuBar);
		JToolBar toolBar = shapes.CreateShapes();
		//tabbedPane.setBackground(Color.blue);
		//obj.createAndShowGUI();
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		JScrollPane scrPane = new JScrollPane(tabbedPane);
		add(scrPane);
		//scrPane.setBackground(Color.orange);
		scrPane.setVisible(true);
		//frame.setVisible(true); 
	}
	
	
	public static void CloseApplication() {
		try {
			System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);  
		
	}

	/*
	static JPanel drawingBoardPanel = new JPanel();
	static JFrame frame;
	static JPanel p1;
	static NewTab obj;

	public static void CloseApplication() {
		try {
			System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void callAddButton() {
		new NewTab("button");
	}

	public static void main(String[] args) {
		Menu objMenu = new Menu();
		Shapes shapes = new Shapes();
		Shapes leftShapes = new Shapes();
		frame = new JFrame("Main Window");
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(objMenu.createMenu());
		
		JToolBar topToolBarToolKit = shapes.CreateShapes();
		topToolBarToolKit.setBounds(10, 0, 1350, 100);
		topToolBarToolKit.setBackground(Color.red);
		frame.getContentPane().add(topToolBarToolKit);
		
		JToolBar leftToolBarToolKit = leftShapes.CreateLeftShapes();
		leftToolBarToolKit.setBounds(10, 110,150, 700);
		leftToolBarToolKit.setBackground(Color.blue);
		frame.getContentPane().add(leftToolBarToolKit);
		
		new NewTab("main_window");
		//obj = new NewTab();
		//obj.createAndShowGUI();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	*/
}
