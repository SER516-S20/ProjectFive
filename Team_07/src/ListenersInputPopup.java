import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.HashMap;

/**
 * Listeners for the InputPopup
 *
 * @author Aditya Bajaj
 * @author Karandeep Singh Grewal
 * @since April 30, 2020
 */
public class ListenersInputPopup {

    public static HashMap< PanelRightTab,String> mapTab = new HashMap<>();

    static void addCancelButtonListeners(ButtonCustom buttonCustom, InputPopup popup) {
        buttonCustom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                popup.dispose();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    static void addCloseOnLostFocusListeners(InputPopup popup) {
        popup.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent windowEvent) {

            }

            @Override
            public void windowLostFocus(WindowEvent windowEvent) {
                popup.dispose();
            }
        });
    }

    static void addDoneButtonListeners(ButtonCustom buttonCustom, InputPopup popup) {
        buttonCustom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                popup.op.setValue(popup.name.getText());
                popup.dispose();
                setTabTitle(ListenersPanelRightTab.mapOP.get(popup.op), popup.name.getText());
                mapTab.replace(ListenersPanelRightTab.mapOP.get(popup.op),popup.name.getText());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });
    }

    public static void setTabTitle(JPanel tab, String title) {
        JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, tab);

        for (int tabIndex = 0; tabIndex < tabbedPane.getTabCount(); tabIndex++) {
            if (SwingUtilities.isDescendingFrom(tab, tabbedPane.getComponentAt(tabIndex))) {
                tabbedPane.setTitleAt(tabIndex, title);
                break;
            }
        }
    }
}
