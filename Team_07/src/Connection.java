import java.awt.geom.GeneralPath;

/**
 * Line connections between two operators
 *
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class Connection {

    Connection(Connector src, Connector dest) {
        PanelRightTab panelRightTab = Database.selectedTab;
        panelRightTab.src.add(src);
        panelRightTab.dest.add(dest);
    }

    /**
     * Creates a path from source to destination
     * Used to draw a polyline from source to destination instead of a straight line
     * @return Two dimensional array of the form [xCoordinates, yCoordinates]
     */
    public static int[][] getLinePoints(int xSource, int ySource, int xDest, int yDest) {
        int[][] points;
        int totalPoints;
        int xMin = 20; //Minimum length line on the left of destination operator

        //Max Vertical Distance between src and dest operator when the general path is switched
        //from "between the two operators" to "over/under the two operators"
        //(When destination operator is on the left of source operator)
        int yMax = 60;

        //When destination operator is on the left of the source operator
        if (xDest - xSource > xMin * 2) {
            totalPoints = 4;
            points = new int[2][4];
            points[0][1] = (xSource + xDest) / 2;
            points[1][1] = ySource;
            points[0][2] = (xSource + xDest) / 2;
            points[1][2] = yDest;
        }

        //When destination operator is on the right of the source operator
        else {
            points = new int[2][6];
            totalPoints = 6;
            int yOffset = 0;
            if (yDest - ySource <= yMax && yDest >= ySource) {
                yOffset = yMax;
            } else if (ySource - yDest < yMax && ySource > yDest) {
                yOffset = -yMax;
            }
            points[0][1] = (xSource + xMin);
            points[1][1] = ySource;
            points[0][2] = (xSource + xMin);
            points[1][2] = (ySource + yDest) / 2 + yOffset;
            points[0][3] = xDest - xMin;
            points[1][3] = (ySource + yDest) / 2 + yOffset;
            points[0][4] = xDest - xMin;
            points[1][4] = yDest;

        }

        //Source Point
        points[0][0] = xSource;
        points[1][0] = ySource;

        //Destination Point
        points[0][totalPoints - 1] = xDest;
        points[1][totalPoints - 1] = yDest;

        return points;
    }

    public static GeneralPath getGeneralPath(int xSource, int ySource, int xDest, int yDest) {
        int[] xPoints = {10, 50, 100, 150, 200, 250, 300, 350};
        int[] yPoints = {10, 50, 10, 50, 10, 50, 10, 50};
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                3);
        int[][] points = getLinePoints(xSource, ySource, xDest, yDest);
        path.moveTo(points[0][0], points[1][0]);
        for (int i = 0; i < points[0].length - 1; i++)
            path.lineTo(points[0][i + 1], points[1][i + 1]);
        for (int i = points[0].length-1; i >0; i--)
            path.lineTo(points[0][i -1], points[1][i-1]);
        path.closePath();
        return path;
    }
}
