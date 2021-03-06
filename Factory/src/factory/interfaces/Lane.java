package factory.interfaces;


import java.util.List;

import factory.Part;


public interface Lane {

public Nest getNest(); //returns this lane's nest

public void msgIncreaseAmplitude();

public void msgDumpNest();

public void msgPurge();

public void setNest(Nest n); // used for testing purposes only

public void msgNestWasDumped();

public void msgNestNeedsPart(Part pt);

public void msgNestHasStabilized();

public void msgNestHasDestabilized();

public boolean hasMixedParts();

public void msgPartAddedToLane(Part part);

public void msgDumpYourNest();

public List<Part> getParts();

//public void msgFeedingParts(int numParts);
//
//public void msgNestIsOutOfParts();


}
