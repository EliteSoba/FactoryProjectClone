package factory.graphics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

class GraphicPartsRobot extends GraphicRobot
{
	protected ArrayList<GraphicItem> items;			// inventory of items
	int itemIndex;									// item requested by the back-end
	
	/**
	 * @deprecated Use GraphicPartsRobot(...) constructor
	 */
	public GraphicPartsRobot()
	{
	}
	
	/**
	 * Initializes all variables of parts robot upon instantiation.
	 * @param init_x
	 * @param init_y
	 * @param init_theta
	 * @param init_dx
	 * @param init_dy
	 * @param init_dtheta
	 * @param init_imageWidth
	 * @param init_imageHeight
	 * @param init_imagePath
	 */
	public GraphicPartsRobot(int init_x, int init_y, int init_theta, int init_dx, int init_dy, int init_dtheta, int init_imageWidth, int init_imageHeight, String init_imagePath)
	{
		// Initialize inventory to have 4 null slots
		items = new ArrayList<GraphicItem>();
		for(int i = 0; i < 4; i++)
			items.add(null);
		// Initialize properties of the robot to passed-in and default values
		state = 0;		// 0 = idle, 1 = going to nest, 2 = arrived at nest, 3 = going to station, 4 = arrived at station, 5 = going to center, 6 = arrived at center
		destinationNest = -1;
		destinationKit = -1;
		x = init_x;
		y = init_y;
		fx = init_x;
		fy = init_y;
		ftheta = 0;
		theta = init_theta;
		dx = init_dx;
		dy = init_dy;
		dtheta = init_dtheta;
		imageWidth = init_imageWidth;
		imageHeight = init_imageHeight;
		image = Toolkit.getDefaultToolkit().getImage(init_imagePath);
		itemIndex = 0;
		// The parts robot will prioritize: up, right, left, down
		movementCheckingOrders = new int[4];
		movementCheckingOrders[0] = 1;
		movementCheckingOrders[0] = 0;
		movementCheckingOrders[0] = 2;
		movementCheckingOrders[0] = 3;
	}
	
	/**
	 * Gets the kit that the parts robot is assigned to move to (zero-based indexing).
	 * @return destinationKit
	 */
	public int getDestinationKit()
	{
		return destinationKit;
	}
	
	/**
	 * Sets the kit that the robot should move to (zero-based indexing).
	 * @param init_destinationKit
	 */
	public void setDestinationKit(int init_destinationKit)
	{
		destinationKit = init_destinationKit;
	}
	
	/**
	 * Gets the nest that the parts robot is assigned to move to (zero-based indexing).
	 * @return destinationNest
	 */
	public int getDestinationNest()
	{
		return destinationNest;
	}
	
	/**
	 * Sets the nest that the robot should move to (zero-based indexing).
	 * @param init_destinationNest
	 */
	public void setDestinationNest(int init_destinationNest)
	{
		destinationNest = init_destinationNest;
	}
	
	/**
	 * Sets the index of the item requested by the back-end (zero-based indexing).
	 * @param index
	 */
	public void setItemIndex(int index) {
		itemIndex = index;
	}
	
	/**
	 * Gets the index of the item requested by the back-end (zero-based indexing).
	 * @return itemIndex
	 */
	public int getItemIndex() {
		return itemIndex;
	}
	
	/**
	 * Sets the first empty slot in the parts robot's inventory as newItem.
	 * @param newItem The item being added to the parts robot's inventory.
	 */
	public void addItem(GraphicItem newItem)
	{
		for(int i = 0; i < 4; i++)
			if(items.get(i) == null)
			{
				items.set(i, newItem);
				break;
			}
	}
	
	/**
	 * Sets all items in the robot's inventory to null (essentially clearing all of its items).
	 */
	public void clearItems()
	{
		for(int i = 0; i < 4; i++)
			items.set(i, null);
	}
	
	/**
	 * Returns true if the robot has at least 1 item, false otherwise.
	 * @return If robot has items.
	 */
	public boolean hasItem()
	{
		for(int i = 0; i < items.size(); i++)
			if(items.get(i) != null)		// non-null item found!
				return true;
		return false;
	}
	
	/**
	 * Gets the last item in parts robot's inventory.
	 * @deprecated Use popItemAt(int)
	 * @return lastItem
	 */
	public GraphicItem popItem()
	{
		if (items.size() == 0)
			return null;
		GraphicItem lastItem = items.get(items.size()-1);	// get last item as a copy
		items.set(items.size()-1, null);					// set last item to null
		return lastItem;									// return copy of last item
	}
	
	/**
	 * Gets an item at the provided index and sets the item to null.
	 * @param index The index of the item being taken.
	 * @return The item at the provided index; returns null if the index is invalid
	 */
	public GraphicItem popItemAt(int index) {
		if (index < 0 || index >= items.size())
			return null;
		GraphicItem returnedItem = items.get(index);
		items.set(index, null);
		return returnedItem;
	}
	
	/**
	 * Gets the size of the parts robot's inventory.
	 * @return Size of the inventory (4 by default).
	 */
	public int getSize()
	{
		return items.size();
	}
	
	/**
	 * Gets the item at the provided position in the parts robot's inventory.
	 * @param i
	 * @return The item at position i in the inventory (zero-based indexing).
	 */
	public GraphicItem getItemAt(int i)
	{
		return items.get(i);
	}
	
	/**
	 * Paints the robot (via superclass function) and paints the items the robot is carrying in its inventory.
	 */
	public void paint(Graphics g)
	{
		// Draw the robot
		super.paint(g);
		// Draw the items the robot is carrying
		for(int i = 0; i < items.size(); i++)
			if(items.get(i) != null)				// don't try to paint null items
				items.get(i).paint(g, x+imageWidth-25,y+10+i*20);
	}

}