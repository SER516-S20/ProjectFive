import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	static Op opToDelete = null;

	public ContextMenuOp() {
		add(menuItemDelete);
		menuItemDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				deleteOp();
			}
		});
	}

	void deleteOp()
	{
		Op op = opToDelete;
		List<Connector> src = Database.selectedTab.src;
		List<Connector> dest = Database.selectedTab.dest;
		int opID = op.ID;
		int destSize = dest.size();

		System.out.println("delete op: " + op.label + " ID: " + op.ID);

		// removing connections 
		for (int i = destSize-1; i >= 0; i--){

			System.out.println("delete connection " + src.get(i).op.ID + " " + dest.get(i).op.ID);

			if(dest.get(i).op.ID == opID || src.get(i).op.ID == opID){

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
		if(op.label == "#"){
			PanelRightTab tabToRemove = ListenersPanelRightTab.mapOP.get(opToDelete);
			MainFrame.PANEL_RIGHT.remove(tabToRemove);
			ListenersPanelRightTab.mapOP.remove(opToDelete);
			ListenersInputPopup.mapTab.remove(tabToRemove);
		}

		Database.selectedTab.remove(op);
		ContextMenuOp.opToDelete = null;
	}
}
