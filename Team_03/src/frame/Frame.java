package frame;

import listener.*;
import shape.*;
import shape.Shape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chandan Yadav
 * @since 01/26/2020
 * @version 1.0
 */
public class Frame extends JFrame implements ChangeListener {

	private static Frame singleInstance = null;

	public static Map<Integer, DrawingArea> mapDrawingAreas = new HashMap<>();
	public static int currentTab = 0;
	static int count = 0;
	public static JLabel jlabel = new JLabel();

	Compile compile = new Compile();;
	JTabbedPane tabbedPane = new JTabbedPane();

	public Frame() {
		int frameHeight = 700;
		int frameWidth = 1200;
		this.setTitle("Project Five - Team 3");
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		jlabel.setText("Welcome");
		this.getContentPane().add(jlabel, BorderLayout.SOUTH);
		tabbedPane.addChangeListener(this);
		this.add(tabbedPane, BorderLayout.CENTER);
		JMenuBar menuBar = constructMenuBar();
		this.setJMenuBar(menuBar);
		JToolBar toolBar = constructToolBar();
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		JScrollPane scrPane = new JScrollPane(tabbedPane);
		add(scrPane);
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public static Frame getInstance() {
		if (singleInstance == null) {
			singleInstance = new Frame();
		}
		return singleInstance;
	}

	public void createNewTab(Shape selectedShape) {
		DrawingArea obj = new DrawingArea();
		String temp = Constants.TAB + count;
		tabbedPane.addTab(temp, obj);
		mapDrawingAreas.put(count, obj);
		selectedShape.setMessage(temp);
		count = count + 1;
	}

	private JToolBar constructToolBar() {
		ShapePanel shapePanel = new ShapePanel();
		shapePanel.addMouseListener(new MouseListener());
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.add(shapePanel.functionBlockBegin());
		toolBar.add(shapePanel.functionBlockEnd());
		toolBar.add(shapePanel.ifBlockBegin());
		toolBar.add(shapePanel.ifBlockEnd());
		toolBar.add(shapePanel.forLoop());
		toolBar.add(shapePanel.valueHolder());
		toolBar.add(shapePanel.barShape());
		toolBar.add(shapePanel.hashBlock());
		new JButtonActionListener().addActionListener();
		return toolBar;
	}

	private JMenuBar constructMenuBar() {
		JMenuItem newSpaceMenuItem = constructNewSpaceButton();
		JMenuItem openMenuItem = constructOpenButton();
		JMenuItem saveMenuItem = constructSaveButton();
		JMenuItem compileMenuItem = constructCompileButton();
		JMenuItem translateMenuItem = constructTranslateButton();
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(newSpaceMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		JMenu projectMenu = new JMenu("Project");
		projectMenu.add(compileMenuItem);
		projectMenu.add(translateMenuItem);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(projectMenu);
		return menuBar;
	}

	private JMenuItem constructNewSpaceButton() {
		JMenuItem newSpaceMenuItem = new JMenuItem(Constants.NEW_SPACE);
		DrawingArea obj = new DrawingArea();
		String temp = Constants.TAB + count;
		tabbedPane.addTab(temp, obj);
		mapDrawingAreas.put(count, obj);
		count = count + 1;
		return newSpaceMenuItem;
	}

	private JMenuItem constructOpenButton() {
		JMenuItem openMenuItem = new JMenuItem(Constants.LOAD);
		DrawingArea obj1 = new DrawingArea();
		openMenuItem.addActionListener(event -> {
			obj1.load(tabbedPane);
			obj1.repaintOnDrag();
		});
		return openMenuItem;
	}

	private JMenuItem constructSaveButton() {
		JMenuItem saveMenuItem = new JMenuItem(Constants.SAVE);
		DrawingArea obj2 = new DrawingArea();
		saveMenuItem.addActionListener(event -> {
			obj2.save();
		});
		return saveMenuItem;
	}

	private JMenuItem constructCompileButton() {
		JMenuItem compileMenuItem = new JMenuItem(Constants.COMPILE);
		compileMenuItem.addActionListener(event -> {
			// Save current state
			compile.validate();
		});
		return compileMenuItem;
	}

	private JMenuItem constructTranslateButton() {
		JMenuItem translateMenuItem = new JMenuItem(Constants.TRANSLATE);
		translateMenuItem.addActionListener(event -> {
			new TranslatePackage().translateDiagram();
		});
		return translateMenuItem;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		currentTab = tabbedPane.getSelectedIndex();
		if (currentTab != 0) {
			new DefaultShapes().addDefaultShapes();
		}
	}

}
