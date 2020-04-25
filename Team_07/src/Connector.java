import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Connector for the operators
 *
 * @author Karandeep Singh Grewal
 * @author Praveen Kumar P
 * @since March 10, 2020
 */
public abstract class Connector extends JPanel {
    static Connector src, dest;
    Boolean connected = false;
    Op op;

    Connector(Op op) {
        setBackground(Color.WHITE);
        this.op = op;
        addConnectorListeners(this);
    }

    public static void addConnectorListeners(Connector op) {
        op.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (src == null) {
                    src = (Connector) mouseEvent.getSource();
                    op.setBackground(Color.BLACK);

                } else if (dest == null) {
                    dest = (Connector) mouseEvent.getSource();
                    Boolean canSourceConnect = Database.isSourceValid(src);
                    Boolean canDestConnect = Database.isDestValid(dest);
                    if (canSourceConnect && canDestConnect) {
                        new Connection(src, dest);
                        src.connected = true;
                        dest.connected = true;
                    }
                    src.setBackground(Color.WHITE);
                    src = dest = null;
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                op.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                //noinspection ObjectComparison
                if (src != (mouseEvent.getSource()))
                    op.setBackground(Color.WHITE);
            }
        });
    }

}
