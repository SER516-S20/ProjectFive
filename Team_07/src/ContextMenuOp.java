import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

/**
 * Class for Context menu of Op
 *
 * @author Praveen Kumar
 * @since April 29, 2020
 */
public class ContextMenuOp extends JPopupMenu {

	JMenuItem menuItemDelete = new JMenuItem("delete");
	Op opToDelete = null;

	public ContextMenuOp() {
		add(menuItemDelete);
		menuItemDelete.addActionListener(actionEvent -> deleteOp());
	}
	
	void showContextMenu(MouseEvent mouseEvent){
		if(mouseEvent.isPopupTrigger()) {
			opToDelete = (Op) mouseEvent.getComponent();
			ListenersPanelRightTab.contextMenuOp.show(opToDelete, mouseEvent.getX(), mouseEvent.getY());
			PanelRightTab.refreshTab();
		}
	}
	
	void deleteOp()
	{
		List<Connector> src = Database.selectedTab.src;
		List<Connector> dest = Database.selectedTab.dest;
		int opID = opToDelete.ID;
		int destSize = dest.size();

		PanelLog.logString("delete op: " + opToDelete.label + " ID: " + opToDelete.ID, Color.GRAY);

		// removing connections 
		for (int i = destSize-1; i >= 0; i--){
			if(dest.get(i).op.ID == opID || src.get(i).op.ID == opID){
				PanelLog.logString("delete connection " + src.get(i).op.ID + " " + dest.get(i).op.ID, Color.GRAY);
				Database.selectedTab.dest.get(i).connected = false;
				Database.selectedTab.src.get(i).connected = false;
				Database.selectedTab.dest.remove(i);
				Database.selectedTab.src.remove(i);
			}
		}
		PanelRightTab.refreshTab();

		/* (to not break compiler) because "||" may have multiple connections and 
		 * status may be changed while removing some connections
		 */
		for(int i=0; i < Database.selectedTab.dest.size(); i++){
			Database.selectedTab.dest.get(i).connected = true;
			Database.selectedTab.src.get(i).connected = true;
		}
		//(to not break compiler)resetting the IDs
		for (Component component : Database.selectedTab.getComponents()){
			if (component instanceof Op)
				if (((Op) component).ID > opID)
					((Op) component).ID--;
		}
		Database.selectedTab.OpCount-- ;

		// remove tab associated with the # operator 
		if(opToDelete.label.equals("#")){
			PanelRightTab tabToRemove = ListenersPanelRightTab.mapOP.get(opToDelete);
			MainFrame.PANEL_RIGHT.remove(tabToRemove);
			ListenersPanelRightTab.mapOP.remove(opToDelete);
			ListenersInputPopup.mapTab.remove(tabToRemove);
		}

		Database.selectedTab.remove(opToDelete);
		opToDelete = null;
	}
}
