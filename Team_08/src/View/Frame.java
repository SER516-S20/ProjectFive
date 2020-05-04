package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.Button;

/**
 * This class creates a View.Frame and adds two JPanels to the frame.
 *
 * @author Kartik Mathpal
 * @version 1.0
 */
public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static RightPanel rightPanel;
	private Dimension screenSize = new Dimension(1800, 1000);
	public static JTabbedPane tabbedPane;
	static List<RightPanel> panelList = new ArrayList<>();
	static List<JTabbedPane> tabList = new ArrayList<>();
	static Map<JTabbedPane, RightPanel> map = new HashMap<>();
	public JButton button;
	static int tabCount = 0;

	public Frame() {
		setMinimumSize(new Dimension(800, 500));
		setResizable(true);
		String TITLE = "Project 5 - Team 8";
		setTitle(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		pack();
		setLocationRelativeTo(null);
	}

	public void createTopPanel() {
		try {
			TopPanel topPanel = new TopPanel();
			Button button = new Button();

			button.addButtonsToTopPanel(topPanel);
			topPanel.setBounds(screenSize.width / 20, 0,
					4 * screenSize.width / 6, screenSize.height / 10);
			topPanel.setVisible(true);
			add(topPanel, BorderLayout.NORTH);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createLeftPanel() {
		try {
			LeftPanel leftPanel = new LeftPanel();
			Button button = new Button();
			button.addButtonsToLeftPanel(leftPanel);

			leftPanel.setPreferredSize(new Dimension(200, 600));
			JScrollPane sPane = new JScrollPane();
			sPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sPane.setViewportView(leftPanel);
			sPane.setPreferredSize(new Dimension(200, 300));
			add(sPane, BorderLayout.WEST);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createRightPanel() {
		try {

			tabbedPane = new JTabbedPane();
			rightPanel = new RightPanel();
			tabbedPane.add("Home Tab", rightPanel);// -----------
			tabList.add(tabbedPane);// base
			panelList.add(rightPanel); // default case

			tabbedPane.setPreferredSize(new Dimension(1000, 1000));
			tabbedPane.setBackground(Color.white);
			JScrollPane sPane = new JScrollPane();
			sPane.setViewportView(tabbedPane);
			sPane.setPreferredSize(new Dimension(500, 600));
			add(sPane, BorderLayout.CENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class MyChange implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
			int index = sourceTabbedPane.getSelectedIndex();
			System.out.println("Tab changed to: "
					+ sourceTabbedPane.getTitleAt(index));
			rightPanel = panelList.get(index);

		}
	}

	public static class MyAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			if (str.equals("#")) {
				String st = JOptionPane
						.showInputDialog(null, "Enter Tab Name.");
				if (!st.equals("")) {
					RightPanel rp = new RightPanel();
					tabCount++;
					JTabbedPane tb = new JTabbedPane(JTabbedPane.TOP);
					tb.setVisible(true);
					tb.revalidate();
					tb.repaint();
					tb.add(st, rp);
					tabList.add(tb);
					tabbedPane.addTab(st, tb);
					rightPanel = rp;
					panelList.add(rp);
					map.put(tb, rightPanel);
				}

			} else if (str.equals("Remove Tab")) {
			}
		}

	}

	public void createMenu() {
		this.setJMenuBar(new MenuBar());
	}

	public static void main(String[] args) {

		Frame f = new Frame();
		f.createTopPanel();
		f.createLeftPanel();
		f.createRightPanel();
		f.createMenu();
		f.setVisible(true);

	}

}
