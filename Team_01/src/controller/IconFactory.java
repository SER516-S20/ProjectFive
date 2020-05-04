package controller;

import java.awt.Point;

import model.TabList;
import view.AtTheRate;
import view.CloseBracket;
import view.DoubleBar;
import view.GreaterThan;
import view.Hyphen;
import view.Icons;
import view.LessThan;
import view.OpenBracket;
import view.Pound;

/**
 * This is the factory class used to create and draw different icons on runtime.
 * 
 * @author Mayank Kataruka
 * @version 1.0
 */

public class IconFactory {
	
	private TabList tabList;
	public IconFactory() {
		tabList = TabList.getInstance();
	}

	/**
	 * This method creates an instance of the selected icon and draws it.
	 * 
	 * @param point
	 * @param icon
	 * @param graphics
	 * @return
	 */
	public Icons getIconObject(Point point, String icon) {
		if (tabList.getOpenBracket().equals(icon) && !tabList.getTab().isOpenBracketAdded()) {
			tabList.getTab().setOpenBracketAdded(true);
			return new OpenBracket(point);
		} else if (tabList.getCloseBracket().equals(icon) && !tabList.getTab().isCloseBracketAdded()) {
			tabList.getTab().setCloseBracketAdded(true);
			return new CloseBracket(point);
		} else if (tabList.getLessThan().equals(icon)) {
			return new LessThan(point);
		} else if (tabList.getGreaterThan().equals(icon)) {
			return new GreaterThan(point);
		} else if (tabList.getAtTheRate().equals(icon)) {
			return new AtTheRate(point);
		} else if (tabList.getHyphen().equals(icon)) {
			return new Hyphen(point);
		} else if (tabList.getBars().equals(icon)) {
			return new DoubleBar(point);
		} else if (tabList.getPound().equals(icon)) {
			Icons pound = (Pound) new Pound(point);
			((Pound) pound).setTabIndex(tabList.getSize());
			return pound;
		}
		
		return null;
	}
}
