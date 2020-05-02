import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JPanel;

/**
 * @author Yijian Hu
 * @modified by Hongqi Zhang, Kairui Hsu, ShihYu Chang
 */
public class RightPanel extends JPanel implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	private String name;
	private Hashtable<Integer, ButtonBox> shapes;
	private Frame frame;
	protected List<Connection> connections;
	private Model model;
	private ValuePane vPane;
	boolean isAlreadyOneClick = false;
	
	RightPanel(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void init() {
		model = new Model();
		shapes = model.getTabs().get(name).getshapes();
		addMouseListener(this);
		connections = model.getTabs().get(name).getConnectionCollection();
		this.setLayout(null);
	}
	
	public void addButton(int id,String btnCommand,String title, int x, int y) {
		ButtonBox btn = ButtonBoxFactory.buildButtonBox(btnCommand);
		addActionAndMouseMotionListener(btn);
		this.add(btn);
		this.autoLocation(btn,x - btn.getPreferredSize().width / 2,y - btn.getPreferredSize().height / 2);
		btn.setToolTipText(btnCommand);
		btn.setTitle(title);
		if(id == 0) {
			shapes.put(btn.hashCode(),btn);
			btn.setId(btn.hashCode());
		}else {
			shapes.put(id,btn);
			btn.setId(id);
		}
		Model.getTabs().get(name).setshapes(shapes);
		this.repaint();
	}
	
	private void addActionAndMouseMotionListener(ButtonBox button) {
		button.addMouseMotionListener(this);
		button.addMouseListener(this);
	}
	
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	public void setShapeLocation(int hashCode, int x, int y) {
		shapes.get(hashCode).setLocation(x,y);
		model.getFrame().contentRepaint();
	}
	
	public Hashtable<Integer, ButtonBox> getShapes() {
		return shapes;
	}
	
	public void clear() {
		this.removeAll();
		shapes.clear();
	}
	
	private void autoLocation(ButtonBox button, int x, int y) {
		button.setSize(button.getPreferredSize());
		button.setLocation(x, y);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		e.getComponent().setLocation(e.getX() + e.getComponent().getX(), 
				 e.getY() + e.getComponent().getY());
		for(int i = 0; i < this.connections.size(); i++) {
        	Connection finishedconnection = connections.get(i);
        	Object obj = e.getComponent();
			if(obj instanceof JPanel){
				ButtonBox btn = (ButtonBox) obj;
				if(finishedconnection.getSourceButton() == btn.getId()) {
					finishedconnection.setSourceX(finishedconnection.getSourceX() + e.getX());
					finishedconnection.setSourceY(finishedconnection.getSourceY() + e.getY());
				}else if(finishedconnection.getDestButton() == btn.getId()) {
					finishedconnection.setDestX(finishedconnection.getDestX() + e.getX());
					finishedconnection.setDestY(finishedconnection.getDestY() + e.getY());
				}
			}
        }
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(this)) {
			Box instance = Box.getInstance();
			if(instance.getText() == null) {
				return;
			}
			addButton(0, instance.getText(), " ", e.getX(), e.getY());
		}else {
			if (e.getClickCount() == 2) {
				Object source = e.getComponent();
				if(source instanceof JPanel){
					ButtonBox panelPressed = (ButtonBox) source;
					if(vPane == null) {
						vPane = panelPressed.createJOptionPane();
						panelPressed.setTitle(vPane.getvalue());
					}else {
						String val = panelPressed.getTitle();
						vPane.setValue(val);
						panelPressed.setTitle(vPane.getvalue());
					}
				}
			}
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			this.remove(e.getComponent());
			Object source = e.getComponent();
			if(source instanceof JPanel){
				ButtonBox btn = (ButtonBox) source;
				connections.removeIf(n->(n.getSourceButton() == btn.getId()));
				connections.removeIf(n->(n.getDestButton() == btn.getId()));
				shapes.remove(btn.getId());
				this.repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

    public void paint(Graphics g) {
    	super.paint(g);
        for(int i = 0; i < this.connections.size(); i++) {
        	Connection finishedconnection = connections.get(i);
        	Line line = new Line();
        	line.setSource(finishedconnection.getSourceX(), finishedconnection.getSourceY());
        	line.setDest(finishedconnection.getDestX(), finishedconnection.getDestY());
        	line.draw(g);
        }
    }
    
    public void load(ShapeInfo[] shapeinfo) {
		this.clear();
		System.out.println(shapeinfo.toString());
    	for(int i = 0; i < shapeinfo.length; i++) {
    		addButton(shapeinfo[i].getId(), shapeinfo[i].getType(),shapeinfo[i].getTitle(),
    					shapeinfo[i].getPosition().x,shapeinfo[i].getPosition().y);
    	}
    }
}
