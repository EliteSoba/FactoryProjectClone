package factory;

import java.util.*;
import java.util.concurrent.Semaphore;

import factory.graphics.FrameKitAssemblyManager;
import factory.interfaces.*;
import factory.masterControl.MasterControl;
import agent.Agent;

public class ConveyorControllerAgent extends Agent implements ConveyorController {
	////Data	
	Conveyor conveyor;
	List<Kit> exported_kits = new ArrayList<Kit>();
	enum Conveyor_State { WANTS_EMPTY_KIT, EMPTY_KIT_SENDING, NO_ACTION };
	Conveyor_State conveyor_state = Conveyor_State.NO_ACTION;
	
	Timer timer = new Timer();
	
	//UnitTesting Constructor
	public ConveyorControllerAgent() {
		super(null);
	}
	
	public ConveyorControllerAgent(MasterControl mc) {
		super(mc);
	}
	
	////Messages
	public void msgConveyorWantsEmptyKit() {
		debug("received msgConveyorWantsEmptyKit() from the Conveyor");
		if (conveyor_state.equals(Conveyor_State.NO_ACTION)) {
			conveyor_state = Conveyor_State.WANTS_EMPTY_KIT;
			stateChanged();
		}
	}
	
	
	////Scheduler
	public boolean pickAndExecuteAnAction() {
		if (conveyor_state.equals(Conveyor_State.WANTS_EMPTY_KIT)) {
			conveyor_state = Conveyor_State.EMPTY_KIT_SENDING;
			sendEmptyKit();
			return true;
		}
		return false;
	}
	
	////Actions
	private void sendEmptyKit() {
		//Creates a random time that it will take for the empty kit to make it to the kitting cell
		
		int delivery_time = (int) (1000 + (Math.random()*2000)); //Random time it will take for the empty kit to make it to the cell
		
		timer.schedule(new TimerTask(){
		    public void run(){
		    	//After this timer, the graphics needs to play the new kit animation and then after tell the ConveyorAgent about the new empty kit
		    	//The message to tell the Conveyor about the new kit is Conveyor.msgHeresEmptyKit(new Kit());
		    	
		    	// Tell server to do animation of moving empty kit from conveyor to the topSlot of the stand
				//server.sendNewEmptyKit();
		    	server.command("cca fpm cmd emptyKitEntersCell");
				
				// Wait until the animation is done
				try {
					debug("Waiting on the server to finish the animation sendNewEmptyKit()");
					animation.acquire();
					//should tell the Conveyor now about the new kit before setting state to no_action
					conveyor.msgHeresEmptyKit(new Kit());
					conveyor_state = Conveyor_State.NO_ACTION;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		}, delivery_time);
		stateChanged();
	}
	
	// Hacks / MISC
	public void setConveyor(Conveyor c) {
		this.conveyor = c;
	}

}

