package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import model.AtSymbol;
import model.CloseParanthesis;
import model.GreaterSymbol;
import model.HashSymbol;
import model.LesserSymbol;
import model.MinusSymbol;
import model.OpenParanthesis;
import model.OrSymbol;
import model.Symbol;

/**
 *
 * @author Rahul
 * @since 03-16-2020
 * @Description: This class restores all tabs data  from .txt file and repaints it.
 */
public class OpenApplication implements java.io.Serializable{
		
	public OpenApplication(JFrame frame) {
		super();
		
		JLabel fileName = new JLabel("no file selected");
		JFileChooser fileChooser = new JFileChooser();  
		int r = fileChooser.showOpenDialog(null); 
		
		if (r == JFileChooser.APPROVE_OPTION) {  
            fileName.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
            
            File fileContent = fileChooser.getSelectedFile();
    		BufferedReader reader;	
    		
    		JTabbedPane rp = WorkSpace.getInstance().getRightPanel();
    		rp.removeAll();
    		
    		Collector.getInstance().initialize();
    		
    		try {
    			reader = new BufferedReader(new FileReader(fileContent));
                String line = reader.readLine();
                
                while(line != null) {
                	
                	String[] info = line.split(";");
                	
                	if(info[0].equals("Tab")) {
                		
                		WorkPanel workPanel = new WorkPanel(rp.getWidth(), rp.getHeight()); 
                		rp.addTab(info[2], workPanel);
                	}
                	else if(info[0].equals("shape")){
                    	
                    	String name = info[1];
                    	int x = Integer.parseInt(info[2]);
                    	int y = Integer.parseInt(info[3]);
                    	String userInput = info[4];
                    	int hashTabIndex = -1;
                    	int tabIndex = Integer.parseInt(info[5]);
                    	
                    	if(info.length == 7) {
                    		hashTabIndex = Integer.parseInt(info[6]);
                    	}
                    	
                    	createSymbol(name,x, y, userInput, hashTabIndex, tabIndex);
              
                    	rp.repaint();
                	}
                	else {
                		
                		int tabIndex = Integer.parseInt(info[1]);
                		int pC1Index = Integer.parseInt(info[2]);
                		int c1Index = Integer.parseInt(info[3]);
                		
                		for(String c2 : info[4].split(":")) {
                			String[] c2L = c2.split("-");
                			int pC2Index = Integer.parseInt(c2L[0]);
                    		int c2Index = Integer.parseInt(c2L[1]);
                    		
                    		addLine(tabIndex, pC1Index, c1Index, pC2Index, c2Index);
                		}
                		
                		
                	}
                	line = reader.readLine();
                }
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		else
			fileName.setText("the user cancelled the operation"); 
	}
	
	private void createSymbol(String name,int x, int y, String userInput, int hashTabIndex, int tabIndex) {
		
		Symbol s = null;
		JTabbedPane rp = WorkSpace.getInstance().getRightPanel();
		WorkPanel wp = (WorkPanel) rp.getComponent(tabIndex);
		
		switch(name){
			case "(": s = new OpenParanthesis(wp, x, y);
				wp.setOpenP(true);
				break;
			case ")": s = new CloseParanthesis(wp, x, y);
				wp.setCloseP(true);
				break;
			case "<": s = new LesserSymbol(wp, x, y);
				break;
			case ">": s = new GreaterSymbol(wp, x, y);
				break;
			case "@": s = new AtSymbol(wp, x, y);
				break;
			case "||": s = new OrSymbol(wp, x, y);
				break;
			case "-": s = new MinusSymbol(wp, x, y);
				break;
			case "#": s = new HashSymbol(wp, x, y);
						if(hashTabIndex != -1) {
							WorkPanel tab = (WorkPanel) rp.getComponent(hashTabIndex);
							HashSymbol hashSymbol = (HashSymbol) s;
							hashSymbol.setTab(tab);
							hashSymbol.setTabFlag(true);
						}
				break;
		}
		s.setUserInput(userInput);
	}
	
	private void addLine(int tabIndex, int pC1Index, int c1Index, int pC2Index, int c2Index) {
		
		WorkPanel tab = (WorkPanel) WorkSpace.getInstance().getRightPanel().getComponent(tabIndex);
		
		Symbol s1 = (Symbol) tab.getComponent(pC1Index);
		Connector c1 = (Connector) s1.getComponent(c1Index);
		
		Symbol s2 = (Symbol) tab.getComponent(pC2Index);
		Connector c2 = (Connector) s2.getComponent(c2Index);
		
		if(c1.getType() == "Dot")
			c1.setLine(true);
		if(c2.getType() == "Dot")
			c2.setLine(true);
		
		Collector.getInstance().addTabLines(tab, c1, c2);
		
	}
}
