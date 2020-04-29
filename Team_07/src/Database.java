import java.awt.*;

/**
 * Common Database to store project wide variables
 *
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class Database {
    final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    final static Color GRAY = new Color(78, 108, 162);
    final static Color LIGHT_GRAY = new Color(49, 72, 121);
    static Op selectedOp;
    static PanelRightTab selectedTab = null;

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
