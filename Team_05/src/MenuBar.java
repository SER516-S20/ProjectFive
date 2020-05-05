import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
				int res=JOptionPane.showConfirmDialog(null, "Do you want to save these files? ", "", JOptionPane.YES_NO_CANCEL_OPTION);
				if(res==JOptionPane.YES_OPTION){ 
					if(fileBrowser.browser("Save file")) {
						fileManager.save(fileBrowser.getCurrentFile());
					}
					Model.clear();
				}else if(res==JOptionPane.NO_OPTION) {
					Model.clear();
				}
			}
		});
		fileMenu.add(itemNew);
		fileMenu.addSeparator();
		JMenuItem itemOpen = new JMenuItem("Open");
		itemOpen.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(fileBrowser.browser("Open file")) {
				fileManager.open(fileBrowser.getCurrentFile());
				}
			}
		});
		fileMenu.add(itemOpen);
		fileMenu.addSeparator();
		JMenuItem itemSave = new JMenuItem("Save");
		itemSave.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
				if(fileBrowser.browser("Save file")) {
					fileManager.save(fileBrowser.getCurrentFile());
				}
			}
		});
		fileMenu.add(itemSave);
		menuBar.add(fileMenu);
		JMenu projectMenu =  new JMenu("Project");
		JMenuItem Compiler = new JMenuItem("Compile");
		Compiler.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Model.setMessage(" ");
			tabs = Model.getTabs();
			Thread myThreads[] = new Thread[tabs.size()];
			int i = 0;
			for(String key:tabs.keySet()) {
			    myThreads[i] = new Thread(new Compilation(key));
			    myThreads[i].start();
				i++;
			}
	        try {
				for(int j = 0 ; j < tabs.size();j++) {
					myThreads[j].join(20000);
				}
	        } catch (InterruptedException t) {
	            t.printStackTrace();
	        }
			String dialogMessage = Model.getMessage();
			JOptionPane.showMessageDialog(null,dialogMessage);
		}
		});
		projectMenu.add(Compiler);
		projectMenu.addSeparator();
		JMenuItem Translate = new JMenuItem("Translate");
		Translate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Interpreter translate = new Interpreter();
                JTextArea text = new JTextArea(15, 60);
                text.setText(translate.getResult());
                text.setWrapStyleWord(true);
                text.setLineWrap(true);
                text.setCaretPosition(0);
                text.setEditable(false);
				JOptionPane.showMessageDialog(null, new JScrollPane(text),"Translate Results",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		projectMenu.add(Translate);
		menuBar.add(projectMenu);
		return menuBar;
	}
}