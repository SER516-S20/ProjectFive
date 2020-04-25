import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Listeners for the InputPopup
 *
 * @author Karandeep Singh Grewal
 * @since March 11, 2020
 */
public class ListenersInputPopup {
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
}
