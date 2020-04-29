import javax.swing.*;
import java.awt.*;

/**
 * Right Tabbed Pane
 *
 * @author Karandeep Singh Grewal
 * @author Aditya Bajaj
 * @since March 10, 2020
 */
public class PanelRight extends JTabbedPane {
    public static int tabNum = 1;
    PanelRightTab tab;

    PanelRight() {
        super();
        setForeground(Color.WHITE);
        addNewTab();
        Database.selectedTab = (PanelRightTab) getSelectedComponent();
        addChangeListener(changeEvent -> Database.selectedTab = (PanelRightTab) getSelectedComponent());
    }

    public void addNewTab() {
        tab = new PanelRightTab();
        addTab("Tab " + tabNum, tab);
        tabNum++;
    }
    
    public PanelRightTab getRightTab() {
    	return Database.selectedTab;
    }
}
