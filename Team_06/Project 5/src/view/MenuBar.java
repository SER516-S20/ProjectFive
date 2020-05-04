package view;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.OpenApplication;
import controller.SaveApplication;
import controller.WorkSpace;
import controller.Compile;
import controller.Translate;

/**
 *
 * @author Rahul
 * @since 03-08-2020
 * @Description: Menu will show different options like New,Save,Open.
 */
public class MenuBar {

	public MenuBar(JFrame mainFrame) {
		JMenuBar menuBar = new JMenuBar(); 
		JMenu fileMenu = new JMenu("File");
		JMenuItem newTab = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");	
		JMenuItem save = new JMenuItem("Save");
		
		JMenu projectMenu = new JMenu("Project");
		
		JMenuItem compile = new JMenuItem("Compile");	
		JMenuItem translate = new JMenuItem("Translate");
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					new SaveApplication(mainFrame);
			}
		});
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new OpenApplication(mainFrame);
			}
		});
		
		newTab.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveApplication(mainFrame);
				WorkSpace.getInstance().deleteAllTabs(mainFrame);
			}
		});
		
		compile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String msg = new Compile().checkPanel();
				showMessageDialog(null, msg);
			}
		});
		
		translate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = new Compile().checkPanel();
				if (msg.equals("Compiled Successfully")) {
					String code = new Translate().translateCode();
					JTextArea ta = new JTextArea(30, 50);
	                ta.setText(code);
	                ta.setWrapStyleWord(true);
	                ta.setLineWrap(true);
	                ta.setCaretPosition(0);
	                ta.setEditable(false);

	                JOptionPane.showMessageDialog(null, new JScrollPane(ta), "RESULT", JOptionPane.INFORMATION_MESSAGE);
	                
				}else {
					JTextArea ta = new JTextArea(50, 20);
	                ta.setText(msg);
	                ta.setWrapStyleWord(true);
	                ta.setLineWrap(true);
	                ta.setCaretPosition(0);
	                ta.setEditable(false);

	                JOptionPane.showMessageDialog(null, new JScrollPane(ta), "RESULT", JOptionPane.INFORMATION_MESSAGE);
	                	
					showMessageDialog(null, msg);
				}
				
			}
		});
		
		fileMenu.add(newTab);
		fileMenu.add(open);
		fileMenu.add(save);
		
		projectMenu.add(compile);
		projectMenu.add(translate);
		
		menuBar.add(fileMenu);
		menuBar.add(projectMenu);
		
		mainFrame.setJMenuBar(menuBar);
	}

}
