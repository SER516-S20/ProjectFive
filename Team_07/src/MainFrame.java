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
    final ToolbarTop toolbarTop = new ToolbarTop();
    final PanelLeft panelLeft = new PanelLeft();
    final static PaneRight PANE_RIGHT = new PaneRight();
    static MainFrame mainFrame = new MainFrame();
    final MenuBar menuBar = new MenuBar();

    public MainFrame() {
        super("Project Five - Team 7");
        setSize(1800, 1000);
        add(addScrollPane());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar.menu);

    }

    private JScrollPane addScrollPane() {
        JScrollPane scrollBarPane;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(toolbarTop, BorderLayout.NORTH);
        mainPanel.add(panelLeft, BorderLayout.WEST);
        mainPanel.add(PANE_RIGHT, BorderLayout.CENTER);
        mainPanel.setBackground(Color.WHITE);
        scrollBarPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scrollBarPane;
    }

    public static void main(String[] args) {
        mainFrame.setVisible(true);
    }


}
