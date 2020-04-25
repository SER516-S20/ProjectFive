/**
 * Factory to create new connector
 *
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class FactoryConnector {
    static Connector connector = null;

    public static Connector getOp(String opName, Op op) {
        switch (opName) {
            case "Dot":
                connector = new ConnectorDot(op);
                break;
            case "Bar":
                connector = new ConnectorBar(op);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opName);
        }
        return connector;
    }
}
