import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.*;

/**
 * Listeners for the PanelLeft
 *
 * @author Aravind Thillai Villalan
 * @author Karandeep Singh Grewal
 * @since March 11, 2020
 */
public class ListenersPanelLeft {
    static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    static Op draggableOp = null;
    static boolean isOpCreated = false;

    public static void addShapeListeners(Op op) {
        final int[] dragX = new int[1];
        final int[] dragY = new int[1];
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
                isOpCreated = false;
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                op.setCursor(HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                op.setCursor(DEFAULT_CURSOR);


            }
        });

        op.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                Database.selectedOp = (Op) e.getSource();
                try {
                    if (!isOpCreated) {
                        draggableOp = Database.selectedOp
                                .getClass().getDeclaredConstructor().newInstance();
                        isOpCreated = true;
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exp) {
                    exp.printStackTrace();
                }


                Cursor cursor = new Cursor(Cursor.MOVE_CURSOR);
                draggableOp.setCursor(cursor);
                int mouseLocationX = e.getXOnScreen() + dragX[0];
                int mouseLocationY = e.getYOnScreen() + dragY[0];
                draggableOp.setLocation(mouseLocationX,
                        mouseLocationY);

                if (isOpCreated) {
                    MainFrame.PANEL_RIGHT.getRightTab().add(draggableOp);
                }
                MainFrame.PANEL_RIGHT.getRightTab().repaint();

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                dragX[0] = op.getX() - e.getXOnScreen();
                dragY[0] = op.getY() - e.getYOnScreen();

            }

        });
    }

}
