import javax.swing.JTabbedPane;

/**
 * @author Yijian Hu
 */
public class RightTabbedPane extends JTabbedPane{
	private static final long serialVersionUID = 1L;

	RightTabbedPane() {
		if(Model.getTabs().size()==0)
			addWorkingAreaTab();
		Model.setRightTabbedPane(this);
	}
	
	public boolean addWorkingAreaTab(RightPanel tab) {
		boolean added = false;
		if(tab.getName() == null) {
			added = false;
		}else if(!Model.getTabs().containsKey(tab.getName())) {
			Model.getTabs().put(tab.getName(), new TabInfo(tab));
			added = true;
			add(tab.getName(),tab);
			tab.init();
		}
		return added;
	}
	
	public void addWorkingAreaTab() {
		String init_name = "Tab";
		String name = init_name;
		int count = 1;
		while(Model.getTabs().containsKey(name)) {
			name = init_name + Integer.toString(count);
			count++;
		}
		RightPanel tab = new RightPanel(name);
		addWorkingAreaTab(tab);
	}
	
	public String getCurrentTabName() {
		return ((RightPanel)getSelectedComponent()).getName();
	}
	
	public RightPanel getCurrentTab() {
		return (RightPanel)getSelectedComponent();
	}
}