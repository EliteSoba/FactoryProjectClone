package factory;

import java.util.ArrayList;
import agent.Agent;
import java.util.*;
import factory.interfaces.*;

public class GantryAgent extends Agent implements Gantry {
	public ArrayList<MyBin> myBins = new ArrayList<MyBin>();   
	BinConfig binConfig;

	enum MyBinState { NEEDED, DELIVERED}

	class MyBin {

		MyBinState state;
		Part pt;
		Feeder fdr;

		public MyBin(Part part, Feeder feeder){
			this.state = MyBinState.NEEDED;
			this.fdr = feeder;
			this.pt = part;
		}
	}




	// *** MESSAGES ***

	public void msgFeederNeedsPart(Part part, Feeder feeder) {
		myBins.add(new MyBin(part, feeder));
		stateChanged();
	}

	public void msgChangeGantryBinConfig(BinConfig binConfig) {
		this.binConfig = binConfig;
		stateChanged();
	}


	// *** SCHEDULER ***

	public boolean pickAndExecuteAnAction() {

		for(MyBin b: myBins){
			if(b.state == MyBinState.NEEDED){
				goFetchTheRequestedBin(b);
				return true;
			}
		}
		return false;
	}


	// *** ACTIONS ***
	private void goFetchTheRequestedBin(MyBin b) {
		DoPickupPurgeBin(); //animation message
		DoRefillPurgeBin(binConfig.binList.get(b.pt));
		DoBringRequestedBin(binConfig.binList.get(b.pt /*b.fdr; not sure why this parameter is here.  leaving it for now*/));
		b.fdr.msgHereIsPart(b.pt);
		b.state = MyBinState.DELIVERED;
		stateChanged();
	}

	private void DoBringRequestedBin(Integer integer) {
		print("Bringing requested bin");
		
	}

	private void DoRefillPurgeBin(Integer integer) {
		print("Refilling Purge Bin");
		
	}

	private void DoPickupPurgeBin() {
		print("Picking up Purge Bin");
		
	}



}




