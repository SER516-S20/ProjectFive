package controller;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.TabList;

/**
 * This is the class where all the JButtons with images of icons on it is
 * created on the left Panel.
 * 
 * @author Rishika Bera
 * @version 1.0
 */
public class OptionsPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean isMoving = false;

	private List<JButton> shapes = new ArrayList<>();

	public OptionsPane() {
		initializeButtons();
		addActionListenersToButtons();
	}

	private void initializeButtons() {
		JButton openBracket = new JButton(new ImageIcon("Resources//openBracket.png"));
		openBracket.setName(TabList.getInstance().getOpenBracket());
		shapes.add(openBracket);

		JButton closeBracket = new JButton(new ImageIcon("Resources//closeBracket.png"));
		closeBracket.setName(TabList.getInstance().getCloseBracket());
		shapes.add(closeBracket);

		JButton lessThan = new JButton(new ImageIcon("Resources//lessThan.png"));
		lessThan.setName(TabList.getInstance().getLessThan());
		shapes.add(lessThan);

		JButton greaterThan = new JButton(new ImageIcon("Resources//greaterThan.png"));
		greaterThan.setName(TabList.getInstance().getGreaterThan());
		shapes.add(greaterThan);

		JButton atTheRate = new JButton(new ImageIcon("Resources//atTheRate.png"));
		atTheRate.setName(TabList.getInstance().getAtTheRate());
		shapes.add(atTheRate);

		JButton hyphen = new JButton(new ImageIcon("Resources//hyphen.png"));
		hyphen.setName(TabList.getInstance().getHyphen());
		shapes.add(hyphen);

		JButton bars = new JButton(new ImageIcon("Resources//bars.png"));
		bars.setName(TabList.getInstance().getBars());
		shapes.add(bars);

		JButton pound = new JButton(new ImageIcon("Resources//pound.png"));
		pound.setName(TabList.getInstance().getPound());
		shapes.add(pound);
	}

	private void addActionListenersToButtons() {
		ListIterator<JButton> listIterator = shapes.listIterator();
		Box box = Box.createVerticalBox();
		while (listIterator.hasNext()) {
			JButton button = listIterator.next();
			box.add(Box.createVerticalStrut(10));
			button.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					if (!isMoving) {
						TabList.getInstance().getTab().setSelectedOption(button.getName());
						TabList.getInstance().getTab().getWorkspace().setMovingCursor();
						Cursor cursor = new Cursor(Cursor.MOVE_CURSOR);
						setCursor(cursor);
					}
					isMoving = true;
				}
			});
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.getLocationOnScreen().getX() > TabList.getInstance().getTab().getWorkspace().getParent()
							.getLocationOnScreen().getX()) {
						Point convertPoint = SwingUtilities.convertPoint(button, e.getPoint(),
								TabList.getInstance().getTab().getWorkspace());
						TabList.getInstance().getTab().setPoint(convertPoint, "Clicked");
					}
					TabList.getInstance().getTab().setSelectedOption(null);
					isMoving = false;
					TabList.getInstance().getTab().getWorkspace().setDefaultCursor();
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					setCursor(cursor);
				}
			});
			box.add(button);
		}
		this.add(box);
	}
}
