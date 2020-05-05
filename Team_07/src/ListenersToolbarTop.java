import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listeners for the ToolbarTop
 *
 * @author Aditya Bajaj
 * @since April 26, 2020
 *
 */
public class ListenersToolbarTop {
    final static Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    public static void addShapeListeners(Op op) {
        op.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Database.selectedOp != null) {
                    Database.selectedOp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                }
                Database.selectedOp = (Op) e.getSource();
                Database.selectedOp.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                op.setCursor(DEFAULT_CURSOR);
            }
        });

    }

}
