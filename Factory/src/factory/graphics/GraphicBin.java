//Minh La

package factory.graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import factory.Part;

public class GraphicBin{

	ArrayList <GraphicItem> binItems;
	ImageIcon binImage;
	String partName;
	ImageIcon binItemsStackImage;

	public GraphicBin(Part part){
		partName = part.name;
		int binSize = 12 * 55;		//Number of items in bin
		binItems = new ArrayList<GraphicItem>();
		for(int i = 0; i < binSize;i++){
			binItems.add(new GraphicItem());
		}
		//Declaration of items in bin's location
		for(int i = 0;i<binItems.size();i++){
			binItems.get(i).setX(-40);
			binItems.get(i).itemImage = new ImageIcon("Images/" + partName + ".png");
		}
		binItemsStackImage = new ImageIcon("Images/"  + partName +"Stack.png");
		binImage = new ImageIcon("Images/bin.png");
	}

	public ArrayList<GraphicItem> getBinItems(){
		return binItems;
	}

	public ImageIcon getBinImage(){
		return binImage;
	}

}