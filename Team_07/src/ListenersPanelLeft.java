import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listeners for the PanelLeft
 *
 * @author Karandeep Singh Grewal
 * @since March 11, 2020
 */
public class ListenersPanelLeft {
    public static void addShapeListeners(Op op) {
        op.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (Database.selectedOp != null) {
                    Database.selectedOp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }
                Database.selectedOp = (Op) mouseEvent.getSource();
                Database.selectedOp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                op.setCursor(Database.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                op.setCursor(Database.DEFAULT_CURSOR);
            }
        });
    }

}
