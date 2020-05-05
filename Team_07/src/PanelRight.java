import javax.swing.*;

/**
 * Right Tabbed Pane
 *
 * @author Aditya Bajaj
 * @author Karandeep Singh Grewal
 * @since April 29, 2020
 */
public class PanelRight extends JTabbedPane {
    public static int tabNum = 1;
    static PanelRightTab tab;

    PanelRight() {
        super();
        tab = new PanelRightTab();
        addTab("Tab " + tabNum, tab);
        tabNum++;

        Database.selectedTab = (PanelRightTab) getSelectedComponent();
        addChangeListener(changeEvent -> Database.selectedTab = (PanelRightTab) getSelectedComponent());
    }

    public PanelRightTab addNewTab() {
        tab = new PanelRightTab();
        addTab("Tab " + tabNum, tab);
        tabNum++;
        return tab;
    }

}
