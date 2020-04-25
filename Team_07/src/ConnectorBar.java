import java.awt.*;

/**
 * Bar Connector
 *
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class ConnectorBar extends Connector {
    final Dimension BAR_DIMENSION = new Dimension(10, 30);

    ConnectorBar(Op op) {
        super(op);
        setPreferredSize(BAR_DIMENSION);
        setMaximumSize(BAR_DIMENSION);

    }
}
