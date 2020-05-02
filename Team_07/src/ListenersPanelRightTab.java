import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Listeners for the PanelRightTab
 *
 * @author Aditya Bajaj
 * @author Karandeep Singh Grewal
 * @since April 29, 2020
 */
public class ListenersPanelRightTab {
    public static HashMap<Op, PanelRightTab> mapOP = new HashMap<>();
    static Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    static Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    static int currentConnection;
    private static boolean panelRightTabAllowance = true;

    public static void addRightPanelTabListeners(JPanel rightPanel) {
        rightPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (!panelRightTabAllowance) {
                    if (mouseEvent.getButton() == 3) {
                        Database.selectedTab.src.get(currentConnection).connected = false;
                        Database.selectedTab.dest.get(currentConnection).connected = false;
                        Database.selectedTab.src.remove(currentConnection);
                        Database.selectedTab.dest.remove(currentConnection);
                        PanelRightTab.refreshTab();
                    }
                    return;
                }
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
                op.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                op.setBounds(mouseLocationX - op.getWidth() / 2,
                        mouseLocationY - op.getHeight() / 2,
                        op.getWidth(),
                        op.getHeight());
                rightPanel.add(op);

                ListenersPanelRightTab.addShapeListeners(op);
                if (op.getOpLabel().getText().equals("#")) {
                    mapOP.put(op, MainFrame.PANEL_RIGHT.addNewTab());
                    ListenersInputPopup.mapTab.put(mapOP.get(op),"Tab " + (PanelRight.tabNum-1));
                }
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

    public static void addRightPanelTabMotionListeners(JPanel rightPanel) {
        rightPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                for (int i = 0; i < Database.selectedTab.src.size(); i++) {
                    Connector srcConnector = Database.selectedTab.src.get(i), destConnector = Database.selectedTab.dest.get(i);
                    Point panelLocation = rightPanel.getLocationOnScreen();
                    Point srcLocation = srcConnector.getLocationOnScreen();
                    Point destLocation = destConnector.getLocationOnScreen();
                    int x1 =
                            srcLocation.x - panelLocation.x + srcConnector.getWidth();
                    int y1 =
                            srcLocation.y - panelLocation.y + srcConnector.getHeight() / 2;
                    int x2 =
                            destLocation.x - panelLocation.x - 2;
                    int y2 =
                            destLocation.y - panelLocation.y + destConnector.getHeight() / 2;
                    GeneralPath generalPath = Connection.getGeneralPath(x1, y1, x2, y2);
                    if(generalPath.intersects(mouseEvent.getXOnScreen() - panelLocation.x,
                            mouseEvent.getYOnScreen() - panelLocation.y, 20, 20)) {
                        rightPanel.setCursor(HAND_CURSOR);
                        panelRightTabAllowance = false;
                        currentConnection = i;
                        break;
                    }
                    else{
                        panelRightTabAllowance=true;
                        rightPanel.setCursor(DEFAULT_CURSOR);
                    }

                }

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
                if(mouseEvent.isPopupTrigger()) {
                    ContextMenuOp.opToDelete = op;
                    PanelRightTab.opContextMenu.show(op, mouseEvent.getX(), mouseEvent.getY());
                }
                PanelRightTab.refreshTab();
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
                PanelRightTab.refreshTab();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                dragX[0] = op.getX() - e.getXOnScreen();
                dragY[0] = op.getY() - e.getYOnScreen();
                PanelRightTab.refreshTab();
            }
        });
    }

    static void addListenersToPanelOps(PanelRightTab tab) {
        ListenersPanelRightTab.addRightPanelTabMotionListeners(tab);
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
