package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.TabList;
import view.Workspace;

/**
 * This class creates left panel, right panel and Menu button
 * 
 * @author Chandan
 * @version 4.0
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private OptionsPane optionsPanel;
	private JScrollPane scrollPane;
	private JTabbedPane tabbedPane;
	private final String TITLE = "Team 1";

	private Rectangle screenSize;
	private FileManager fileManager;
	private WorkspaceController workspaceController;
	private NodeCompiler nodeCompiler;
	private JMenuBar menuBar;
	private JScrollPane scrollPane2;

	public Main() {
		menuBar = new JMenuBar();
		nodeCompiler = new NodeCompiler();
		this.setMinimumSize(new Dimension(400, 400));
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setLayout(null);
		setTitle(TITLE);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				super.componentResized(e);
				scrollPane.setBounds(0, 0, e.getComponent().getWidth() / 4, 9 * e.getComponent().getHeight() / 10);
				scrollPane2.setBounds(e.getComponent().getWidth() / 4, 0, 3 * e.getComponent().getWidth() / 4,
						2 * screenSize.height / 19);
				tabbedPane.setBounds(e.getComponent().getWidth() / 4, screenSize.height / 10,
						11 * e.getComponent().getWidth() / 15, 4 * e.getComponent().getHeight() / 5);
			}
		});
	}

	private void createOptionsPanel() {

		optionsPanel = new OptionsPane();
		scrollPane = new JScrollPane(optionsPanel);
		optionsPanel.setBounds(0, 0, screenSize.width / 4, 9 * screenSize.height / 10);
		optionsPanel.setVisible(true);
		scrollPane.setBounds(0, 0, screenSize.width / 4, 9 * screenSize.height / 10);
		scrollPane.setVisible(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(optionsPanel);
		this.getContentPane().add(scrollPane);
	}

	/**
	 * Function to create multiple tabs in right panel.
	 */
	private void createTabs() {
		tabbedPane = new JTabbedPane();
		createWorkspace();
		tabbedPane.setVisible(true);
		tabbedPane.setBounds(screenSize.width / 4, screenSize.height / 10, 11 * screenSize.width / 15 + 10,
				4 * screenSize.height / 5);
		this.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				TabList.getInstance().setCurrentTabIndex(tabbedPane.getSelectedIndex());
				TabList.getInstance().getTab().getWorkspace().repaint();
			}
		});

	}

	/**
	 * Function to create workspace in each tab.
	 */
	private void createWorkspace() {
		TabList tabList = TabList.getInstance();
		Workspace workspace = new Workspace();
		tabList.addTab(workspace);
		workspaceController = new WorkspaceController();
		workspaceController.setTabbedPane(tabbedPane);
		tabList.getRecentTab().addObserver(workspaceController);
		tabbedPane.add("Tab 1", workspace);
	}

	/** create toolbar with icons **/
	private void createToolBar() {
		ToolBar toolBarPanel = new ToolBar();
		scrollPane2 = new JScrollPane(toolBarPanel);
		toolBarPanel.setVisible(true);
		scrollPane2.setBounds(screenSize.width / 4, 0, 3 * screenSize.width / 4, 2 * screenSize.height / 19);
		toolBarPanel.setBounds(screenSize.width / 4, 0, 3 * screenSize.width / 4, 2 * screenSize.height / 19);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setViewportView(toolBarPanel);
		this.getContentPane().add(scrollPane2);
	}

	/**
	 * Function to create menu bar with load,save, add workspace and compile
	 * buttons.
	 */
	private void createMenu() {
		JMenu menu = new JMenu("File");
		JMenuItem saveButton = new JMenuItem("Save");
		fileManager = new FileManager();
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileManager.saveFile();
			}
		});
		JMenuItem loadButton = new JMenuItem("Load");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileManager.loadFile(tabbedPane);
			}
		});
		menu.add(saveButton);
		menu.add(loadButton);
		JMenu projectMenu = new JMenu("Project");
		JMenuItem compileButton = new JMenuItem("Compile");
		compileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// nodeCompiler.createAdjacencyList();
				nodeCompiler.doCompile();
			}
		});
		JMenuItem translateButton = new JMenuItem("Translate");
		translateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nodeCompiler.doTranslate();
			}
		});
		projectMenu.add(compileButton);
		projectMenu.add(translateButton);
		menuBar.add(javax.swing.Box.createHorizontalStrut(10));
		menuBar.add(menu);
		menuBar.add(javax.swing.Box.createHorizontalStrut(10));
		menuBar.add(projectMenu);
		menuBar.add(javax.swing.Box.createHorizontalStrut(10));
		this.setJMenuBar(menuBar);
	}

	public static void main(String[] args) {
		Main frame = new Main();
		frame.createMenu();
		frame.createOptionsPanel();
		frame.createTabs();
		frame.createToolBar();
		frame.setVisible(true);
	}

}
