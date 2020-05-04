import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


/**
 * @author ShihYu Chang
 */

public class ConnectionController implements MouseListener {
	private static Connection tempconnection;
	private List<Connection> connections;
	private static int changeid = 0;
	public ConnectionController(ButtonBox buttonBox) {
		connections =Model.getTabs().get(Model.getRightTabbedPane().getCurrentTabName()).getConnectionCollection();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		ButtonBox obj = (ButtonBox)e.getComponent().getParent();
		int width = e.getComponent().getParent().getWidth()/2;
		int buttonx = e.getComponent().getLocation().x;
		int tempx = e.getComponent().getParent().getLocation().x + e.getComponent().getLocation().x+5;
		int tempy = e.getComponent().getParent().getLocation().y + e.getComponent().getLocation().y+5;
		boolean selected = false;
		
		if(buttonx > width) {
			if(!obj.getClass().toString().equalsIgnoreCase("class BarButtonBox")) {
				for(int i=0; i < connections.size(); i++) {
					Connection c = connections.get(i);
					if(tempx == c.getSourceX() && tempy == c.getSourceY()) {
						selected = true;
						tempconnection = null;
					}
				}
			}
			if(!selected) {
				if(changeid != 0) {
					for(int i = 0; i < this.connections.size(); i++) {
			        	Connection finishedconnection = connections.get(i);
						if(finishedconnection.getSourceButton() == changeid) {
							finishedconnection.setSourceX(tempx);
							finishedconnection.setSourceY(tempy);
							finishedconnection.setSourceButton(obj.getId());
						}
			        }
					e.getComponent().getParent().getParent().repaint();
					changeid = 0;
				}
				else {
					if(tempconnection == null) {
						tempconnection = new Connection();
						tempconnection.setSourceX(tempx);
						tempconnection.setSourceY(tempy);
						tempconnection.setSourceButton(obj.getId());
					}else if(tempconnection != null && tempconnection.getSourceButton() == null && tempconnection.getDestButton() != obj.getId()){		
						tempconnection.setSourceX(tempx);
						tempconnection.setSourceY(tempy);
						tempconnection.setSourceButton(obj.getId());
					}else {
						tempconnection = null;
					}
				}
			}
			else {
				changeid = obj.getId();
			}
		}else {
			if(!obj.getClass().toString().equalsIgnoreCase("class BarButtonBox")) {
				for(int i=0; i < connections.size(); i++) {
					Connection c = connections.get(i);
					if(tempx == c.getDestX() && tempy == c.getDestY()) {
						selected = true;
						tempconnection = null;
					}
				}
			}
			if(!selected) {
				if(changeid != 0) {
					for(int i = 0; i < this.connections.size(); i++) {
			        	Connection finishedconnection = connections.get(i);
						if(finishedconnection.getDestButton() == changeid) {
							finishedconnection.setDestX(tempx);
							finishedconnection.setDestY(tempy);
							finishedconnection.setDestButton(obj.getId());
						}
			        }
					e.getComponent().getParent().getParent().repaint();
					changeid = 0;
				}
				else {
					if(tempconnection == null) {
						tempconnection = new Connection();
						tempconnection.setDestX(tempx);
						tempconnection.setDestY(tempy);
						tempconnection.setDestButton(obj.getId());
					}else if(tempconnection != null && tempconnection.getDestButton() == null && tempconnection.getSourceButton() != obj.getId()){
						tempconnection.setDestX(tempx);
						tempconnection.setDestY(tempy);
						tempconnection.setDestButton(obj.getId());
					}else {
						tempconnection = null;
					}
				}
			}
			else {
				changeid = obj.getId();
			}
		}
		if(tempconnection != null && tempconnection.getSourceButton() != null && tempconnection.getDestButton() != null) {
			Model.getTabs().get(Model.getRightTabbedPane().getCurrentTabName()).addConnection(tempconnection);
			tempconnection = null;
			e.getComponent().getParent().getParent().repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
