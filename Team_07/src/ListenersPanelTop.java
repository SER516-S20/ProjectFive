import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listeners for the PanelTop
 *
 * @author Karandeep Singh Grewal
 * @since March 14, 2020
 */
public class ListenersPanelTop {
    public static void addPanelTopListeners(ButtonCustom buttonCustom, String buttonType) {
        buttonCustom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if ((buttonType.equals("Open")))
                    FileManager.open();
                if (buttonType.equals("Save"))
                    FileManager.save();
                if (buttonType.equals("NewTab"))
                    MainFrame.PANE_RIGHT.addNewTab();
                if (buttonType.equals("Compile")) {
                    Compiler compiler = new Compiler();
                    PanelTop.compileMessage.setText(compiler.compile());
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

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }
}
