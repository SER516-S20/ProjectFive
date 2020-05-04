import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.TransferHandler;

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
        //super.exportDone(source, data, action);
        // Decide what to do after the drop has been accepted
    	/*
    	JFrame frame = (JFrame) source.getTopLevelAncestor();
    	System.out.println(source.getDropTarget().getClass().getName());
    	Component []comps = frame.getComponents();
    	for(int i = 0; i < comps.length; i++) {
    		if(comps[i] instanceof RightTabbedPane) {
    			//comps[i].getx
    		}
    	}
    	System.out.println(frame.getClass().getName());
    	System.out.println(source.getParent().getComponentCount() + ", " + source.getAlignmentY());
    	*/
        workArea.addButton(0, cmd, " ", 100, 100);
        System.out.println();
    }

}
