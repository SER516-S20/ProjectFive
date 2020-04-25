import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Listeners for the PanelRightTab
 *
 * @author Karandeep Singh Grewal
 * @since March 11, 2020
 */
public class ListenersPanelRightTab {

    public static void addPanelListeners(JPanel rightPanel) {
        rightPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int mouseLocationX = mouseEvent.getX();
                int mouseLocationY = mouseEvent.getY();
                Op op = null;
                if (Database.selectedOp == null) return;
                try {
                    op = Database.selectedOp
                            .getClass().getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                assert op != null;
                op.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                op.setBounds(mouseLocationX - op.getWidth() / 2,
                        mouseLocationY - op.getHeight() / 2,
                        op.getWidth(),
                        op.getHeight());
                rightPanel.add(op);

                ListenersPanelRightTab.addShapeListeners(op);
                rightPanel.revalidate();
                rightPanel.repaint();
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

    public static void addShapeListeners(Op op) {
        final int[] dragX = new int[1];
        final int[] dragY = new int[1];
        op.addMouseListener(new MouseListener() {
            Cursor cursor;

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    new InputPopup(mouseEvent);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {


            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                cursor = new Cursor(Cursor.HAND_CURSOR);
                op.setCursor(cursor);

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                cursor = new Cursor(Cursor.HAND_CURSOR);
                op.setCursor(cursor);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                op.setCursor(cursor);
            }
        });
        op.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                Cursor cursor = new Cursor(Cursor.MOVE_CURSOR);
                op.setCursor(cursor);
                int mouseLocationX = e.getXOnScreen() + dragX[0];
                int mouseLocationY = e.getYOnScreen() + dragY[0];
                op.setLocation(mouseLocationX,
                        mouseLocationY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                dragX[0] = op.getX() - e.getXOnScreen();
                dragY[0] = op.getY() - e.getYOnScreen();
            }
        });
    }

    static void addAllListenersToTab(PaneRightTab tab) {
        ListenersPanelRightTab.addPanelListeners(tab);
        for (Component component :
                tab.getComponents()) {
            Op op = (Op) component;
            ListenersPanelRightTab.addShapeListeners(op);
            for (Component component1 : op.inputConnector.getComponents()) {
                if (component1 instanceof Connector) {
                    Connector.addConnectorListeners((Connector) component1);
                }
            }
            for (Component component1 : op.outputConnector.getComponents()) {
                if (component1 instanceof Connector) {
                    Connector.addConnectorListeners((Connector) component1);
                }
            }

        }
    }

}
