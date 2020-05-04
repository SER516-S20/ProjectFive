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
            public void mouseClicked(MouseEvent e) {
                if (src == null) {
                    src = (Connector) e.getSource();
                    op.setBackground(Color.BLACK);

                } else if (dest == null) {
                    dest = (Connector) e.getSource();
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
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                op.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //noinspection ObjectComparison
                if (src != (e.getSource()))
                    op.setBackground(Color.WHITE);
            }
        });
    }

}
