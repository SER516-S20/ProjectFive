import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rohit Kumar Singh
 * @modified on 04-26-2020
 * @version 1.0
 */
public class Translate {
	
	private static RightPanelDataProcessor processor = new RightPanelDataProcessor	();
	private static List<Icon> icons = new ArrayList<Icon>();
	private static HashMap<String, List<Icon>> iconMap; 
	private static HashMap<Integer,String> iconStore = new HashMap<Integer,String>();
	
	public static void processTranslate(RightPanelDataProcessor _processor) {
		processor = _processor;	
	}
	
	public static void printTranslate() {
		
		List<Line> lines = processor.getLineList();
		HashMap<String, List<Icon>> icons = processor.getIconMap();
		iconMap = processor.getIconMap();
		
		storeAllIcons();
		
		System.out.println(iconMap.size());
		
		for(int i=0;i<lines.size();i++) {
			Dot start = lines.get(i).getStartDot();
			Dot end = lines.get(i).getEndDot();
			compute(start.getX(), end.getX());
		}
			
	}
	
	private static void storeAllIcons() {
		
		icons = new ArrayList<>(); 
		for(String key: iconMap.keySet()) {
			
			List<Icon> iconsStored = iconMap.get(key);
			
			for(Icon icon: iconsStored) {
				iconStore.put(icon.getCenterX(), icon.getTraslateSymbol());
			}
			
			icons.addAll(iconsStored);
		}
				
	}
		
	
	private static void compute(Integer source_x_cordinate, Integer target_x_cordinate) {

		String source =iconStore.get(source_x_cordinate-187);
		String target = iconStore.get(target_x_cordinate-7);
		System.out.println(source+ " -> "+ target);	
	}

}
	
	