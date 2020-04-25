import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class for the custom button
 *
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class ButtonCustom extends JPanel {


    Color buttonColor;
    final MouseListener BUTTON_CLICK_LISTENERS = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            setBackground(buttonColor.darker());
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            setBackground(buttonColor);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            setCursor(Database.HAND_CURSOR);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            setCursor(Database.HAND_CURSOR);
        }
    };
    JLabel buttonLabel;

    ButtonCustom(String buttonLabel, Color color) {
        setPreferredSize(new Dimension(100, 40));
        setBackground(color);
        buttonColor = color;
        setLayout(new GridBagLayout());
        addMouseListener(BUTTON_CLICK_LISTENERS);
        add(getButtonLabel(buttonLabel));
    }

    private JLabel getButtonLabel(String label) {
        buttonLabel = new JLabel(label);
        buttonLabel.setForeground(new Color(240, 240, 250));
        return buttonLabel;
    }
}
