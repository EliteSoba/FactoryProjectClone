package factory.interfaces;

import factory.Kit;

public interface ConveyorController {
	
	public void msgConveyorWantsEmptyKit(Conveyor c);
	public void msgKitExported(Conveyor c, Kit k);
	
}
