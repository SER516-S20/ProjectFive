import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;

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
    static Point shift;

    public static void addShapeListeners(Op op) {
        op.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                shift = e.getLocationOnScreen();
                shift.x -= op.getLocationOnScreen().x;
                shift.y -= op.getLocationOnScreen().y;
                draggableOp = op;
                try {
                    draggableOp = op.getClass().getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
                    exception.printStackTrace();
                }
                ListenersPanelRightTab.addShapeListeners(draggableOp);
                draggableOp.setLocation(-100,-100);
                Database.selectedTab.add(draggableOp);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point tabLocation = Database.selectedTab.getLocationOnScreen();
                if (draggableOp.getLocationOnScreen().x < tabLocation.x - shift.x
                        || draggableOp.getLocationOnScreen().y < tabLocation.y - shift.y) {
                    Database.selectedTab.remove(draggableOp);
                    PanelRightTab.refreshTab();
                } else if (draggableOp.getOpLabel().getText().equals("#")) {
                    ListenersPanelRightTab.mapOP.put(draggableOp,
                            MainFrame.PANEL_RIGHT.addNewTab());
                    ListenersInputPopup.mapTab.put(ListenersPanelRightTab.mapOP.get(draggableOp),
                            "Tab " + (PanelRight.tabNum - 1));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                op.setCursor(HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                op.setCursor(DEFAULT_CURSOR);
            }
        });

        op.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point tabLocation = Database.selectedTab.getLocationOnScreen();
                draggableOp.setLocation(
                        e.getXOnScreen() - tabLocation.x - shift.x,
                        e.getYOnScreen() - tabLocation.y - shift.y);
                PanelRightTab.refreshTab();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

}
