import javax.swing.*;
import java.awt.*;

/**
 * Top Panel
 * Contains the "Open", "Save", "New Tab" and "Compile" buttons
 *
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class PanelTop extends JPanel {
    final ButtonCustom openButton = new ButtonCustom("Open", Database.THEME_BLUE_DARK);
    final ButtonCustom saveButton = new ButtonCustom("Save", Database.THEME_BLUE_DARK);
    final ButtonCustom newTabButton = new ButtonCustom("New Tab", Database.THEME_BLUE_DARK);
    final ButtonCustom compileButton = new ButtonCustom("Compile", Database.THEME_BLUE_DARK);
    final Dimension DIMENSIONS_PANEL_TOP = new Dimension(Database.SCREEN_SIZE.width, 100);
    static JLabel compileMessage = new JLabel();

    PanelTop() {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT, 40, 30));
        setPreferredSize(DIMENSIONS_PANEL_TOP);
        setBackground(Database.THEME_BLUE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.WHITE));
        add(openButton);
        add(saveButton);
        add(newTabButton);
        add(compileButton);
        add(compileMessage);
        compileMessage.setSize(new Dimension(300, 100));
        compileMessage.setForeground(Color.WHITE);
        ListenersPanelTop.addPanelTopListeners(openButton, "Open");
        ListenersPanelTop.addPanelTopListeners(saveButton, "Save");
        ListenersPanelTop.addPanelTopListeners(newTabButton, "NewTab");
        ListenersPanelTop.addPanelTopListeners(compileButton, "Compile");
    }
}
