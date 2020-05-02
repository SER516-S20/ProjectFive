import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author Yijian Hu
 */
public class FileBrowser {
	private JFrame parentFrame;
	private JFileChooser fileChooser;
	private File currentFile;
	
	public boolean browser(String title){
		parentFrame = new JFrame();
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		int selection;
		if(title.equals("Save file")){
			selection = fileChooser.showSaveDialog(parentFrame);
		}else{
			selection = fileChooser.showOpenDialog(parentFrame);
		}
		if(selection == JFileChooser.APPROVE_OPTION) {
			currentFile = fileChooser.getSelectedFile();
			return true;
		}
		return false;
	}
	
	public File getCurrentFile(){
		return currentFile;
	}
}