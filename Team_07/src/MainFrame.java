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
    final MenuBar MENU_BAR = new MenuBar();

    public MainFrame() {
        super("Project Five - Team 7");
        setSize(1800, 1000);
        add(addScrollPane());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(MENU_BAR.menu);

    }

    public static void main(String[] args) {
        mainFrame.setVisible(true);
        mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private JScrollPane addScrollPane() {
        JScrollPane scrollBarPane;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(TOOLBAR_TOP, BorderLayout.NORTH);
        mainPanel.add(PANEL_LEFT, BorderLayout.WEST);
        mainPanel.add(PANEL_RIGHT, BorderLayout.CENTER);
        mainPanel.setBackground(Color.WHITE);
        scrollBarPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scrollBarPane;
    }
}
