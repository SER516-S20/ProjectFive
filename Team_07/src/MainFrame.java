import javax.swing.*;
import java.awt.*;

/**
 * Main Class
 *
 * @author Aditya Bajaj
 * @author Karandeep Singh Grewal
 * @since April 26, 2020
 */

public class MainFrame extends JFrame {
    final static PanelRight PANEL_RIGHT = new PanelRight();
    static MainFrame mainFrame = new MainFrame();
    final ToolbarTop TOOLBAR_TOP = new ToolbarTop();
    final PanelLeft PANEL_LEFT = new PanelLeft();
    final PanelLog PANEL_LOG = new PanelLog();
    final MenuBar MENU_BAR = new MenuBar();

    public MainFrame() {
        super("Project Five - Team 7");
        setSize(1000,1000);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        JScrollPane TOOLBAR_TOP_SCROLLPANE = wrapToScrollPane(TOOLBAR_TOP);
        TOOLBAR_TOP_SCROLLPANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        mainPanel.add(TOOLBAR_TOP_SCROLLPANE, BorderLayout.NORTH);

        JScrollPane PANEL_LEFT_SCROLLPANE = wrapToScrollPane(PANEL_LEFT);
        PANEL_LEFT_SCROLLPANE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(PANEL_LEFT_SCROLLPANE, BorderLayout.WEST);

        JScrollPane PANEL_RIGHT_SCROLLPANE = wrapToScrollPane(PANEL_RIGHT);
        TOOLBAR_TOP_SCROLLPANE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        mainPanel.add(PANEL_RIGHT_SCROLLPANE, BorderLayout.CENTER);
        
        mainPanel.add(PANEL_LOG.PANEL_LOG_SCROLLPANE,BorderLayout.SOUTH);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(MENU_BAR.menu);
    }

    public static void main(String[] args) {
        mainFrame.setVisible(true);
        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public JScrollPane wrapToScrollPane(Component panel){
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setPreferredSize(panel.getPreferredSize());
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        jScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        return jScrollPane;
    }
}
