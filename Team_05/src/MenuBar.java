import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * @author Kairui Hsu
 **/
public class MenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private FileBrowser fileBrowser;
	private FileManager fileManager;
	private RightTabbedPane rightPanel;
	private Model model;
	private static Hashtable<String, TabInfo> tabs;
	
	MenuBar(RightTabbedPane rightPanel) {
		model = new Model();
		this.rightPanel = rightPanel;
	}
	
	public JMenuBar createMenuBar() {
		fileBrowser = new FileBrowser();
		fileManager = new FileManager();
		JMenu fileMenu =  new JMenu("File");
		JMenuBar menuBar = new JMenuBar();
		JMenuItem itemNew = new JMenuItem("New");
		itemNew.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				if(fileBrowser.browser("New")) {
					fileManager.save(fileBrowser.getCurrentFile());
				}
			}
		});
		fileMenu.add(itemNew);
		fileMenu.addSeparator();
		JMenuItem itemOpen = new JMenuItem("Open");
		itemOpen.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(fileBrowser.browser("Open")) {
				fileManager.open(fileBrowser.getCurrentFile());
				}
			}
		});
		fileMenu.add(itemOpen);
		fileMenu.addSeparator();
		JMenuItem itemSave = new JMenuItem("Save");
		itemSave.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				if(fileBrowser.browser("Save")) {
					fileManager.save(fileBrowser.getCurrentFile());
				}
			}
		});
		fileMenu.add(itemSave);
		menuBar.add(fileMenu);
//		JButton NewSpace = new JButton(" New Space ");
//		NewSpace.setBorder(null);
//		NewSpace.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				Model.getRightTabbedPane().addWorkingAreaTab();
//			}
//		});
//		menuBar.add(NewSpace);
		JMenu projectMenu =  new JMenu("Project");
		JMenuItem Compiler = new JMenuItem("Compile");
		//...
		projectMenu.add(Compiler);
		projectMenu.addSeparator();
		JMenuItem Translate = new JMenuItem("Translate");
		//...
		projectMenu.add(Translate);
//		JButton Compiler = new JButton(" Compiler ");
//		Compiler.setBorder(null);
//		Compiler.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				Model.setMessage(" ");
//				tabs = Model.getTabs();
//				Thread myThreads[] = new Thread[tabs.size()];
//				int i = 0;
//				for(String key:tabs.keySet()) {
//				    myThreads[i] = new Thread(new Compilation(key));
//				    myThreads[i].start();
//					i++;
//				}
//		        try {
//					for(int j = 0 ; j < tabs.size();j++) {
//						myThreads[j].join(20000);
//					}
//		        } catch (InterruptedException t) {
//		            t.printStackTrace();
//		        }
//				String dialogMessage = Model.getMessage();
//				JOptionPane.showMessageDialog(null,dialogMessage);
//			}
//		});
//		menuBar.add(Compiler);
		menuBar.add(projectMenu);
		return menuBar;
	}
}