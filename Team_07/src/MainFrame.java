import javax.swing.*;
import java.awt.*;

/**
 * Main Class
 *
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class MainFrame extends JFrame {
    final PanelTop panelTop = new PanelTop();
    final PanelLeft panelLeft = new PanelLeft();
    final static PaneRight PANE_RIGHT = new PaneRight();
    static MainFrame mainFrame = new MainFrame();

    public MainFrame() {
        super("Project Four - Team 7");
        setSize(1800, 1000);
        add(addMainPanel());
        setLocationRelativeTo(null);
    }

    private JPanel addMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(panelLeft, BorderLayout.WEST);
        mainPanel.add(PANE_RIGHT, BorderLayout.CENTER);
        mainPanel.setBackground(Color.WHITE);
        return mainPanel;
    }

    public static void main(String[] args) {
        mainFrame.setVisible(true);
    }


}
