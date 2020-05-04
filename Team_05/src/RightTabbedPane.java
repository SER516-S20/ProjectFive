import javax.swing.JTabbedPane;

/**
 * @author Yijian Hu
 */
public class RightTabbedPane extends JTabbedPane{
	private static final long serialVersionUID = 1L;

	RightTabbedPane() {
		if(Model.getTabs().size()==0)
			addWorkingAreaTab("Main");
		Model.setRightTabbedPane(this);
	}
	
	public TabInfo addWorkingAreaTab(RightPanel tab) {
		TabInfo tabInfo = null;
		if(tab.getName() == null) {
			tabInfo = null;
		}else if(!Model.getTabs().containsKey(tab.getName())) {
			Model.getTabs().put(tab.getName(), new TabInfo(tab));
			tabInfo = Model.getTabs().get(tab.getName());
			add(tab.getName(),tab);
			tab.init();
		}
		return tabInfo;
	}
	
	public TabInfo addWorkingAreaTab(String tabName) {
		//String init_name = "Tab";
		//String name = init_name;
		//int count = 1;
		//while(Model.getTabs().containsKey(name)) {
		//	name = init_name + Integer.toString(count);
		//	count++;
		//}
		RightPanel tab = new RightPanel(tabName);
		return addWorkingAreaTab(tab);
	}
	
	public String getCurrentTabName() {
		return ((RightPanel)getSelectedComponent()).getName();
	}
	
	public RightPanel getCurrentTab() {
		return (RightPanel)getSelectedComponent();
	}
	
	public boolean renameTab(String name, String newName)
	{
		boolean renamed = false;
		if(!Model.getTabs().containsKey(name) || Model.getTabs().containsKey(newName))
		{
			renamed = false;
		}
		else
		{
			TabInfo tabInfo = Model.getTabs().get(name);
			Model.getTabs().remove(name);
			tabInfo.setName(newName);
			Model.getTabs().put(newName, tabInfo);
			int index = indexOfComponent(tabInfo.getTab());
			setTitleAt(index, newName);
			renamed = true;
		}
		return renamed;
	}
}