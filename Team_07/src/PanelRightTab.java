import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * RightPane Tab
 *
 * @author Aditya Bajaj
 * @author Karandeep Singh Grewal
 * @since April 29, 2020
 */
public class PanelRightTab extends JPanel implements Serializable {
    public List<Connector> src = new ArrayList<>();
    public List<Connector> dest = new ArrayList<>();
    public int OpCount;

    PanelRightTab() {
        super();
        setLayout(null);
        setPreferredSize(new Dimension(Database.SCREEN_SIZE.width,Database.SCREEN_SIZE.height));
        setBackground(Color.DARK_GRAY);
        ListenersPanelRightTab.addRightPanelTabListeners(this);
        ListenersPanelRightTab.addRightPanelTabMotionListeners(this);
    }

    static public void refreshTab() {
        Database.selectedTab.repaint();
        Database.selectedTab.revalidate();
    }

    static public List<Connector> getDestConnectors(){
        List<Connector> connectors = new ArrayList<>();
        for (Component component: Database.selectedTab.getComponents()
             ) {
            if(component instanceof Op){
                JPanel connectorPanel = ((Op) component).inputConnector;
                for (Component connector: connectorPanel.getComponents()
                     ) {
                    if(connector instanceof Connector)
                        connectors.add((Connector) connector);
                }
            }
        }
        return connectors;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Stroke stroke = new BasicStroke(1f);
        graphics2D.setStroke(stroke);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
        for (int i = 0; i < src.size(); i++) {
            Point panelLocation = getLocationOnScreen();
            Connector srcConnector = src.get(i), destConnector = dest.get(i);
            int x1 =
                    srcConnector.getLocationOnScreen().x - panelLocation.x + srcConnector.getWidth();
            int y1 =
                    srcConnector.getLocationOnScreen().y - panelLocation.y + srcConnector.getHeight() / 2;
            int x2 =
                    destConnector.getLocationOnScreen().x - panelLocation.x - 2;
            int y2 =
                    destConnector.getLocationOnScreen().y - panelLocation.y + destConnector.getHeight() / 2;
            int[] arrowX = new int[]{x2, x2 - 10, x2 - 10};
            int[] arrowY = new int[]{y2, y2 - 10, y2 + 10};
            graphics2D.fillPolygon(arrowX, arrowY, 3);
            graphics2D.draw(Connection.getGeneralPath(x1,y1,x2,y2));
        }
    }
}
