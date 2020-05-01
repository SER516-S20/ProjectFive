import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * RightPane Tab
 *
 * @author Aditya Bajaj
 * @author Karandeep Singh Grewal
 * @since April 30, 2020
 *
 */
public class PanelRightTab extends JPanel implements Serializable {
    public List<Connector> src = new ArrayList<>();
    public List<Connector> dest = new ArrayList<>();
    public int OpCount;
    GeneralPath polyline;

    PanelRightTab() {
        super();
        setLayout(null);
        setBackground(Color.GRAY);
        ListenersPanelRightTab.addPanelListeners(this);
    }

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
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
            int[][] points = Connection.getLinePoints(x1, y1, x2, y2);
            graphics2D.drawPolyline(points[0], points[1], points[0].length);
        }
    }

    static public void refreshTab(){
        Database.selectedTab.repaint();
        Database.selectedTab.revalidate();
    }
}
