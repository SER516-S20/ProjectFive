package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import view.Workspace;

/**
 * This is a singleton class that contains a list of all the tabs in the
 * workspace.
 * 
 * @author Raghavan
 * @version 1.0
 */
public class TabList implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentTabIndex;
	private List<Tab> tabList;
	private static TabList tabListInstance;

	private TabList() {
		currentTabIndex = 0;
		tabList = new ArrayList<Tab>();
	}

	/**
	 * Returns the singleton instance of the TabList class.
	 */
	public static TabList getInstance() {
		if (tabListInstance == null) {
			tabListInstance = new TabList();
		}
		return tabListInstance;
	}

	public Tab getTab() {
		return tabList.get(currentTabIndex);
	}

	public Tab getTab(int i) {
		return tabList.get(i);
	}

	public Tab getRecentTab() {
		return tabList.get(getSize() - 1);
	}

	public void addTab(Workspace workspace) {
		tabList.add(new Tab(workspace));
	}

	public int getCurrentTabIndex() {
		return currentTabIndex;
	}

	public void setCurrentTabIndex(int currentTabIndex) {
		this.currentTabIndex = currentTabIndex;
	}

	public int getSize() {
		return tabList.size();
	}

	public List<Tab> getTabList() {
		return tabList;
	}

	public void setTabList(List<Tab> tabList) {
		this.tabList = tabList;
	}

	private final String OPENBRACKET = "OPEN_BRACKET";

	private final String CLOSEBRACKET = "CLOSE_BRACKET";

	private final String LESSTHAN = "LESS_THAN";

	private final String GREATERTHAN = "GREATER_THAN";

	private final String ATTHERATE = "AT_THE_RATE";

	private final String HYPHEN = "HYPHEN";

	private final String BARS = "BARS";

	private final String POUND = "POUND";

	public String getOpenBracket() {
		return OPENBRACKET;
	}

	public String getCloseBracket() {
		return CLOSEBRACKET;
	}

	public String getLessThan() {
		return LESSTHAN;
	}

	public String getGreaterThan() {
		return GREATERTHAN;
	}

	public String getAtTheRate() {
		return ATTHERATE;
	}

	public String getHyphen() {
		return HYPHEN;
	}

	public String getBars() {
		return BARS;
	}

	public String getPound() {
		return POUND;
	}
}
