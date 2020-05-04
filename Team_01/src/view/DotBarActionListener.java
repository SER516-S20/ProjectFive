package view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Tab;
import model.TabList;

/**
 * This is the class where ActionListener is added to dots and bars of icons.
 * 
 * @author Rishika Bera
 * @version 1.0
 */

public class DotBarActionListener implements ActionListener {

	private Icons icon;
	private Point point;
	private boolean isInput;

	public DotBarActionListener(Icons icon, Point point, boolean isInput) {
		this.icon = icon;
		this.point = point;
		this.isInput = isInput;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tab tab = TabList.getInstance().getTab();
		if (!isInput || tab.isFirstDotClicked()) {
			if (!tab.isFirstDotClicked()) {
				tab.setFirstDotClicked(true);
				tab.setOriginIcon(icon);
				tab.setOriginPoint(point);
				tab.setOriginInput(isInput);
				tab.setOriginDot((JButton) e.getSource());
			} else if (icon != tab.getOriginIcon()) {
				tab.setDestInput(isInput); 
				tab.setDestIcon(icon);
				tab.setDestDot((JButton) e.getSource());
				tab.setDestPoint(point, "Drawline");
			}
		}
	}
}
