package factory.interfaces;

import factory.BinConfig;
import factory.Part;

public interface Gantry {

	void msgChangeGantryBinConfig(BinConfig binConfig);

//	void msgFeederNeeds(Part pt, Feeder feeder);

	void msgFeederNeedsPart(Part pt, Feeder feederAgent);

	void msgTestGetPart();


}
