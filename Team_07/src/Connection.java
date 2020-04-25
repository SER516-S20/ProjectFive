/**
 * Line connections between two operators
 *
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class Connection {

    Connection(Connector src, Connector dest) {
        PaneRightTab paneRightTab = Database.selectedTab;
        paneRightTab.src.add(src);
        paneRightTab.dest.add(dest);
    }

    /**
     * Creates a path from source to destination
     * Used to draw a polyline from source to destination instead of a straight line
     *
     * @param xSource X Coordinate of the source connector
     * @param ySource Y Coordinate of the source connector
     * @param xDest   X Coordinate of the destination connector
     * @param yDest   Y Coordinate of the destination connector
     * @return Two dimensional array of the form [xCoordinates, yCoordinates]
     */
    public static int[][] getLinePoints(int xSource, int ySource, int xDest, int yDest) {
        int[][] points;
        int totalPoints;

        //When destination operator is on the left of the source operator
        if (xDest - xSource > 20) {
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
            if (yDest - ySource <= 60 && yDest >= ySource) {
                yOffset = 70;
            } else if (ySource - yDest < 60 && ySource > yDest) {
                yOffset = -70;
            }
            points[0][1] = (xSource + 20);
            points[1][1] = ySource;
            points[0][2] = (xSource + 20);
            points[1][2] = (ySource + yDest) / 2 + yOffset;
            points[0][3] = xDest - 20;
            points[1][3] = (ySource + yDest) / 2 + yOffset;
            points[0][4] = (xDest - 20);
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
}
