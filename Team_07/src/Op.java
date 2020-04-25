import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Operator
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class Op extends JPanel implements Serializable {
    public int ID;
    final Dimension opDimen = new Dimension(100, 50);
    final int vMargins = 5, borderThickness = 2, //vMargin - vertical margin for opLabel
            vGap = vMargins - borderThickness, //vGap - gap between border and opLabel
            connectorWidth = 10;
    final Dimension opLabelDimen =
            new Dimension(opDimen.width - 2 * (connectorWidth + borderThickness),
                    opDimen.height - 2 * vMargins);
    Color color;
    final JLabel opLabel;
    public String label;
    JPanel inputConnector, outputConnector;
    private String value;

    Op() {
        setPreferredSize(opDimen);
        setBounds(0, 0, opDimen.width, opDimen.height);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, vGap));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, borderThickness));
        opLabel = getOpLabel();
        ID = Database.selectedTab.OpCount++ + 1;
    }

    JLabel getOpLabel() {
        JLabel operatorLabel = new JLabel(label);
        operatorLabel.setForeground(Color.WHITE);
        operatorLabel.setPreferredSize(opLabelDimen);
        operatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        operatorLabel.setVerticalAlignment(SwingConstants.CENTER);
        operatorLabel.setFont(new Font("Serif", Font.BOLD, 30));
        return operatorLabel;
    }


    JPanel getConnectorPanel() {
        JPanel connector = new JPanel();
        connector.setLayout(new BoxLayout(connector, BoxLayout.Y_AXIS));
        connector.setPreferredSize(new Dimension(connectorWidth, opLabelDimen.height));
        connector.setBackground(color);
        return connector;
    }

    void packOperator() {
        setBackground(color);
        inputConnector = getConnectorPanel();
        add(inputConnector);
        add(opLabel);
        outputConnector = getConnectorPanel();
        add(outputConnector);
    }

    void addToConnector(String connectorType, Connector connector) {
        if (connectorType.equals("I")) {
            inputConnector.add(Box.createVerticalGlue());
            inputConnector.add(connector);
            inputConnector.add(Box.createVerticalGlue());
        } else if (connectorType.equals("O")) {
            outputConnector.add(Box.createVerticalGlue());
            outputConnector.add(connector);
            outputConnector.add(Box.createVerticalGlue());
        } else
            System.out.println("Invalid connector type");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
