
package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import model.TabList;

/**
 * This is the class where all the JButtons with images of icons on it is
 * created on the toolbar.
 * 
 * @author Rishika Bera
 * @version 1.0
 */
public class ToolBar extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<JButton> shapes = new ArrayList<>();
	JToolBar toolbar= new JToolBar();
	public ToolBar() {
		
		initializeButtons();
		addActionListenersToButtons();
	}

	private JToolBar initializeButtons() {
		
		JPanel panel= new JPanel();
		JButton openBracket = new JButton(new ImageIcon("Resources//openBracket.png"));
		openBracket.setPreferredSize(new Dimension(140, 100));
		openBracket.setName(TabList.getInstance().getOpenBracket());
		shapes.add(openBracket);

		JButton closeBracket = new JButton(new ImageIcon("Resources//closeBracket.png"));
		closeBracket.setPreferredSize(new Dimension(140, 100));
        closeBracket.setName(TabList.getInstance().getCloseBracket());
		shapes.add(closeBracket);

		JButton lessThan = new JButton(new ImageIcon("Resources//lessThan.png"));
		lessThan.setPreferredSize(new Dimension(140, 100));
		lessThan.setName(TabList.getInstance().getLessThan());
		shapes.add(lessThan);

		JButton greaterThan = new JButton(new ImageIcon("Resources//greaterThan.png"));
		greaterThan.setPreferredSize(new Dimension(140, 100));
		greaterThan.setName(TabList.getInstance().getGreaterThan());
		shapes.add(greaterThan);

		JButton atTheRate = new JButton(new ImageIcon("Resources//atTheRate.png"));
		atTheRate.setPreferredSize(new Dimension(140, 100));
		atTheRate.setName(TabList.getInstance().getAtTheRate());
		shapes.add(atTheRate);

		JButton hyphen = new JButton(new ImageIcon("Resources//hyphen.png"));
		hyphen.setPreferredSize(new Dimension(140, 100));
		hyphen.setName(TabList.getInstance().getHyphen());
		shapes.add(hyphen);

		JButton bars = new JButton(new ImageIcon("Resources//bars.png"));
		bars.setPreferredSize(new Dimension(140, 100));
		bars.setName(TabList.getInstance().getBars());
		shapes.add(bars);
		
		JButton pound = new JButton(new ImageIcon("Resources//pound.png"));
		pound.setPreferredSize(new Dimension(140, 100));
		pound.setName(TabList.getInstance().getPound());
		shapes.add(pound);
		
		ListIterator<JButton> listIterator = shapes.listIterator();
		while (listIterator.hasNext()) {
			JButton button = listIterator.next();
			panel.add(button);}

		toolbar.add(panel);
		return toolbar;
		
	}

	private void addActionListenersToButtons() {
		ListIterator<JButton> listIterator = shapes.listIterator();
		Box box = Box.createHorizontalBox();
		while (listIterator.hasNext()) {
			JButton button = listIterator.next();
			box.add(Box.createHorizontalStrut(3));
			
			box.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TabList.getInstance().getTab().setSelectedOption(button.getName());
				}
			});
		}
		this.add(box);
	}
}
