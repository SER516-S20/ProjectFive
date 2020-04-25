import java.awt.*;

/**
 * Dot Connector
 *
 * @author Karandeep Singh Grewal
 * @since March 11, 2020
 */
public class ConnectorDot extends Connector {
    final Dimension DOT_DIMENSION = new Dimension(10, 10);

    ConnectorDot(Op op) {
        super(op);
        setMaximumSize(DOT_DIMENSION);
    }
}
