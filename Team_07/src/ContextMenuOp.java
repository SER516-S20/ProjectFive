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
				if(op.label == "||"){ //because "||" has multiple connections
					//TODO 
				}
				else{
					Database.selectedTab.dest.get(i).connected = false;
					Database.selectedTab.src.get(i).connected = false;
				}
				Database.selectedTab.dest.remove(i);
				Database.selectedTab.src.remove(i);
			}
		}
		PanelRightTab.refreshTab();

		// remove tab associated with the # operator 
		if(op.label == "#"){
			MainFrame.PANEL_RIGHT.remove(ListenersPanelRightTab.mapOP.get(opToDelete));
			ListenersPanelRightTab.mapOP.remove(opToDelete);
		}
		
		Database.selectedTab.remove(op);
		ContextMenuOp.opToDelete = null;
	}
}
