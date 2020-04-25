import java.awt.*;

/**
 * Common Database to store project wide variables
 *
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class Database {
    final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    final static Color THEME_BLUE = new Color(78, 108, 162);
    final static Color THEME_BLUE_DARK = new Color(49, 72, 121);
    static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    static Op selectedOp;
    static PaneRightTab selectedTab = null;

    /**
     * @return True if the connector is source connector (output)
     */
    public static boolean isSourceValid(Connector source) {
        if (source.getParent().getX() > 50) {
            if (source instanceof ConnectorBar)
                return true;
            return !selectedTab.src.contains(source);
        }
        return false;
    }

    /**
     * @return True if the connector is destination connector (input)
     */
    public static boolean isDestValid(Connector destination) {
        if (destination.getParent().getX() < 50) {
            if (destination instanceof ConnectorBar)
                return true;
            return !selectedTab.dest.contains(destination);
        }
        return false;
    }

}
