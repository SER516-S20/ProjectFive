import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
/**
 * @author Hongqi Zhang
 */
public class ComponentTransferHandler extends TransferHandler {
	public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
	private static final long serialVersionUID = 1L;
	private	RightPanel workArea;
	private String cmd;
	
	public ComponentTransferHandler(String cmd, RightPanel workArea) {
		this.cmd = cmd;
		this.workArea = workArea;
	}
    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = new StringSelection(cmd);
        return t;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
        Random rand = new Random();
        int difference = 100;
        int height = workArea.getHeight() /2;
        int width = workArea.getWidth() / 2;
        System.out.println(width + "===" + height);
        int xPos = rand.nextInt(width - difference) + difference;
        int yPos = rand.nextInt(height - difference) + difference;
        workArea.addButton(0, cmd, " ", xPos, yPos);
        System.out.println();
    }

}
