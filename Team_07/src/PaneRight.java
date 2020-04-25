import javax.swing.*;
import java.awt.*;

/**
 * Right Tabbed Pane
 *
 * @author Karandeep Singh Grewal
 * @author Aditya Bajaj
 * @since March 10, 2020
 */
public class PaneRight extends JTabbedPane {
    public static int tabNum = 1;
    PaneRightTab tab;

    PaneRight() {
        super();
        setForeground(Color.WHITE);
        addNewTab();
        Database.selectedTab = (PaneRightTab) getSelectedComponent();
        addChangeListener(changeEvent -> Database.selectedTab = (PaneRightTab) getSelectedComponent());
    }

    public void addNewTab() {
        tab = new PaneRightTab();
        addTab("Tab " + tabNum, tab);
        tabNum++;
    }
}
